<%--
  Created by IntelliJ IDEA.
  User: guillerdalit
  Date: 8/1/20
  Time: 4:40 PM
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
</head>
<style>
    body {
        background-image: url("https://i.picsum.photos/id/307/5760/3840.jpg?hmac=XtsDQJ477ylS15H1VvpO9qCUlvrvBcDVQBu9ZUlB-SI");
        background-size: 100% 100%;
    }
</style>

<body>


<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="col-md-3 col-xl-2">

        </div>

        <div class="col-md-9 col-xl-8 py-md-3 pl-md-5 bd-content main-background">

            <form action = "/editrecipe" method = "post">

                <div>
                    <label><b><em>Recipe Name</em></b></label>
                    <%
                        HttpSession sess = request.getSession(false); //use false to use the existing session
                        String editRecipeName = (String) sess.getAttribute("editRecipeName");//this will return id anytime in the session
                        out.println("<input type=\"text\" placeholder='"+editRecipeName+"' name=\"editingRecipeName\" width =\"30\" />");
                    %>
                </div>
                <div>
                    <label><b><em>Recipe Step</em></b></label>
                    <img src="./Images/tomato.jpg" width="36px;">
                </div>
                <div>
                    <%
                        sess = request.getSession(false); //use false to use the existing session
                        String editRecipeStep = (String) sess.getAttribute("editRecipeStep");//this will return id anytime in the session
                        out.println("<textarea name=\"editingRecipeStep\" rows=\"15\" cols=\"100\" > '"+editRecipeStep+"'</textarea>");
                    %>

                </div>
                <button type = "submit" name="editButton">Edit</button>
            </form>


        </div>
        <div class="d-none d-xl-block col-xl-2 bd-toc">
        </div>
    </div>
</div>
</body>
</html>
