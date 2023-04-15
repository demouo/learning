package web.session;


import jdk.internal.org.jline.reader.SyntaxError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/getSessionServlet")
public class getSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        //获取key value
        final HttpSession session = req.getSession();

        String username= URLEncoder.encode("用户名","utf-8");
        final Object attribute =session.getAttribute(username);
        String value=URLDecoder.decode((String)attribute, "UTF-8");
        System.out.println("用户名"+"="+value);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
    this.doGet(req, resp);
    }
}
