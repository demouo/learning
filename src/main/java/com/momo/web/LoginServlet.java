package com.momo.web;

import com.momo.pojo.User;
import com.momo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

     private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/**
 * 添加功能
 * 1.参数：req中的参数
 * 2.无结果
 *
 */
       //处理post的请求的乱码
        req.setCharacterEncoding("utf-8");

      String username = req.getParameter ("username");
      String password = req.getParameter ("password");

      //获取复选款
        String remember = req.getParameter("remember");

        final User user = userService.login(username, password);

        if (user != null) {

            if ("1".equals(remember)) { //登录成功 而且勾选了 记住我
                //记住我  发送cookie

                //用户名一个cookie  //密码一个cookie
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                //存活
                usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
                //发送
                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);
            }
                //存个session 因为要欢迎这个用户 但是现在是一个会话的两次请求了
                req.getSession().setAttribute("user", user);
                //登录成功
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/selectAllServlet");
        }else {
            //登录失败  跳转

            req.setAttribute("login_err", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
