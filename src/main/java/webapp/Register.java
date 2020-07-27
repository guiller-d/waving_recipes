package webapp;

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

@WebServlet(name="register")
public class Register extends HttpServlet {

    DBHandler dbHandler = new DBHandler();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("password_repeat");

        if (password.compareTo(repeatPassword) != 0){
            String errorMessage = "errorMessage";
            String error = "Password Mismatch";
            request.setAttribute(errorMessage, error);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        try{
            System.out.println("In here");
            Connection connection = dbHandler.startConnection();
            dbHandler.insertAccountToDB(username, 0);
            dbHandler.closeConnection();
        }
        catch (Exception e){
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
