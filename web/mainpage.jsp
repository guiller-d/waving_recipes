<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="controller.DBHandler" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="webapp.WebHandler" %>
<%@ page import="webapp.Mainpage" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: Guiller Dalit and Mina Lee
  Date: 7/24/20
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Cooking Recipe</title>
    <link rel="icon shortcut" href="./Images/icon.PNG">
    <link rel="stylesheet" href="Style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<!-- https://coolors.co/588b8b-ffffff-ffd5c2-f28f3b-c8553d -->
<style>
    body {
        background-image: url("Images/main-background.jpg");
        background-size: 100% 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
</style>
<body>
<!--navbar-->
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
                <div style="margin-left: 15px;"><!-- Create a method in which it will print out new recipe in the mainpage-->
                    <form action="mainpage.jsp" method="post" class="form-inline my-2 my-lg-0">
                        <%--@declare id="recipelist"--%><input input type="text" name ="recipeSearch" list="recipeList" class="form-control mr-sm-2" placeholder="Search...">
                        <button class="btn btn-primary" type="submit" name="search">Search</button>
                    </form>
                </div>
            </li>
        </ul>
        <form action="mainpage.jsp" method="post">
            <div class="dropdown">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${currentUserName}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <button type="submit" class="dropdown-item" name="home">Home</button>
                    <button type="submit" class="dropdown-item" name="myRecipe">My Recipe</button>
                    <button type="submit" class="dropdown-item" name="myFavorites">Favorites</button>
                    <button type="submit" class="dropdown-item" name="following">Followers</button>
                    <button type="submit" class="dropdown-item" name="logoutInDisplay">Logout</button>
                </div>
            </div>
        </form>
    </div>
</nav>



<%  WebHandler webHandler = new WebHandler();
    DBHandler dbHandler = new DBHandler();


%>
<datalist id="recipeList">
    <%
        /**************************************************************************
         * Autocomplete search
         **************************************************************************/
        try{
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
        }
        /**************************************************************************
         * get My Recipe
         **************************************************************************/
        ArrayList<Integer> myFavList = new ArrayList<Integer>();
        try {
            HttpSession sess = request.getSession(false); //use false to use the existing session
            int currentUserID = (int) sess.getAttribute("currentUserID");//this will return id anytime in the session
            Connection connection = dbHandler.startConnection();
            Statement statement = connection.createStatement();
            ResultSet rs_myrecipe = statement.executeQuery("SELECT * FROM recipe NATURAL JOIN myrecipe WHERE account_id='"+currentUserID+"'");
            while(rs_myrecipe.next()){
                //rs_myrecipe.getInt("account_id");
                myFavList.add(rs_myrecipe.getInt("recipe_id"));

            }
            rs_myrecipe.close();
            statement.close();
            connection.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    %>
</datalist>

<!--display recipes-->
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="mx-auto col-xl-11 main-background">
            <!--display recipes-->
            <div class="container" style="margin-bottom: 15px; margin-top: 15px;">
                <div class="row">
                    <%
                        /**************************************************************************
                         * getting recipe owners
                         **************************************************************************/
                        ArrayList <Integer> recipeOwners = new ArrayList <Integer>();
                        ArrayList <Integer> uploadedRecipes = new ArrayList <Integer>();

                        try{
                            Connection connection = dbHandler.startConnection();
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery("SELECT * FROM recipe NATURAL JOIN access");

                            while (rs.next()){
                                recipeOwners.add(rs.getInt("account_id"));
                                uploadedRecipes.add(rs.getInt("recipe_id"));
                            }

                            rs.close();
                            statement.close();
                            connection.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                        ArrayList <Integer>  myFavoriteList = new ArrayList<Integer>();
                        HttpSession sess = request.getSession(false); //use false to use the existing session
                        String currentUser = (String) sess.getAttribute("currentUserName");//this will return username anytime in the session
                        int currentUserID = (int) sess.getAttribute("currentUserID");//this will return id anytime in the session
                        /**************************************************************************
                         * get all my favorites
                         **************************************************************************/
                        try{
                            Connection connection = dbHandler.startConnection();
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery("SELECT * FROM recipe NATURAL JOIN myfavorite WHERE account_id='"+currentUserID+"'");

                            while (rs.next()){
                                myFavoriteList.add(rs.getInt("recipe_id"));
                            }
                            rs.close();
                            statement.close();
                            connection.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        sess = request.getSession();
                        sess.setAttribute("myFavoriteList", myFavoriteList);

                        /**************************************************************************
                         * getting owners username
                         **************************************************************************/
                        ArrayList <String> OwnersUsernames = new ArrayList <String>();

                        try{
                            Connection connection = dbHandler.startConnection();
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery("SELECT * FROM account");

                            int accout_id = 0;
                            String username = "";

                            while (rs.next()){
                                accout_id = rs.getInt("account_id");
                                username = rs.getString("username");
                                for (int j = 0; j <  recipeOwners.size(); j++){
                                    if (recipeOwners.get(j) == accout_id){
                                        OwnersUsernames.add(username);
                                    }
                                }
                            }
                            rs.close();
                            statement.close();
                            connection.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                        /**************************************************************************
                         * Displaying Recipe
                         **************************************************************************/
                        try {
                            Connection connection = dbHandler.startConnection();
                            Statement stmt = connection.createStatement();
                            ResultSet rs;
                            //display search
                            if (request.getParameter("search") != null){
                                String recipeInSearch = request.getParameter("recipeSearch");
                                recipeInSearch = "%" + recipeInSearch + "%";
                                rs = stmt.executeQuery("SELECT * FROM recipe WHERE recipe_name LIKE '"+recipeInSearch+"'");
                            }//display deafult
                            else{
                                rs = stmt.executeQuery("SELECT * FROM recipe");
                            }
                            int recipeID;
                            String recipeName;
                            String imagePath;
                            int index = 0;
                            String gotoRecipe = "";
                            String addToFavorites = "";
                            String unAddToFavorites = "";

                            while (rs.next()) {
                                recipeID = rs.getInt("recipe_id");
                                recipeName = rs.getString("recipe_name");
                                imagePath = webHandler.getImage(recipeID);
                                if ((index % 4) == 0 || index == 0){
                                    out.println( "<div class=\"row\">\n");
                                }
                                out.println( "<div class=\"col d-flex\" name =''"+recipeID+"'" + ">\n" +
                                        "        <div class=\"card\" style=\"width: 15rem;\">\n" +
                                        "             <img src='"+imagePath+"'" +" class=\"card-img-top\" alt=\"...\" width=\"150\" height=\"200\" >\n" +
                                        "                 <div class=\"card-body\">\n" +
                                        "                     <h5 class=\"card-title\">'"+recipeName+"'</h5>\n" + "");
                                for (int i = 0; i < uploadedRecipes.size(); i++){
                                    if (uploadedRecipes.get(i) == recipeID){
                                        out.println("<footer class=\"blockquote-footer text-right\">'"+OwnersUsernames.get(i)+"'</footer>\n");
                                        break;
                                    }
                                }
                                gotoRecipe = "gotoRecipe"+recipeID;
                                addToFavorites = "addToFavorites"+recipeID;
                                unAddToFavorites = "unAddToFavorites"+recipeID;

                                out.println("<form action=\"/mainpage\" method=\"post\">\n" +
                                        "        <button class=\"btn btn-primary\" type=\"submit\" name='"+gotoRecipe+"'" + ">GO to recipe\n" + "  ");
                                if (myFavoriteList.contains(recipeID)){
                                    //out.println("<img onclick=\"\" class=\"fav-icon\"  name='"+unAddToFavorites+"'\" title=\"Add to favorite\" src=\"./Images/heart-filled-red.png\">");
                                    out.println("<button class=\"btn btn-primary\" type=\"submit\" name='"+unAddToFavorites+"'" + ">Is My Favorite\n");
                                }else{
                                    //out.println("<img onclick=\"\" class=\"fav-icon\" title=\"Add to favorite\" name='"+addToFavorites+"'\" src=\"./Images/heart-empty-black.png\">");
                                    out.println("<button class=\"btn btn-primary\" type=\"submit\" name='"+addToFavorites+"'" + ">Add to Favorites\n");
                                }
                                out.println("</button>\n" +
                                        "                   </form>       " +
                                        "                 </div>\n" +
                                        "            </div>\n" +
                                        "        </div>\n");
                                if ((index % 4) == 3){
                                    out.println( "</div>\n");
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
                         * Go back to mainpage
                         **************************************************************************/
                        if (request.getParameter("home") != null){
                            response.sendRedirect("/mainpage.jsp");
                        }
                        /**************************************************************************
                         * Logging OUT, NO SESSION implementation, basic logout
                         **************************************************************************/
                        if (request.getParameter("logoutInDisplay") != null){
                            response.sendRedirect("/login.jsp");
                            //request.getRequestDispatcher("/login.jsp").forward(request, response);
                        }
                        /**************************************************************************
                         * Show My Recipe
                         **************************************************************************/
                        if (request.getParameter("myRecipe") != null){
                            response.sendRedirect("/myrecipe.jsp");
                            //request.getRequestDispatcher("/myrecipe.jsp").forward(request, response);
                        }

                        /**************************************************************************
                         * Show My Recipe
                         **************************************************************************/
                        if (request.getParameter("myFavorites") != null){
                            response.sendRedirect("/myfavorite.jsp");
                            //request.getRequestDispatcher("/myrecipe.jsp").forward(request, response);
                        }
                        /**************************************************************************
                         * Add Recipe
                         **************************************************************************/
                        if (request.getParameter("addRecipe") != null){
                            response.sendRedirect("/addRecipe.jsp");
                            //request.getRequestDispatcher("/addRecipe.jsp").forward(request, response);
                        }
                        /**************************************************************************
                         * go tp followers
                         **************************************************************************/
                        if (request.getParameter("following") != null){
                            response.sendRedirect("/follower.jsp");
                            //request.getRequestDispatcher("/addRecipe.jsp").forward(request, response);
                        }
                    %>
                </div>
            </div>
        </div>

        <a href="/addRecipe.jsp"><img class="add-recipe-btn" title="Add new recipe" src="./Images/plus.png"></a>

    </div>

</body>
</html>

