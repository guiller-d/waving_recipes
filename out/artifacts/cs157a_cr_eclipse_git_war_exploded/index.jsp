<%@ page import="controller.Controller" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cooking Recipe</title>
</head>
<body>
<h1>Cooking Recipe</h1>
<%
  String db = "cs157a";
  Controller controller = new Controller();
  controller.initializedDB();
  if (controller.isComplete() == true){
    System.out.println("Database Setup Completed.");
    response.sendRedirect("login.jsp");
  }
%>
<form action="/controller" method="post"/>

</body>
</html>
