package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import models.Account;

public class Controller {

    public Controller(){

    }
    public void startCookingRecipe() throws Exception {

        Connection connection = startConnection();
        if (connection != null){
            createTable(connection);
        }
        else{
            System.out.println("Connection to the Database failed");
        }
    }
    public Connection startConnection() throws Exception {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookingRecipe?autoReconnect=true&useSSL=false", "root", "magnolia");
            System.out.println("Connection to the Database established");
        }
        catch (Exception e){
            e.printStackTrace();
        }
       return connection;
    }
    public void createTable(Connection connection) throws Exception {

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
    //XML required
    public void addAccount(){
        //xml todo

    }
    public void addRecipe(){

    }

}
