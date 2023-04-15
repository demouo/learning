package web.cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/setCookieServlet")
public class setCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
       //发送cookie
        //把中文转url编码
        String  username="用户名";
        String value="张三";
        username=URLEncoder.encode(username, "UTF-8");
         value= URLEncoder.encode(value, "UTF-8");
        Cookie cookie =new Cookie(username,value);
        System.out.println("编码后:"+username+" " +value);
        //七天的表达
        cookie.setMaxAge((60*60*24*7));
        //发送

        resp.addCookie(cookie);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
    this.doGet(req, resp);
    }
}
