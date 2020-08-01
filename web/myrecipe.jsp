<%@ page import="webapp.WebHandler" %>
<%@ page import="controller.DBHandler" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/30/20
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Recipe</title>
    <link rel="icon shortcut" href="./Images/icon.png">
    <link rel="stylesheet" href="Style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</head>

<style>
    body {
        background-image: url("./Images/myRecipe-bg.jpg");
        background-size: 100% 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="./Images/icon.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
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
                <a class="nav-link" href="#">About</a>
            </li>
        </ul>
        <form action="mainpage.jsp" method="post">
            <div class="dropdown">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${currentUserName}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <button type="submit" class="dropdown-item" name="myRecipe">My Recipe</button>
                    <button type="submit" class="dropdown-item" name="following">Following</button>
                    <button type="submit" class="dropdown-item" name="logoutInDisplay">Logout</button>
                </div>
            </div>
        </form>
    </div>
</nav>
<h2 style="color:white;">My Recipe</h2>

<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="mx-auto col-xl-11 main-background">

            <!--display recipes-->
            <div class="container" style="margin-bottom: 15px; margin-top: 15px;">
                <div class="row">

                    <%
                        WebHandler webHandler = new WebHandler();
                        DBHandler dbHandler = new DBHandler();

                        /**************************************************************************
                         * Displaying My Recipe
                         **************************************************************************/
                        try {
                            Connection connection = dbHandler.startConnection();
                            Statement stmt = connection.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * FROM recipe NATURAL JOIN access");

                            int recipeID;
                            String recipeName;
                            String imagePath;
                            int index = 0;

                            while (rs.next()) {
                                recipeID = rs.getInt("recipe_id");
                                recipeName = rs.getString("recipe_name");
                                imagePath = webHandler.getImage(recipeID);

                                System.out.println("recipeID= " + recipeID);
                                System.out.println("recipeName= " + recipeName);
                                System.out.println("imagePath= " + imagePath);

                                if ((index % 4) == 0 || index == 0) {
                                    out.println("<div class=\"row\">\n");
                                }
                                out.println("<div class=\"col d-flex\" name =''" + recipeID + "'" + ">\n" +
                                        "        <div class=\"card\" style=\"width: 15rem;\">\n" +
                                        "             <img src='" + imagePath + "'" + " class=\"card-img-top\" alt=\"...\" width=\"150\" height=\"200\" >\n" +
                                        "               <div class=\"card-body\">\n" +
                                        "                   <h5 class=\"card-title\">'" + recipeName + "'</h5>\n" + "" +
                                        "                   <footer class=\"blockquote-footer text-right\" style=\"color: green\">Public</footer>" +
                                        "                </div>\n" +
                                        "        </div>\n" +
                                        "    </div>\n");
                                if ((index % 4) == 3) {
                                    out.println("</div>\n");
                                }
                                index++;
                            }
                            rs.close();
                            stmt.close();
                            connection.close();
                        }
                        catch(Exception e) {
                            System.out.println("SQLException caught: " + e.getMessage());
                        }

                        /**************************************************************************
                         * Logging OUT, NO SESSION implementation, basic logout
                         **************************************************************************/
                        if (request.getParameter("logoutInDisplay") != null){
                            response.sendRedirect("/login.jsp");

                        }
                        /**************************************************************************
                         * Show My Recipe
                         **************************************************************************/
                        if (request.getParameter("myRecipe") != null){
                            response.sendRedirect("/myrecipe.jsp");

                        }
                    %>

                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
