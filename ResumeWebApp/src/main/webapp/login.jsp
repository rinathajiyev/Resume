<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
        <form action="login" method="POST">
            <div class="divElement">
                <center>
                    <h1 class="login_header">Sign in</h1>
                </center>

                <hr/>
                <div class="form-group col">
                    <label class="login_body" for="email">Email address</label>
                    <input class="form-control" placeholder="enter your email" type="email" name="email"/>
                </div>

                <div class="form-group col">
                    <label class="login_body" for="password">Password</label>
                    <input class="form-control" placeholder="enter your password" type="password" name="password"/>
                </div>

                <div class="col-6">
                    <button class="btn btn-primary" type="submit" name="signIn">Sign in</button>
                </div>
            </div>
        </form>
</body>
</html>
