package com.momo.web;

import com.momo.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {

    private final BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/**
 * 删除功能
 * 1.参数：id
 * 2.无结果
 */
        Integer id = Integer.parseInt(req.getParameter("id"));

        brandService.deleteById(id);

        req.getRequestDispatcher("/selectAllServlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
