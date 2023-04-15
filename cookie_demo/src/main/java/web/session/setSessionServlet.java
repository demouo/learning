package web.session;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/setSessionServlet")
public class setSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        //做好参数
        String username = URLEncoder.encode("用户名","UTF-8");
        String value=URLEncoder.encode("小莫","UTF-8");
        //获取一个session


        final HttpSession reqSession = req.getSession();

        reqSession.setAttribute(username,value);

         System.out.println(reqSession);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
    this.doGet(req, resp);
    }
}
