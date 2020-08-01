package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="follower")
public class Follower extends HttpServlet {


    WebHandler webHandler = new WebHandler();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int accountCount = webHandler.getAccountCount();
        int accountID = 0;
        for (int index = 0; index < accountCount; index++){

            if (request.getParameter(String.valueOf(index)) != null){
                accountID = index;
                break;
            }
        }

        if (accountID != 0) {
            HttpSession sess = request.getSession();
            sess.setAttribute("theClickId", accountID);
            request.getRequestDispatcher("/displayFollowerRecipe.jsp").forward(request, response);

        }


    }
}
