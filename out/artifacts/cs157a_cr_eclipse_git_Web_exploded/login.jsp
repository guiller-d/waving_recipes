<%@ page import="controller.Controller" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js" lang="">

<head>
    <link rel="stylesheet" href="main.css">
</head>

<body>

<div class="container" id="container">
    <div class="form-container sign-up-container">
        <!-- Sign Up form code goes here -->
        <form action="/register" method="post">
            <h1>Create Account</h1>

            <span>With your username and password</span>
            <input type="username" placeholder="Username"  name="username" />
            <input type="Password" placeholder="password" name="password" />
            <input type="Password" placeholder="VerifyPassword" name="password_repeat" />
            <button type = "submit" name="register">Sign Up</button>
        </form>


    </div>
    <div class="form-container sign-in-container">
        <!-- Sign In form code goes here -->
        <form action="/login" method="post">
            <h1>Sign in</h1>
            <span>With your account</span>
            <input type="username" placeholder="Username" name="username" />
            <input type="password" placeholder="Password" name="password"/>
            <a href="#">Forgot your password?</a>
            <button type="submit" name="login">Sign In</button>
        </form>


    </div>
    <div class="overlay-container">
        <!-- The overlay code goes here -->
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>Keep Connected With Your Personal Info.</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <p>Enter your Personal details and start exploring Foodies</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>

<script>
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');

    signUpButton.addEventListener('click', () => {
        container.classList.add('right-panel-active');
    });

    signInButton.addEventListener('click', () => {
        container.classList.remove('right-panel-active');
    });
</script>

</body>

</html>

<%--
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

<div class = "middle-container">
    <h1> Don't have account yet? <h1>
</div>
<form action="/register" method="post" class="form">
    <button type="submit" name="register">Register</button>
</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<p style="color:red";>${errorMessage}</p>




<%
    /*********
     following.jsp
     myRecipe.jsp

     addRecipe button
     heart (empty and solid) RED
     Favorite page


    ****/

%>
</body>
</html> --%>


