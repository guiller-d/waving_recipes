package webapp;

import controller.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


//TODO: CHECK RECIPE AND ACCOUNT ID DIDN;T MATCH
@WebServlet(name="myrecipe")
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

                HttpSession sess = request.getSession();

                Connection connection = dbHandler.startConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM recipe WHERE recipe_id='"+recipeID+"'");
                rs.next();

                sess.setAttribute("editRecipeName", rs.getString("recipe_name"));
                sess.setAttribute("editRecipeStep", rs.getString("step"));
                sess.setAttribute("editRecipeID", recipeID);
                rs.close();
                stmt.close();
                connection.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            response.sendRedirect("/editrecipe.jsp");
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editrecipe.jsp");
            //dispatcher.forward(request,response);
        }





    }


}
