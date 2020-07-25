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
    private static String ingredients;
    private static int privacy;
    private static boolean status = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("In here");
        PrintWriter out = response.getWriter();

        String recipeID = request.getParameter("10000");

        out.println( "<h1>" + "In mainpage." + "</h1>");
        out.println( "<h1>" + "In mainpage." + "</h1>");
        out.println( "<h1>" + "In mainpage." + "</h1>");
        out.println( "<h1>" + "In mainpage." + "</h1>");

        try{
            getRecipe(10000, out);
            response.sendRedirect("displayRecipe.jsp");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void getRecipe(int recipeID, PrintWriter out) throws Exception{
        Connection connection = dbHandler.startConnection();
        /*
         * TODO: if (recipeID == recipeTable.recipe_id),
         *  if it's in the recipe table, then we get the recipe in the Ingredient Table using the recipID
         **/
        String selectSql = "SELECT * FROM recipe WHERE recipe_id='"+recipeID+"'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectSql);
        rs.first();

        recipeID = rs.getInt("recipe_id");
        recipeName = rs.getString("recipe_name");
        recipeStep = rs.getString("step");
        privacy = rs.getInt("privacy");
        status = true;
        rs.close();
        statement.close();
        out.println( "<h1>" + recipeID + "</h1>");

    }
    public static int getRecipeID() {
        return recipeID;
    }
    public static String getRecipeName() {
        return recipeName;
    }
    public static String getStep() {
        return recipeStep;
    }
    public static String getIngredients() {
        return ingredients;
    }

}
