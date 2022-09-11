package com.company.resumewebapp.controller;

import com.company.dao.inter.*;
import com.company.entity.*;
import com.company.main.*;
import com.company.resumewebapp.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userDao.findByEmailAndPassword(email, password);

            if (user == null) {
                throw new IllegalArgumentException("the email or password is incorrect! Please try again...");
            }

            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
        } catch (Exception ex){
            ControllerUtil.errorPage(response, ex);
        }

    }

}
