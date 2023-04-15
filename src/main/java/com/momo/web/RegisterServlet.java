package com.momo.web;

import com.momo.pojo.User;
import com.momo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/**
 * 注册功能
 * 1.参数：req中的参数
 * 2.无结果
 *
 */
        //处理post的请求的乱码
        req.setCharacterEncoding("utf-8");
        //获取注册信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //获取用户的验证码
        final String checkCode = req.getParameter("checkCode");
        //获取程序生成的验证码
        final HttpSession session = req.getSession();
        final String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        //比对验证码  验证码不相同的话 提示+截止
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            req.setAttribute("register_mes", "宝宝看清楚哇，验证码错误啦");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

            //不允许注册
            return;
        }

        //判断注册成功
        final boolean registerSucceed = userService.register(user);

        if (registerSucceed) {
            //注册成功  跳转登录
            req.setAttribute("register_mes", "注册成功，请登录");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            //注册失败
            req.setAttribute("register_mes", "用户名已存在，请重新注册");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
