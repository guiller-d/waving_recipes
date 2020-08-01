package webapp;

import controller.DBHandler;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;


@WebServlet(name ="addrecipe")
public class AddRecipe extends HttpServlet {

    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    private DBHandler dbHandler = new DBHandler();
    private WebHandler webHandler = new WebHandler();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String recipeName = ""; //request.getParameter("addRecipeName");
        String recipeStep = "";//request.getParameter("addRecipeStep");
        String foodType =  "";//request.getParameter("addFoodType");
        int recipeID = 0;
        String imageFilename = "";


        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            return;
        }
        recipeID = webHandler.getRecipeCount() + 1;

        DiskFileItemFactory factory = new DiskFileItemFactory();            // Create a factory for disk-based file items
        factory.setSizeThreshold(MAX_MEMORY_SIZE);  // Sets the size threshold beyond which files are written directly to disk

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        // constructs the folder where uploaded file will be stored
        String uploadFolder = "/Users/guillerdalit/IdeaProjects/cookingrecipe/web/Images";
        //getServletContext().getRealPath("")
        //      + File.separator + DATA_DIRECTORY;
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        /*********************************************************************
         * source: https://www.tutorialspoint.com/jsp/jsp_file_uploading.html
         *********************************************************************/
        // Check that we have a file upload request
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String fileExtention = FilenameUtils.getExtension(fileName);
                    imageFilename = recipeID+"."+fileExtention;
                    String filePath = uploadFolder + File.separator + imageFilename;
                    File uploadedFile = new File(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                }
                else{
                    if (item.getFieldName().equals("addRecipeName")){
                        recipeName = item.getString();
                    }
                    if (item.getFieldName().equals("addRecipeStep")){
                        recipeStep = item.getString();
                    }
                }
            }
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        /*********************************************************************
         * Insert Recipe to Database
         *********************************************************************/
        try {
            Connection connection = dbHandler.startConnection();
            dbHandler.insertRecipeToDB(recipeName, recipeStep, 0);
            dbHandler.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*********************************************************************
         * Insert Account Access to the Recipe
         *********************************************************************/
        try {
            HttpSession sess = request.getSession(false); //use false to use the existing session
            int currentUserID = (int) sess.getAttribute("currentUserID");//this will return id anytime in the session
            Connection connection = dbHandler.startConnection();
            dbHandler.insertMyAccessToDB(currentUserID, recipeID);
            dbHandler.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*********************************************************************
         * Insert Image to Database
         *********************************************************************/
        try {
            Connection connection = dbHandler.startConnection();
            String imagePath = "Images/" + imageFilename;
            dbHandler.insertRecipeImageToDB(recipeID, imagePath);
            dbHandler.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/myrecipe.jsp");

    }
}
