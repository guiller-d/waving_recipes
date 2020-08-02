package controller;

import java.sql.*;
import java.time.LocalDate;

public class DBHandler {

    String user = "root";
    String password = "magnolia";
    Connection connection = null;

    public DBHandler (){

    }
    public void closeConnection() throws SQLException {
        this.connection.close();
    }
    public Connection startConnection() throws Exception {

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookingRecipe?autoReconnect=true&useSSL=false", user, password);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public void createTables(Connection connection) throws Exception{
        // Create account table
        String createSql = "CREATE TABLE IF NOT EXISTS account (account_id INT NOT NULL AUTO_INCREMENT, "
                + "username VARCHAR(32) NULL, login INT NOT NULL, "
                + "PRIMARY KEY (account_id))";
        Statement statement = connection.createStatement();
        statement.execute(createSql);
        // Create recipe table
        createSql = "CREATE TABLE IF NOT EXISTS recipe (recipe_id INT NOT NULL AUTO_INCREMENT, "
                + "recipe_name VARCHAR(128) NOT NULL, step VARCHAR(10000) NOT NULL, "
                + "privacy INT NOT NULL, PRIMARY KEY (recipe_id))";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create password table
        createSql = "CREATE TABLE IF NOT EXISTS password (account_id INT NOT NULL, "
                + " password VARCHAR(32) NOT NULL, hash_password VARCHAR(64) NOT NULL, "
                + " last_update DATE, "
                + " PRIMARY KEY (account_id))";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create food_type table
        createSql = "CREATE TABLE IF NOT EXISTS food_type (recipe_id INT NOT NULL, "
                + " type VARCHAR(32) NOT NULL)";
        statement = connection.createStatement();
        statement.execute(createSql);
        // Create image table
        createSql = "CREATE TABLE IF NOT EXISTS image (recipe_id INT NOT NULL, "
                + "  image VARCHAR(32) NOT NULL)";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create post table
        createSql = "CREATE TABLE IF NOT EXISTS post (account_id INT NOT NULL, "
                + " date_posted DATE)";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create ingredient table
        createSql = "CREATE TABLE IF NOT EXISTS access (recipe_id INT NOT NULL, "
                + "  account_id INT NOT NULL)";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create access table
        createSql = "CREATE TABLE IF NOT EXISTS ingredient (recipe_id INT NOT NULL, "
                + "  ingredient VARCHAR(2000) NOT NULL)";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create comment table
        createSql = "CREATE TABLE IF NOT EXISTS comment (account_id INT NOT NULL, "
                + " recipe_id INT NOT NULL, username VARCHAR(32) NOT NULL, text VARCHAR(300) NOT NULL, "
                + " date_posted DATE)";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create myrecipe table
        createSql = "CREATE TABLE IF NOT EXISTS myrecipe (account_id INT NOT NULL, "
                + " recipe_id INT NOT NULL, date_posted DATE)";

        statement = connection.createStatement();
        statement.execute(createSql);

        // Create access  table
        createSql = "CREATE TABLE IF NOT EXISTS access (account_id INT NOT NULL, "
                + " recipe_id INT NOT NULL)";
        statement = connection.createStatement();
        statement.execute(createSql);

        // Create following  table
        createSql = "CREATE TABLE IF NOT EXISTS following (account_id INT NOT NULL, "
                + " follower_id INT NOT NULL)";
        statement = connection.createStatement();
        statement.execute(createSql);

        System.out.println("Was hereasdasdasdasdasdasdasd");
        // Create myfavorite  table
        createSql = "CREATE TABLE IF NOT EXISTS myfavorite (account_id INT NOT NULL, "
                + " recipe_id INT NOT NULL)";

        statement = connection.createStatement();
        statement.execute(createSql);


        statement.close();// Close connectionconnection.close();}}
    }
    public void insertAccountToDB(String username, int login) throws Exception {
//        Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO account (username, login) " + "VALUES ('"+username+"', '"+login+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertRecipeToDB(String recipe_name, String step, int privacy) throws Exception {

        //     Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO recipe (recipe_name, step, privacy) " + "VALUES ('"+recipe_name+"', '"+step+"', '"+privacy+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertCommentToDB(int account_id, int recipe_id, String username, String text, String date_posted) throws Exception {

        LocalDate localDate = LocalDate.parse(date_posted);
        java.util.Date date = Date.valueOf(localDate);

        //   Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO comment (account_id, recipe_id, username, text, date_posted) " + "VALUES ('"+account_id+"', '"+recipe_id+"','"+username+"', '"+text+"', '"+date+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertRecipeTypeToDB(int recipe_id, String type) throws Exception {

        //   Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO food_type (recipe_id, type) " + "VALUES ('"+recipe_id+"', '"+type+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertRecipeImageToDB(int recipe_id, String imagePath) throws Exception {
        //  Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO image (recipe_id, image) " + "VALUES ('"+recipe_id+"', '"+imagePath+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertRecipeIngredientToDB(int recipe_id, String ingredient) throws Exception {

        //  Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO ingredient (recipe_id, ingredient) " + "VALUES ('"+recipe_id+"', '"+ingredient+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }

    public void insertPasswordToDB(int accountID, String password, String hashPassword, String lastUpdate) throws Exception {

        LocalDate localDate = LocalDate.parse(lastUpdate);
        java.util.Date date = Date.valueOf(localDate);

        //  Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO password (account_id, password, hash_password, last_update) " + "VALUES ('"+accountID+"', '"+password+"', '"+hashPassword+"', '"+date+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertPostToDB(int accountID, String date_posted) throws Exception {

        LocalDate localDate = LocalDate.parse(date_posted);
        java.util.Date date = Date.valueOf(localDate);

        //   Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO post (account_id, date_posted) " + "VALUES ('"+accountID+"', '"+date+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }

    public void insertMyAccessToDB(int accountID, int recipeID) throws Exception {

        //   Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO access (account_id, recipe_id) " + "VALUES ('"+accountID+"','"+recipeID+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertFollowingToDB(int accountID, int follower_id) throws Exception {

        //   Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO following (account_id, follower_id) " + "VALUES ('"+accountID+"','"+follower_id+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }

    public void insertMyFavoriteToDB(int accountID, int recipeID) throws Exception {

        //   Connection connection = startConnection();
        String insertSql = "INSERT IGNORE INTO myfavorite (account_id, recipe_id) " + "VALUES ('"+accountID+"','"+recipeID+"')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }

}
