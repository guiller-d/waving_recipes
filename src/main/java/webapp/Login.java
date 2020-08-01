package webapp;

import controller.Controller;
import controller.DBHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(name="login")
public class Login extends HttpServlet {

    private static WebHandler webHandler = new WebHandler();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            if (webHandler.isDBReady() == true){

                if (request.getParameter("login") != null){
                    if (webHandler.validateUsername(username) == true && webHandler.validatePassword(password) == true){
                        HttpSession sess = request.getSession();
                        int id = webHandler.getAccountID(username, password);
                        sess.setAttribute("currentUserID", id);
                        sess.setAttribute("currentUserName", username);
                        response.sendRedirect("/mainpage.jsp");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
