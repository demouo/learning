package com.momo.web;

import com.momo.pojo.Brand;
import com.momo.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {

    private final BrandService brandService = new BrandService();

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

        String id = req.getParameter("id");
        String companyName = req.getParameter("companyName");
        String brandName = req.getParameter("brandName");
        String ordered = req.getParameter("ordered");
        String description = req.getParameter("description");
        String status = req.getParameter("status");

        Brand brand = new Brand();
        brand.setId(null);
        brand.setId(Integer.parseInt(id));
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));

        brandService.update(brand);

        req.getRequestDispatcher("/selectAllServlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
