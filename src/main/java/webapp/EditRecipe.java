package webapp;


import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "editrecipe")
public class EditRecipe extends HttpServlet {

    DBHandler dbHandler = new DBHandler();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sess = request.getSession(false); //use false to use the existing session
        int recipeID = (int) sess.getAttribute("editRecipeID");//this will return id anytime in the session
        String newRecipeName = request.getParameter("editingRecipeName");
        String newRecipeStep = request.getParameter("editingRecipeStep");

        if (request.getParameter("editButton") != null){

            try{
                Connection connection = dbHandler.startConnection();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE recipe SET recipe_name='"+newRecipeName+"' WHERE recipe_id='"+recipeID+"'");
                stmt.close();
                dbHandler.closeConnection();
                System.out.println("Recipe Name updated successfully ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                Connection connection = dbHandler.startConnection();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE recipe SET step='"+newRecipeStep+"' WHERE recipe_id='"+recipeID+"'");
                System.out.println("Recipe Step updated successfully ");
                stmt.close();
                dbHandler.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         response.sendRedirect("/myrecipe.jsp");

    }


}