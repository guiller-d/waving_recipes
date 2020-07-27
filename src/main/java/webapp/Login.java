package webapp;

import controller.Controller;
import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name="login")
public class Login extends HttpServlet {

    DBHandler dbHandler = new DBHandler();
    Controller controller = new Controller();
    private static int accountID_DB = 0;
    private static String username_DB = "";
    private static String password_DB = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("login") != null){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try{
                controller.initializedDB();
                if (controller.isComplete() == true){

                    if (request.getParameter("register") != null){
                        response.sendRedirect("/register.jsp");
                        //request.getRequestDispatcher("/register.jsp").forward(request, response);
                    }
                    if (request.getParameter("login") != null){
                        if (validateUsername(username) == true && validatePassword(password) == true){
                            System.out.println("Username exist");
                            response.sendRedirect("/mainpage.jsp");
                            //request.getRequestDispatcher("/mainpage.jsp").forward(request, response);
                        }
                        else{
                            // Wrong password notification
                            String errorMessage = "errorMessage";
                            String error = "Invalid Credentials";
                            request.setAttribute(errorMessage, error);
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        }
                    }
                }
                else{
                    System.out.println("Database failed to access");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    public void  register (String username, String password){
        //  INSERT values ...
    }
    public boolean validateUsername(String username){

        boolean isValidated = false;

        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM account WHERE username='"+username+"'";
            Statement statement = connection.createStatement();
            ResultSet rs_account = statement.executeQuery(selectSql);

            if (rs_account.isBeforeFirst() == false) {
                System.out.println("No username found in the database");
                System.out.println("In here");
            }
            else{
                rs_account.first();
                username_DB = rs_account.getString("username");
                accountID_DB = rs_account.getInt("account_id");
                if (username.compareTo(username_DB) == 0){
                    isValidated = true;
                }
                statement.close();
                rs_account.close();
            }
            dbHandler.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return isValidated;
    }

    public boolean validatePassword(String password)  {

        boolean isValidated = false;

        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM password WHERE account_id='"+accountID_DB+"'";
            Statement statement = connection.createStatement();
            ResultSet rs_password = statement.executeQuery(selectSql);

            if (rs_password.isBeforeFirst() == false) {
                System.out.println("No password found in the database");
            }
            else{
                rs_password.first();
                password_DB = rs_password.getString("password");
                if (password.compareTo(password_DB) == 0){
                    isValidated = true;
                }
                statement.close();
                rs_password.close();
            }
            dbHandler.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return isValidated;

    }

}
