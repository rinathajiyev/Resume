package com.company.resumewebapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter(urlPatterns = {"*"}, filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    public void  doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain) {
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;

            if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null) {
                res.sendRedirect("login");
            } else{
                chain.doFilter(request, response);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void destroy(){

    }

    public void init(FilterConfig filterConfig){

    }
}
