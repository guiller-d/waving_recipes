<%@ page import="webapp.Mainpage" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="webapp.DisplayRecipe" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display my recipe</title>
</head>
<body>

<form action=\"/mainpage\" method=\"post\">
    <button type="submit" name="addToFav">Add to Favorites</button>
    </button>
</form>

<img src=${recipeImage} class="card-img-top" alt="..." width="250" height="250" >

<h1 style="color:red";>${recipeImage}</h1>
<h1 style="color:red";>${recipeID}</h1>
<h2 style="color:red";>${recipeName}</h2>
<h1 style="color:red";>${recipeStep}</h1>
<h1 style="color:red";>${commentList[0]} </h1>
<h1 style="color:red";>${commentList[1]} </h1>
<br>

<br>
<br>


<c:forEach items="${commentList}" var="item">
    ${item}<br>
</c:forEach>

</body>
</html>
