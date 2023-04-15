package com.momo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/update.jsp")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //1.注意到req现在不是httpServlet
        HttpServletRequest req=(HttpServletRequest) servletRequest;

        //0.强烈注意：跟登录相关的东西不要拦截 不然怎么登录啊 全部放行
        //0.1存登录资源名
        String []urls={"/css/","/imgs/","/login.jsp","/register.jsp","/loginServlet","/registerServlet","/checkCodeServlet"};

                for(String u:urls){
                    if(req.getRequestURL().toString().contains(u)){
                        //不能拦截
                        //放行
                        filterChain.doFilter(req, servletResponse);
                        return;
                    }
                }



        //2.针对登录态 登录检查：session对象
        final Object user = req.getSession().getAttribute("user");
        if(user!=null){
            //登录中
            //放行
            filterChain.doFilter(req, servletResponse);
        }else{
            //没登录 跳转&提示
            req.setAttribute("login_err","尚未登录，别想悄悄进入");
            req.getRequestDispatcher("login.jsp").forward(req, servletResponse);
        }





    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
