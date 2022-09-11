<%@page import="com.company.entity.User"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/users.css">
        <link rel="stylesheet" href="//use.fontawesome.com/releases/v6.2.0/css/all.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script type="text/javascript" src="assets/js/users.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
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
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <%for(User u: users){%>
                        <tr>
                            <td><%=u.getName()%></td>
                            <td><%=u.getSurname()%></td>
                            <td><%=u.getNationality().getNationality()== null?"N/A":u.getNationality().getNationality()%></td>
                            <td style="width:2px">
                                    <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                    <input type="hidden" name="action" value="delete"/>
                                    <button onclick="setIdForDelete('<%=u.getId()%>')" data-toggle="modal" data-target="#exampleModalCenter" class="btn btn-danger" type="button" value="delete">
                                        <i class="fa-solid fa-trash-can"></i>
                                    </button>
                            </td>
                            <td style="width:2px">
                                <form action="userdetail" method="GET">
                                    <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                    <button class="btn btn-secondary" type="submit", value="update">
                                        <i class="fa-solid fa-pen"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <%}%>
                    </tbody>
                </table>
                </div>
            </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Remove</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure?
                    </div>
                    <div class="modal-footer">
                        <form action="userdetail" method="POST">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="id" value="" id="setId"/>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-danger" value="Delete"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
