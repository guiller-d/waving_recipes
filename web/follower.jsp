<%@ page import="controller.DBHandler" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="webapp.WebHandler" %><%--
  Created by IntelliJ IDEA.
  User: Guiller Dalit and Mina Lee
  Date: 7/31/20
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Follower</title>
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
        background-image: url("./Images/follower-bg.jpg");
        background-size: 100% 100%;
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
            <!--display followers-->
            <div style="margin: 30px;">
                <ul class="list-group list-group-flush">
                    <%
                        DBHandler dbHandler = new DBHandler();
                        WebHandler webHandler = new WebHandler();
                        HttpSession sess = request.getSession(false); //use false to use the existing session
                        String currentUser = (String) sess.getAttribute("currentUserName");//this will return username anytime in the session
                        int currentUserID = (int) sess.getAttribute("currentUserID");//this will return id anytime in the session
                        ArrayList<Integer> followerIDList = new  ArrayList<Integer>();
                        ArrayList<String> followerNameList = new  ArrayList<String>();
                        try{
                            Connection connection = dbHandler.startConnection();
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery("SELECT a.account_id, a.username FROM account a JOIN following f ON f.follower_id = a.account_id WHERE f.account_id='"+currentUserID+"'");

                            while (rs.next()){
                                followerIDList.add(rs.getInt("account_id"));
                                followerNameList.add(rs.getString("username"));
                            }
                            rs.close();
                            statement.close();
                            connection.close();
                            for (int index = 0; index <followerIDList.size(); index++ ){
                                System.out.print(followerNameList.get(index) + " "+ followerIDList.get(index));

                                out.println("<li class=\"list-group-item\">\n" +
                                        "      <form action=\"/follower\" method=\"post\">\n" +
                                        "           <label>'"+followerNameList.get(index)+"'</label>\n" +
                                        "                 <button style=\"float: right;\" type=\"submit\"  name='"+followerIDList.get(index)+"'\" class=\"btn btn-primary\">Check Recipes</button>\n" +
                                        "           </form>\n" +
                                        "    </li>");

                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    %>

                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>

