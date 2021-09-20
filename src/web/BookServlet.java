package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author mrs
 * @create 2021-08-07  11:33
 */
public class BookServlet extends BaseServlet{
   private BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.paresInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、调用BookService.addBook()保存图书
        bookService.addbook(book);
        //3、跳到图书列表页面 /manager/bookServlet?action=page
        //不能使用一次性请求，因为网页会保存最后一次请求，如果用户按F5，则会重复发送请求，即会再次重复添加，从而出现bug
//        req.getRequestDispatcher("/manager/bookServlet?action=page").forward(req,resp);
        //使用重定向，分为两次请求，即最后一次请求只是查询东西，不会重复添加了，更加安全！！
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数id，图书教程
        int id = WebUtils.paresInt(req.getParameter("id"),0);
        //2、调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        //3、重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数图书编号
        int id = WebUtils.paresInt(req.getParameter("id"), 0);
        //2、调用bookSerivice,queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3、保存图书到Request域中
        req.setAttribute("book",book);
        //4、请求转发到： pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        String id = req.getParameter("id");
        System.out.println(id);
        //2、调用BookService.updateBook(book);修改图书
        bookService.updateBook(book);
        //3、重定向回图书列表管理页面
//        地址:/工程名/manager/bookServlet?action=page
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.paresInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.paresInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BookService.page(pageNo,pageSize):Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、保存配置对象到Request域中
        req.setAttribute("page",page);
        //4、请求转发到page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }
}
