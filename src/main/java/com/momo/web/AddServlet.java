package com.momo.web;

import com.alibaba.fastjson.JSON;
import com.momo.pojo.Brand;
import com.momo.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private final BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/**
 * 添加功能
 * 1.参数：req中的参数
 * 2.无结果
 *
 */
//        //处理post的请求的乱码
//        req.setCharacterEncoding("utf-8");
//
//        String companyName = req.getParameter("companyName");
//        String brandName = req.getParameter("brandName");
//        String ordered = req.getParameter("ordered");
//        String description = req.getParameter("description");
//        String status = req.getParameter("status");
//
//        Brand brand = new Brand();
//        brand.setId(null);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
//        brand.setOrdered(Integer.parseInt(ordered));
//        brand.setDescription(description);
//        brand.setStatus(Integer.parseInt(status));
//
//        brandService.add(brand);
//
//        req.getRequestDispatcher("/selectAllServlet").forward(req, resp);

        //1. 接收数据,request.getParameter 不能接收json的数据
       /* String brandName = request.getParameter("brandName");
        System.out.println(brandName);*/

        // 获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        // 将JSON字符串转为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. 调用service 添加
        brandService.add(brand);

        //3. 响应成功标识
        resp.getWriter().write("success");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
