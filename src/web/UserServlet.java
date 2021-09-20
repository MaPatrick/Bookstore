package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author mrs
 * @create 2021-08-06  11:17
 */
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.调用userSerivce.login()登录处理
        User login = service.login(new User(null, username, password, null));
        //如果等于null，说明登录失败！
        if(login == null){
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);

            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            //登录成功
            //保存用户登录之后的信息到Session域中
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());



        //2、检查验证码是否正确======= 写死，要求验证码是abcde
        if(token!=null&&token.equalsIgnoreCase(code)){
            //正确
            //3、检查用户名是否可用
            if(service.existsUsername(username)){
                //不可用
                //跳回注册页面
                System.out.println("用户名"+username+"已存在");
                //把回显信息，保存到request域中
                req.setAttribute("msg","用户名已存在！");
                req.setAttribute("password",password);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用
                //调用Service保存到数据库
                //跳到注册成功页面 regist_success.jsp
                service.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //把回显信息，保存到request域中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("email",email);
            //不正确
            //跳回注册页面
            System.out.println("验证码"+code+"错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注销的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁Session中用户登录的信息(或者销毁Session)
        req.getSession().invalidate();
        //2、重定向到首页(或登录页面)
        resp.sendRedirect(req.getContextPath());
    }
}
