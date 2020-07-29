package webapp;

import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet(name="register")
public class Register extends HttpServlet {

    DBHandler dbHandler = new DBHandler();
    WebHandler webHandler = new WebHandler();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //needed for registration
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashPassword = "";
        Date date;

        String repeatPassword = request.getParameter("password_repeat");

        if (password.compareTo(repeatPassword) != 0){
            String errorMessage = "errorMessage";
            String error = "Password Mismatch";
            request.setAttribute(errorMessage, error);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        else{
            try {
                hashPassword = webHandler.toHexString(webHandler.getSHA(password));
                date = webHandler.getDateToday();

                webHandler.insertAccount(username);
                webHandler.register(webHandler.getAccountCount(), username, password, hashPassword, date) ;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
