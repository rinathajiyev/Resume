package com.company.resumewebapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"*.jsp"}, filterName = "JspFilter")
public class JspFilter implements Filter {
    public void  doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain) {
        HttpServletResponse res= (HttpServletResponse) response;

        try {
            res.sendRedirect("error?msg=not found");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
