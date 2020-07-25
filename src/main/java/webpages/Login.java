package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        if (request.getParameter("login") != null){
            String username = request.getParameter("uname");
            String password = request.getParameter("pwd");
            out.println(username);
            out.println(password);
            /*TODO: search the username and password if in the database,
               if username and password in the database then user login,
               otherwise, display the same page display wrong password
               validate(username, password);
            */
            if (validate(username, password) == true){
                response.sendRedirect("mainpage.jsp");
            }else{
                // Wrong password notification
                out.println( "<h3>" + "Incorrect Credentials." + "</h3>");
            }
        }
        if (request.getParameter("register") != null){

            /*TODO:  String username = request.getParameter("new_username");
             String username = request.getParameter("new_password");
            register(username, password);
             */

            response.sendRedirect("register.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    public void  register (String username, String password){
        //  INSERT values ...

    }
    public boolean validate (String username, String password){
        return true;
    }



}
