package webapp;

import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


@WebServlet(name ="mainpage")
public class Mainpage extends HttpServlet {

    private DBHandler dbHandler = new DBHandler();

    private  int recipeID;
    private  String recipeName;
    private  String recipeStep;
    private  int recipePrivacy;
    private ArrayList<Integer> accountIDList = new ArrayList<Integer>();


    private static boolean status = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("In here");
        PrintWriter out = response.getWriter();
        if (request.getParameter("10000") != null){
            response.sendRedirect("/displayRecipe.jsp");
        }

        HttpSession sess = request.getSession();
        sess.setAttribute("recipeID", 10000);
        /*
        setRecipeInfo(10000);
        String recipeName = "recipeName";
        String recipeNameMessage = getRecipeName();
        request.setAttribute(recipeName, recipeNameMessage);
        String recipeStep = "recipeStep";
        String recipeStepMessage = getRecipeStep();
        request.setAttribute(recipeStep, recipeStepMessage);
        setComments(10000);
*/
        //request.getRequestDispatcher("/displayRecipe.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void setImage(int recipe_id, HttpServletRequest request){
        try{
            Part part = request.getPart("image");
            InputStream inputStream = part.getInputStream();
        }
        catch (Exception e){

        }
    }
    public void setComments(int recipe_id){
        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM comment WHERE recipe_id='"+recipe_id+"'";
            Statement statement = connection.createStatement();
            ResultSet rs_comment = statement.executeQuery(selectSql);
            while(rs_comment.next()){


                /*System.out.println(rs_comment.getInt("account_id"));
                System.out.println(rs_comment.getInt("recipe_id"));
                System.out.println(rs_comment.getString("username"));
                System.out.println(rs_comment.getString("text"));
                System.out.println(rs_comment.getDate("date_posted"));
                accountIDList.add(rs_comment.getInt("account_id"));
                System.out.println(rs_comment.getInt("account_id"));*/
            }
            rs_comment.close();
            statement.close();
            dbHandler.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }

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
            dbHandler.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
    public  void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    public  void setRecipeStep(String recipeStep) {

        this.recipeStep = recipeStep;
    }
    public  void setRecipePrivacy(int recipePrivacy) {

        this.recipePrivacy = recipePrivacy;
    }


    public  int getRecipeID(){
        return recipeID;
    }
    public  String getRecipeName(){
        return recipeName;
    }
    public  int getRecipePrivacy() {
        return recipePrivacy;
    }
    public  String getRecipeStep() {
        return recipeStep;
    }

}
