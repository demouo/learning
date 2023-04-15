package com.momo.web;

import com.momo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.momo.util.CheckCodeUtil;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {

     private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //生成验证码
        final ServletOutputStream outputStream = resp.getOutputStream();
        final String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);

        //存入session
        final HttpSession session = req.getSession();
        session.setAttribute("checkCodeGen",checkCode);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
