<%@page import="com.company.entity.User"%>
<%@page import="com.company.dao.inter.UserDaoInter"%>
<%@page import="com.company.main.Context"%>
<%@ page import="com.company.entity.Country" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.dao.inter.CountryDaoInter" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/userdetail.css">
        <link rel="stylesheet" href="//use.fontawesome.com/releases/v6.2.0/css/all.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Integer id = Integer.valueOf(request.getParameter("id"));
            UserDaoInter userDao = Context.instanceUserDao();
            User user = userDao.getById(id);

            CountryDaoInter countryDao = Context.instanceCountryDao();

            List<Country> countries = countryDao.getAllCountries();
            List<Country> nationalities = countryDao.getAllNationalities();
        %>

            <form action="userdetail" method="POST">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <div style="width:50%" class="divElement">
                    <div class="form-group col-5 set_position">
                        <label class="set_words" for="name">Name</label>
                        <input placeholder="enter your name" class="form-control" type="text" name="name" value="<%=user.getName()%>"/>
                    </div>
                    <div class="form-group col-5 set_position">
                        <label class="set_words" for="surname">Surname</label>
                        <input placeholder="enter your surname" class="form-control" type="text" name="surname" value="<%=user.getSurname()%>"/>
                    </div>
                    <div class="form-group col">
                        <i class="fa-solid fa-user"></i>
                        <label class="set_words" for="profile">Profile</label>
                        <textarea style="height: 100px" class="form-control" name="profile"><%=user.getProfileDesc()%></textarea>
                    </div>
                    <div class="form-group col">
                        <i class="fa-solid fa-location-dot"></i>
                        <label class="set_words" for="address">Address</label>
                        <input placeholder="enter your address" class="form-control" type="text" name="address" value="<%=user.getAddress()%>"/>
                    </div>
                    <div class="form-group col">
                        <i class="fa-solid fa-phone"></i>
                        <label class="set_words" for="phone">Phone</label>
                        <input placeholder="enter your phone number" class="form-control" type="text" name="phone" value="<%=user.getPhone()%>"/>
                    </div>
                    <div class="form-group col">
                        <i class="fa-solid fa-cake-candles"></i>
                        <label class="set_words" for="birthdate">Birthdate</label>
                        <input placeholder="enter your birthdate" class="form-control" type="text" name="birthdate" value="<%=user.getBirthDate()%>"/>
                    </div>
                    <div class="form-group col">
                        <i class="fa-solid fa-house"></i>
                        <label class="set_words" for="birthplace">Birthplace</label>
                        <select class="form-control">
                            <option selected><%=user.getBirthPlace().getName()%></option>
                            <%for(Country c: countries){%>
                            <option><%=c%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group col">
                        <i class="fa-solid fa-flag"></i>
                        <label class="set_words" for="nationality">Nationality</label>
                        <select class="form-control">
                            <option selected><%=user.getNationality().getNationality()%></option>
                            <%for(Country n: nationalities){%>
                            <option><%=n%></option>
                            <%}%>
                        </select>
                    </div>
                </div>


                <div style="width:50%" class="col divElement">
                    <input type="hidden" name="action" value="update"/>

                    <center>
                        <input class="btn btn-primary col-6" type="submit" name="save" value="Save"/>
                    </center>
                </div>
            </form>

    </body>
</html>
