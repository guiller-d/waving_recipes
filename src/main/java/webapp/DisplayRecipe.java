package webapp;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@WebServlet(name="displayRecipe")
public class DisplayRecipe extends HttpServlet{

    private  static int recipeID;
    private  static String recipeName;
    private  static String recipeStep;
    private String recipeImage;

    private ArrayList<String> commentList = new ArrayList<String>();
    private ArrayList<String> usernameList = new ArrayList<String>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sess = request.getSession(false); //use false to use the existing session
        recipeID = (int) sess.getAttribute("recipeID");//this will return username anytime in the session
        recipeName = String.valueOf(sess.getAttribute("recipeName"));
        recipeStep = String.valueOf(sess.getAttribute("recipeStep"));
        recipeImage = String.valueOf(sess.getAttribute("recipeImage"));
        commentList = (ArrayList<String>) sess.getAttribute("commentList");
        usernameList = (ArrayList<String>) sess.getAttribute("usernameList");


        String recipe_id = "recipeID";
        String recipe_name = "recipeName";
        String recipe_step = "recipeStep";
        String recipe_image = "recipeImage";
        String comment_list = "commentList";
        String username_list = "usernameList";

        request.setAttribute(recipe_id, recipeID);
        request.setAttribute(recipe_name, recipeName);
        request.setAttribute(recipe_step, recipeStep);
        request.setAttribute(recipe_image, recipeImage);
        request.setAttribute(comment_list, commentList);
        request.setAttribute(username_list, usernameList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayRecipe.jsp");
        dispatcher.forward(request,response);
        //request.getRequestDispatcher("/displayRecipe.jsp").forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void getRecipe(){
        System.out.println("getting recipe");
    }
}
