package com.momo.web;

import com.alibaba.fastjson.JSON;
import com.momo.pojo.Brand;
import com.momo.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

     private final BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/**
 * 查询all
 * 1.无参数
 * 2.返回List
 */
       // final List<Brand> brands = brandService.selectAll();
         // req.setAttribute("brands", brands);
        //req.getRequestDispatcher("/brand.jsp").forward(req, resp);

        //1. 调用Service查询
        List<Brand> brands = brandService.selectAll();

        //2. 将集合转换为JSON数据   序列化
        String jsonString = JSON.toJSONString(brands);

        //3. 响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
