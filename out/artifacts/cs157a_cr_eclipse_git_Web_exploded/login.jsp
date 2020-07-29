<%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="login.css">

</head>

<body>
<form action="/login" method="post" class="form">
    <h1>SIGN IN</h1>
    <label for="username"><b><em>Username</em></b></label>
    <input type="text" placeholder="Enter Username" name="username" required>
    <label for="password"><b><em>Password</em></b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <label>
        <input type="checkbox" checked="checked"name="Remember Me"> Remember Me
    </label>
    <br/>
    <button type="submit" name="login">SIGN IN</button>

    <br/>
    <div class="container">
        <span class="psw"> <a href="#">Forgot Password?</a></span>
    </div>
</form>
<br/>
<br/>
<br/>

<p style="color:red";>${errorMessage}</p>

<div class = "middle-container">
    <h1> Don't have account yet? <h1>
        <form action="/login" method="post">
            <button type="submit" name="register">Register</button>
        </form>
</div>

</body>
</html>


