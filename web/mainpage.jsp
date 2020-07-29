<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="controller.DBHandler" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="webapp.WebHandler" %>
<%@ page import="webapp.Mainpage" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cooking Recipe</title>
    <link rel="icon shortcut" href="Images/icon.png">
    <link rel="stylesheet" href="Style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<!-- https://coolors.co/588b8b-ffffff-ffd5c2-f28f3b-c8553d -->

<body>
<!--navbar-->
<%--@declare id="recipelist"--%><nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="Images/icon.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
        Cooking Recipe
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li>
        </ul>
        <button class="btn btn-primary" type="button">Logout</button>
    </div>
</nav>

<div><!-- Create a method in which it will print out new recipe in the mainpage-->
    <form action="/mainpage" method="post" class="form-inline my-2 my-lg-0">
        <input input type="text" name ="recipeSearch" list="recipeList" class="form-control mr-sm-2" placeholder="Search...">
        <button class="btn btn-primary" type="submit" name="search">Search</button>
    </form>
</div>
<br>
<br>
<br>

<%  WebHandler webHandler = new WebHandler();
    DBHandler dbHandler = new DBHandler();
%>
<datalist id="recipeList">
    <%  try{
        Connection connection = dbHandler.startConnection();
        out.println("Initial entries in table \"emp\": <br/>");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT recipe_name FROM recipe");

        while (rs.next()){
            out.println("<option value='"+rs.getString("recipe_name")+"'></option>");
        }
        rs.close();
        stmt.close();
        connection.close();
    }
    catch (Exception e){
        e.printStackTrace();
    } %>
</datalist>
<%
    try {
        Connection connection = dbHandler.startConnection();
        out.println("Initial entries in table \"emp\": <br/>");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM recipe");
        int recipeID;
        String recipeName;
        String imagePath;
        int index = 0;
        out.println("<div class=\"container\" style=\"margin: 15px;>\n");
        out.println( "<div class=\"row\">\n");
        while (rs.next()) {
            recipeID = rs.getInt("recipe_id");
            recipeName = rs.getString("recipe_name");
            imagePath = webHandler.getImage(recipeID);

            if ((index % 4) == 0 || index == 0){
                out.println( "</div>\n");
                out.println( "<div class=\"row\">\n");
                out.println(index % 4);
            }
            out.println( "<div class=\"col\" name =''"+recipeID+"'" + ">\n" +
                    "        <div class=\"card\" style=\"width: 15rem;\">\n" +
                    "             <img src='"+imagePath+"'" +" class=\"card-img-top\" alt=\"...\" width=\"150\" height=\"200\" >\n" +
                    "               <div class=\"card-body\">\n" +
                    "                   <h5 class=\"card-title\">'"+recipeName+"'</h5>\n" +
                    "                   <form action=\"/mainpage\" method=\"post\">\n" +
                    "                        <button type=\"submit\" name='"+recipeID+"'" + ">GO to recipe\n" + "  " +
                    "                        <button type=\"submit\" name='"+recipeID+"'" + ">Add to Favorites\n" +
                    "                        </button>\n" +
                    "                   </form>       " +
                    "                 </div>\n" +
                    "            </div>\n" +
                    "        </div>\n");
            if ((index % 4) == 3){
                out.println(index % 4);
                out.println( "</div>\n");
            }
            index++;
        }
        out.println( "</div>\n");
        rs.close();
        stmt.close();
        connection.close();
    } catch(Exception e) {
        System.out.println("SQLException caught: " + e.getMessage());
    }
%>

</body>
</html>