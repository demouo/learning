package com.momo.web;

import com.momo.pojo.Brand;
import com.momo.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {

     private final BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/**
 * 修改功能
 * 1.参数：id
 * 2.返回一个对象
 *
 */
       //处理post的请求的乱码
    //    req.setCharacterEncoding("utf-8");

      String id = req.getParameter ("id");

        final Brand brand = brandService.selectById(Integer.parseInt(id));

        req.setAttribute("brand",brand);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
