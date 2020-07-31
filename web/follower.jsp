<%--
  Created by IntelliJ IDEA.
  User: mina8
  Date: 7/31/2020
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Follower</title>
    <link rel="icon shortcut" href="./img/icon.png">
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
        background-image: url("./images/follower-bg.jpg");
        background-size: 100% 100%;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="./img/icon.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
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
        <div class="dropdown">
            <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Username
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">My Recipe</a>
                <a class="dropdown-item" href="#">Following</a>
                <a class="dropdown-item" href="#">Logout</a>
            </div>
        </div>
    </div>
</nav>
<h2>Followers</h2>

<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="mx-auto col-xl-11 main-background">

            <%
                /**************************************************************************
                 * Displaying Followers
                 **************************************************************************/
                    Connection connection = dbHandler.startConnection();
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM account natural join following where follow_id = "+ );

                    int recipeID;
                    String recipeName;
                    String imagePath;
                    int index = 0;
                    while (rs.next()) {
                        recipeID = rs.getInt("recipe_id");
                        recipeName = rs.getString("recipe_name");
                        imagePath = webHandler.getImage(recipeID);

                        if ((index % 4) == 0 || index == 0){
                            out.println( "<div class=\"row\">\n");
                        }
                        out.println( "<div class=\"col\" name =''"+recipeID+"'" + ">\n" +
                                "        <div class=\"card\" style=\"width: 15rem;\">\n" +
                                "             <img src='"+imagePath+"'" +" class=\"card-img-top\" alt=\"...\" width=\"150\" height=\"200\" >\n" +
                                "               <div class=\"card-body\">\n" +
                                "                   <h5 class=\"card-title\">'"+recipeName+"'</h5>\n" + "" +
                                "            <footer class=\"blockquote-footer text-right\">'"+recipeName+"'</footer>\n" +
                                "                   <form action=\"/mainpage\" method=\"post\">\n" +
                                "                        <button class=\"btn btn-primary\" type=\"submit\" name='"+recipeID+"'" + ">GO to recipe\n" + "  " +
                                "                        <button class=\"btn btn-primary\" type=\"submit\" name='"+recipeID+"'" + ">Add to Favorites\n" +
                                "                        </button>\n" +
                                "                   </form>       " +
                                "                 </div>\n" +
                                "            </div>\n" +
                                "        </div>\n");
                        if ((index % 4) == 3){
                            out.println( "</div>\n");
                        }
                        index++;
                    }

                    out.println( "</div>\n");
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
            %>
            <!--display followers-->
            <div style="margin: 30px;">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Follower Username
                        <button type="button" class="btn btn-primary">Check Recipes</button>
                    </li>
                    <li class="list-group-item">Follower Username
                        <button type="button" class="btn btn-primary">Check Recipes</button>
                    </li>
                    <li class="list-group-item">Follower Username
                        <button type="button" class="btn btn-primary">Check Recipes</button>
                    </li>
                    <li class="list-group-item">Follower Username
                        <button type="button" class="btn btn-primary">Check Recipes</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</div>

</body>
</html>

