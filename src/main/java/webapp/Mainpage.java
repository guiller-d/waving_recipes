package webapp;

import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


@WebServlet(name ="mainpage")
public class Mainpage extends HttpServlet {

    DBHandler dbHandler = new DBHandler();
    WebHandler webHandler = new WebHandler();

    private ArrayList<String> commentList = new ArrayList<String>();
    private ArrayList<String> usernameList = new ArrayList<String>();

    private static boolean status = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeID = 0;

        int recipeCount = webHandler.getRecipeCount();
        for (int index = 0; index < recipeCount; index++){
            if (request.getParameter(String.valueOf(index)) != null){
                recipeID = index;
                break;
            }
        }
        if (recipeID != 0){
            getRecipeComments(recipeID, request);
            displayRecipe(recipeID, response, request);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("It works");
        String text = "some text";
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(text);
    }
    public void displayRecipe(int recipeID, HttpServletResponse response, HttpServletRequest request){

        int recipePrivacy = 0;
        String recipeName = "";
        String recipeImage = "";
        String recipeStep = "";

        //get all user comments to this recipe and store them in accountList and commentList
        webHandler.setAccountComments(recipeID);
        try{

            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM recipe WHERE recipe_id='"+recipeID+"'";
            Statement statement = connection.createStatement();
            ResultSet rs_recipe = statement.executeQuery(selectSql);
            rs_recipe.first();

            recipeName = rs_recipe.getString("recipe_name");
            recipeStep = rs_recipe.getString("step");
            recipePrivacy = rs_recipe.getInt("privacy");
            recipeImage = webHandler.getImage(recipeID);

            System.out.println("RecipeID=" + recipeID);
            System.out.println("RecipeName=" + recipeName);
            System.out.println("RecipeStep=" + recipeStep);
            System.out.println("recipePrivacy" + recipePrivacy);
            System.out.println("recipeImage" + recipeImage);

            System.out.println(recipeID);
            System.out.println(recipeName);
            System.out.println(recipeStep);
            System.out.println(recipeImage);

            //available access to the next JSP
            HttpSession sess = request.getSession();
            sess.setAttribute("recipeID", recipeID);
            sess.setAttribute("recipeName", recipeName);
            sess.setAttribute("recipeStep", recipeStep);
            sess.setAttribute("recipeImage", recipeImage);
            response.sendRedirect("/displayRecipe.jsp");
        }
        catch (Exception e){

        }
    }
    public void getRecipeComments(int recipeID, HttpServletRequest request){
        HttpSession sess = request.getSession();
        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM comment WHERE recipe_id='"+recipeID+"'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectSql);

            while (rs.next()){
                commentList.add(rs.getString("text"));
                usernameList.add(rs.getString("username"));
            }
            rs.close();
            statement.close();
            connection.close();

            sess.setAttribute("commentList", commentList);
            sess.setAttribute("usernameList", usernameList);
        }
        catch (Exception e){

        }

    }

}
