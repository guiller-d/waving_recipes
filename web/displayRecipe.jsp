<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="controller.DBHandler" %>
<%@ page import="webapp.WebHandler" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 7/24/20
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Recipe</title>
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
        background-image: url("./images/displayRecipe-bg.jpg");
        background-size: 100% 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
</style>

<body>

<div class="container-fluid">
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
    <div class="row flex-xl-nowrap">


        <div class="col-md-9 col-xl-8 py-md-3 pl-md-5 bd-content main-background">
            <form action=\"/mainpage\" method=\"post\">
                <button class="btn btn-primary" type="submit" name="addToFav">Add to Favorites</button>
                </button>
            </form>

            <form>
                <div class="text-center">
                    <img src="${recipeImage}" class="rounded" alt="..." width="150" height="200">
                </div>
                <div class="form-group">
                    <label> Recipe Name: </label> ${recipeName}
                </div>

                <div class="form-group">
                    <label>Dish Type:</label> ${ingredients}

                </div>
                <div class="form-group">
                    <label>Ingredients:</label> ${ingredients}

                </div>
                <div class="form-group">
                    <label>Step:</label> ${recipeStep}
                </div>



            </form>
        </div>
        <div class="col-md-3 col-xl-3 comment-background">
            <div class="card w-100">
                <form action="displayRecipe.jsp" method="post" class="form-inline my-2 my-lg-0">
                    <div class="card-body">
                        <h5 class="card-title">Add comment:</h5>
                        <input input type="text" name ="addingComment" class="form-control" placeholder="Delecious...">
                        <small class="form-text text-muted">Tell us how you feel</small>
                        <div class="text-right">
                            <button type="submit pull-right" class="btn btn-primary" name="addComment">Submit</button>
                        </div>
                    </div>
            </div>


            <%
                DBHandler dbHandler = new DBHandler();
                HttpSession sess = request.getSession(false); //use false to use the existing session

                /**************************************************************************
                 * Getting Comments and Username
                 **************************************************************************/
                ArrayList<String> commentList =  new  ArrayList<String>();
                ArrayList<String> usernameList =  new  ArrayList<String>();
                ArrayList<String> datePostedList = new ArrayList<String>();
                int recipeID = (int) sess.getAttribute("recipeID");//this will return username anytime in the session
                try{
                    Connection connection = dbHandler.startConnection();
                    String selectSql = "SELECT * FROM comment WHERE recipe_id='"+recipeID+"'";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(selectSql);

                    while (rs.next()){
                        commentList.add(rs.getString("text"));
                        usernameList.add(rs.getString("username"));
                        datePostedList.add(String.valueOf(rs.getDate("date_posted")));
                    }
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                /**************************************************************************
                 * Getting Ingredients
                 **************************************************************************/
                String ingredients = "";
                try{

                    Connection connection = dbHandler.startConnection();
                    String selectSql = "SELECT * FROM ingredient WHERE recipe_id='"+recipeID+"'";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(selectSql);
                    while (rs.next()){
                        ingredients = rs.getString("ingredient");
                        sess.setAttribute("ingredients", ingredients);
                    }
                    rs.close();
                    statement.close();
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                /**************************************************************************
                 * Getting Food Type
                 **************************************************************************/
                String foodType =  "";
                try{

                    Connection connection = dbHandler.startConnection();
                    String selectSql = "SELECT * FROM food_type WHERE recipe_id='"+recipeID+"'";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(selectSql);
                    while (rs.next()){
                        foodType = rs.getString("ingredient");
                        sess.setAttribute("ingredients", ingredients);
                    }
                    rs.close();
                    statement.close();
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                /**************************************************************************
                 * Printing Recipe's Comments with Username and the Date Posted
                 **************************************************************************/
                for (int index = 0 ; index < commentList.size(); index++ ){

                    out.println("<div class=\"card w-100\">\n" +
                            "       <div class=\"card-body\">\n" +
                            "           <h5 class=\"card-title\">'"+usernameList.get(index)+"'</h5>\n" +
                            "           <p class=\"card-text\">'"+commentList.get(index)+"'</p>\n" +
                            "           <footer class=\"blockquote-footer text-right\">'"+commentList.get(index)+"'</footer>\n" +
                            "       </div>\n" +
                            "    </div>");
                }
                /**************************************************************************
                 * Adding Comment
                 **************************************************************************/
                if (request.getParameter("addComment") != null){
                    WebHandler webHandler = new WebHandler();
                    sess = request.getSession(false); //use false to use the existing session
                    int currentUserID = (int) sess.getAttribute("currentUserID");//this will return username anytime in the session
                    String currentUserName = (String) sess.getAttribute("currentUserName");
                    String comment = request.getParameter("addingComment");
                    Date date = webHandler.getDateToday();

                    try{
                        Connection connection = dbHandler.startConnection();
                        dbHandler.insertCommentToDB(currentUserID, recipeID, currentUserName, comment, String.valueOf(date));
                        connection.close();
                        response.sendRedirect("/displayRecipe.jsp");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                /**************************************************************************
                 * Logging OUT, NO SESSION implementation, basic logout
                 **************************************************************************/
                if (request.getParameter("logoutInDisplay") != null){
                    response.sendRedirect("/login.jsp");
                }


            %>

        </div>
    </div>

</div>


</body>
</html>




<%--
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Recipe</title>
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
        background-image: url("login.jpg.jpeg");
        background-size: 100% 100%;
    }
</style>

<body>

<div class="container-fluid">
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
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                    </li>
                </ul>
            <form action="displayRecipe.jsp" method="post">
                <div class="dropdown">
                    <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Username
                    </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button type="submit pull-right" class="btn btn-primary" name="myRecipe">My Recipe</button>
                            <button type="submit pull-right" class="btn btn-primary" name="following">Following</button>
                            <button type="submit pull-right" class="btn btn-primary" name="logoutInDisplay">Logout</button>
                        </div>
                </div>
            </form>
        </div>
    </nav>
    <div class="row flex-xl-nowrap">

        <div class="col-xl-8 main-background">
            <form action=\"/mainpage\" method=\"post\">
                <button class="btn btn-primary" type="submit" name="addToFav">Add to Favorites</button>
                </button>
            </form>
            <form>
                <div class="text-center">
                    <img src="${recipeImage}" class="rounded" alt="..." width="150" height="200">
                </div>
                <div class="form-group">
                    <label>Recipe Name:
                        <h3> ${recipeName}</h3>
                    </label>
                </div>
                <div class="form-group">
                    <label>Ingredients:
                        <h5> ${recipeID}</h5>
                    </label>

                </div>

                <div class="form-group">
                    <label>Dish Type:

                    </label>
                </div>
                <div class="form-group">
                    <label>Ingredients:${ingredients}</label>

                </div>
                <div class="form-group">
                    <label>Step:
                        <h3>${recipeStep}</h3>
                    </label>
                </div>

            </form>
        </div>
        <div class="col-md-3 col-xl-3 comment-background">
            <div class="card w-100">
                <form action="displayRecipe.jsp" method="post" class="form-inline my-2 my-lg-0">
                    <div class="card-body">
                        <h5 class="card-title">Add comment:</h5>
                        <input input type="text" name ="addingComment" class="form-control" placeholder="Delecious...">
                        <small class="form-text text-muted">Tell us how you feel</small>
                        <div class="text-right">
                            <button type="submit pull-right" class="btn btn-primary" name="addComment">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
            <%
                HttpSession sess = request.getSession(false); //use false to use the existing session
                DBHandler dbHandler = new DBHandler();

                /**************************************************************************
                 * Printing Comments and Username
                 **************************************************************************/
                ArrayList<String> commentList =  new  ArrayList<String>();
                ArrayList<String> usernameList =  new  ArrayList<String>();
                ArrayList<String> datePostedList = new ArrayList<String>();
                int recipeID = (int) sess.getAttribute("recipeID");//this will return username anytime in the session

                try{
                    Connection connection = dbHandler.startConnection();
                    String selectSql = "SELECT * FROM comment WHERE recipe_id='"+recipeID+"'";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(selectSql);

                    while (rs.next()){
                        commentList.add(rs.getString("text"));
                        usernameList.add(rs.getString("username"));
                        datePostedList.add(String.valueOf(rs.getDate("date_posted")));
                    }
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                for (int index = 0 ; index < commentList.size(); index++ ){

                    out.println(" <div class=\"card w-100\">\n" +
                            "                <div class=\"card-body\">\n" +
                            "                    <h5 class=\"card-title\">'"+usernameList.get(index)+"'</h5>\n" +
                            "                    <p class=\"card-text\">'"+commentList.get(index)+"'</p>\n" +
                            "                    <footer class=\"blockquote-footer text-right\">'"+commentList.get(index)+"'</footer>\n" +
                            "                </div>\n" +
                            "            </div>");

                }
                /**************************************************************************
                 * Adding Comment
                 **************************************************************************/
                if (request.getParameter("addComment") != null){
                    WebHandler webHandler = new WebHandler();
                    sess = request.getSession(false); //use false to use the existing session
                    int currentUserID = (int) sess.getAttribute("currentUserID");//this will return username anytime in the session
                    String currentUserName = (String) sess.getAttribute("currentUserName");
                    String comment = request.getParameter("addingComment");
                    Date date = webHandler.getDateToday();

                    try{
                        Connection connection = dbHandler.startConnection();
                        dbHandler.insertCommentToDB(currentUserID, recipeID, currentUserName, comment, String.valueOf(date));
                        connection.close();
                        response.sendRedirect("/displayRecipe.jsp");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                /**************************************************************************
                 * Logging OUT, NO SESSION implementation, basic logout
                 **************************************************************************/
                if (request.getParameter("logoutInDisplay") != null){
                    response.sendRedirect("/login.jsp");
                }
            %>
        </div>
    </div>
</div>


</body>
</html>

<%--

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
--%>
