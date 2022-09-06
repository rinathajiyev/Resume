package com.company.resumewebapp;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = {"/userdetail"})
public class UserController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User u = userDao.getById(id);
        u.setName(name);
        u.setSurname(surname);

        userDao.updateUser(u);

        response.sendRedirect("userdetail.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            User user = null;
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("specify id");
            }

            Integer userId = Integer.valueOf(request.getParameter("id"));
            UserDaoInter userDao = Context.instanceUserDao();
            user = userDao.getById(userId);
            if (user == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }

            request.setAttribute("user", user);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);

        } catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        }

    }

}
