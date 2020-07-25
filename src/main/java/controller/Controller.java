package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(name="controller")
public class Controller extends HttpServlet {

    boolean dbIsSetup = false;

    DBHandler dbHandler = new DBHandler();

    public Controller () throws Exception{
        System.out.println("In controller");
        //startCookingRecipe();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void initializedDB() throws Exception {

        Connection connection = dbHandler.startConnection();
        if (connection!= null){
            System.out.println("Connection to the Database established");
            createTables(connection);
            dbIsSetup = true;
        }
        else{
            System.out.println("Connection to the Database failed");
        }
    }
    public boolean isComplete(){
        return dbIsSetup;
    }
    public void createTables(Connection connection) throws Exception{
        // Create account table
        String createSql = "CREATE TABLE IF NOT EXISTS account (account_id INT NOT NULL, "
                + "username VARCHAR(32) NULL, login INT NOT NULL, "
                + "PRIMARY KEY (account_id))";
        Statement statement = connection.createStatement();
        statement.execute(createSql);
        // Create recipe table
        createSql = "CREATE TABLE IF NOT EXISTS recipe (recipe_id INT NOT NULL, "
                + "recipe_name VARCHAR(128) NOT NULL, step VARCHAR(10000) NOT NULL, "
                + "privacy INT NOT NULL, PRIMARY KEY (recipe_id))";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create password table
        createSql = "CREATE TABLE IF NOT EXISTS password (account_id INT NOT NULL, "
                + " password VARCHAR(32) NOT NULL, hash_password VARCHAR(32) NOT NULL, "
                + " last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "
                + " PRIMARY KEY (account_id))";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create comment table
        createSql = "CREATE TABLE IF NOT EXISTS comment (account_id INT NOT NULL, "
                + " username VARCHAR(32) NOT NULL, text VARCHAR(300) NOT NULL, "
                + " date_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)";
        statement = connection.createStatement();
        statement.execute(createSql);
        statement.close();// Close connectionconnection.close();}}
    }
}
