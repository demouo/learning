package web.cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/getCookieServlet")
public class getCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
       //获取cookie
        for (Cookie cookie : req.getCookies()) {
            String username=cookie.getName();
            username= URLDecoder.decode(username, "UTF-8");
            if("用户名".equals(username))
            {
                String value=cookie.getValue();
                value=URLDecoder.decode(value, "UTF-8");
                System.out.println(username+"="+value);
                break;
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
    this.doGet(req, resp);
    }
}
