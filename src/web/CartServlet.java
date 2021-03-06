package web;

import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mrs
 * @create 2021-08-11  18:59
 */
public class CartServlet extends BaseServlet {
    private BookService bookSerice = new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取请求的参数，商品编号
        int id = WebUtils.paresInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookSerice.queryBookById(id);
        //把图书信息，转换为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.paresInt(req.getParameter("id"),0);
        //获取购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向到原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void Clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //清空购物车
            cart.clear();
            //重定向到原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数、商品编号、商品数量
        int id = WebUtils.paresInt(req.getParameter("id"),0);
        int count = WebUtils.paresInt(req.getParameter("count"),1);
        //获取Cart购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart != null){
            //修改商品数量
            cart.updateCount(id,count);
            //重定向到原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
