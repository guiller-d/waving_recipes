<%@ page import="controller.Controller" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.

<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="login.css">
</head>
<h1>Login Page</h1>
<body>
<form action="/login" method="post">
    <h1>SIGN IN</h1>
    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required>
        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <button type="submit" name="login">Login</button>
        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
    </div>
    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
</form>

<form action="/register.jsp" method="post">
    <button type="submit" name="register">Register</button>
</form>
</body>
</html>
<div class = "middle-container">
    <h1> Don't have account yet? <h1>
        <form action="/login" method="post">
            <button type="submit" name="register">Register</button>
        </form>
</div>
<p style="color:red";>${errorMessage}</p>
--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <button type="submit" name="login" id="somebutton">SIGN IN</button>

    <br/>
    <div class="container">
        <span class="psw"> <a href="#">Forgot Password?</a></span>
    </div>
</form>
<br/>
<br/>
<br/>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        $.get("login", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
            $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
        });
    });
</script>
<p style="color:red";>${errorMessage}</p>

<div class = "middle-container">
    <h1> Don't have account yet? <h1>
        <form action="register.jsp" method="post">
            <button type="submit" name="register">Register</button>
        </form>
</div>

</body>
</html>


