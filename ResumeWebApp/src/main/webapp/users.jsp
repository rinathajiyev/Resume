<%-- 
    Document   : user
    Created on : Aug 30, 2022, 10:30:36 AM
    Author     : Casper
--%>

<%@page import="com.company.entity.User"%>
<%@page import="com.company.dao.inter.UserDaoInter"%>
<%@page import="com.company.main.Context"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/users.css">
        <link rel="stylesheet" href="//use.fontawesome.com/releases/v6.2.0/css/all.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDaoInter userDao = Context.instanceUserDao();
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String nationalityIdStr = request.getParameter("nid");
            Integer nationalityId = null;

            if(nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
                nationalityId = Integer.parseInt(nationalityIdStr);
            }
            List<User> users = userDao.getAll(name, surname, nationalityId);
        %>

            <div class="container mycontainer">
                <div class="row">
                    <div class="col-4">
                        <form action="users.jsp" method="GET">
                        <div class="form-group">
                            <label for="name">name</label>
                            <input placeholder="enter name" class="form-control" type="text" name="name" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="surname">surname</label>
                            <input placeholder="enter surname" class="form-control" type="text" name="surname" value=""/>
                        </div>

                        <input class="btn btn-primary" type="submit" name="search" value="Search"/>
                    </form>
                    </div >
                </div>

                <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>name</th>
                            <th>surname</th>
                            <th>nationality</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <%for(User u: users){%>
                        <tr>
                            <td><%=u.getName()%></td>
                            <td><%=u.getSurname()%></td>
                            <td><%=u.getNationality().getName() == null?"N/A":u.getNationality().getName()%></td>
                            <td>
                                <button class="btn btn-danger" type="submit", name="action", value="delete">
                                    <i class="fa-solid fa-trash-can"></i>
                                </button>
                                <button class="btn btn-secondary" type="submit", name="action", value="update">
                                    <i class="fa-solid fa-pen"/></i>
                                </button>
                            </td>
                        </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
            </div>
    </body>
</html>
