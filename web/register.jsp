<%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
<form action="/register" method="post">
    <div class="top-container">
        <h1>SIGN UP</h1>
        <label for="uname"><b><em>Username</em></b></label>
        <input type="text" placeholder="Enter Username" class="input-box" name="username" required>
        <label for="psw"><b><em>Password</em></b></label>
        <input type="password" placeholder="Enter Password" class="input-box" name="password" required>
        <label for="psw"><b><em>Repeat Password</em></b></label>
        <input type="Repeat password" placeholder="Repeat Password" class="input-box" name="password_repeat" required>
        <p><span><input type="checkbox"></span><em>I agree to all the terms and conditons.</em></p>
        <button type="submit" name="signon">Sign Up</button>
    </div>
</form>

<h2 style="color:red";>${errorMessage}</h2>

</body>
</html>