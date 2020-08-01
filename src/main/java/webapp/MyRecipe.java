package webapp;

import controller.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;


//TODO: CHECK RECIPE AND ACCOUNT ID DIDN;T MATCH
@WebServlet (name="myrecipe")
public class MyRecipe extends HttpServlet {

    WebHandler webHandler = new WebHandler();
    DBHandler dbHandler = new DBHandler();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeCount = webHandler.getRecipeCount() + 1;
        int recipeID = 0;
        for (int index = 0; index < recipeCount; index++){

            if (request.getParameter("delete"+String.valueOf(index)) != null){
                recipeID = index;
                break;
            }

            if (request.getParameter("edit"+String.valueOf(index)) != null){
                recipeID = index;
                break;
            }
        }
        if (request.getParameter("delete" + recipeID) != null){

            try{
                Connection connection = dbHandler.startConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM access WHERE recipe_id='"+recipeID+"'");
                dbHandler.closeConnection();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try{
                Connection connection = dbHandler.startConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM recipe WHERE recipe_id='"+recipeID+"'");
                dbHandler.closeConnection();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myrecipe.jsp");
            dispatcher.forward(request,response);
        }
        if (request.getParameter("edit" + recipeID) != null){

            try{

            }
            catch (Exception e){
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editrecipe.jsp");
            dispatcher.forward(request,response);
        }





    }


}
