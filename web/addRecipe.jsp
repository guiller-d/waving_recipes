<%--
  Created by IntelliJ IDEA.
  User: mina8
  Date: 7/25/2020
  Time: 11:36 PM
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

            <form action = "/addrecipe" method = "post" enctype = "multipart/form-data">

                <div>
                    <label><b><em>Recipe Name</em></b></label>
                    <input type="text" placeholder="Enter Recipe Name" name="addRecipeName" required>
                </div>

                <div>
                    <label><b><em>Recipe Type</em></b></label>
                    <select name="addFoodType">
                        <option>unknown</option>
                        <option>main-dish</option>
                        <option>side-dish</option>
                        <option>dessert</option>
                    </select>
                </div>

                <div>
                    <h3> Choose File to Upload in Server </h3>
                    <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
                </div>

                <div>
                    <label><b><em>Recipe Step</em></b></label>
                    <img src="./Images/tomato.jpg" width="36px;">
                </div>
                <div>
                    <textarea name="addRecipeStep" rows="15" cols="100"></textarea>
                </div>

                <input type="submit" value="Upload Recipe" class="btn btn-primary"/>
            </form>
        </div>
        <div class="d-none d-xl-block col-xl-2 bd-toc">
        </div>
    </div>
</div>
</body>
</html>
