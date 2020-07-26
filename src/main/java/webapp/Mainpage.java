package webapp;

import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet(name ="mainpage")
public class Mainpage extends HttpServlet {

    private DBHandler dbHandler = new DBHandler();

    private static int recipeID;
    private static String recipeName;
    private static String recipeStep;
    private static int recipePrivacy;


    private static boolean status = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("In here");
        PrintWriter out = response.getWriter();
        if (request.getParameter("10000") != null){
            setRecipeInfo(10000);
            response.sendRedirect("displayRecipe.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void setRecipeInfo(int recipe_id){
        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM recipe WHERE recipe_id='"+recipe_id+"'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectSql);
            rs.first();
            setRecipeID(rs.getInt("recipe_id"));
            setRecipeName(rs.getString("recipe_name"));
            setRecipeStep(rs.getString("step"));
            setRecipePrivacy(rs.getInt("privacy"));
            status = true;
            rs.close();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void setRecipeID(int recipeID) {
        Mainpage.recipeID = recipeID;
    }
    public static void setRecipeName(String recipeName) {
        Mainpage.recipeName = recipeName;
    }
    public static void setRecipeStep(String recipeStep) {
        Mainpage.recipeStep = recipeStep;
    }
    public static void setRecipePrivacy(int recipePrivacy) {
        Mainpage.recipePrivacy = recipePrivacy;
    }


    public static int getRecipeID(){
        return recipeID;
    }
    public static String getRecipeName(){
        return recipeName;
    }
    public static int getRecipePrivacy() {
        return recipePrivacy;
    }
    public static String getRecipeStep() {
        return recipeStep;
    }

}
