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
    <link rel="icon shortcut" href="./img/icon.png">
    <link rel="stylesheet" href="Style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<style>
    body {
        background-image: url("./images/addRecipe-background.jpeg");
        background-size: 100% 100%;
    }
</style>

<body>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">


        <div class="mx-auto col-xl-8 py-md-3 pl-md-5 bd-content main-background">
            <form>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Recipe Name:</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="1"></textarea>
                </div>
                <div class="form-group">
                    <label for="exampleFormControlSelect1">Dish Type:</label>
                    <select class="form-control" id="exampleFormControlSelect1">
                        <option>unknown</option>
                        <option>main-dish</option>
                        <option>side-dish</option>
                        <option>dessert</option>
                    </select>
                </div>
                <form>
                    <div class="form-group">
                        <label for="exampleFormControlFile1">Upload your dish:</label>
                        <input type="file" class="form-control-file" id="exampleFormControlFile1">
                    </div>
                </form>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Step:</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="15"></textarea>
                </div>

                <form action="" method="post">
                    <button class="btn btn-primary" type="submit" name="10000">Upload
                    </button>
                    <button class="btn btn-primary" type="submit" name="10000">Cancel
                    </button>
                </form>


            </form>
        </div>
        <div class="d-none d-xl-block col-xl-2 bd-toc">

        </div>
    </div>

</div>


</body>
</html>
