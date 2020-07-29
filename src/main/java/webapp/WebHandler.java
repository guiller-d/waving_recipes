package webapp;

import controller.Controller;
import controller.DBHandler;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WebHandler {

    DBHandler dbHandler = new DBHandler();
    Controller controller = new Controller();
    private static int accountID = 0;
    private static String username_DB = "";
    private static String password_DB = "";
    private static String hashPassword = "";
    private static boolean databaseReady = false;

    private ArrayList<String> commentList = new ArrayList<String>();
    private ArrayList<Integer> accountIDList = new ArrayList<Integer>();



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
                accountID = rs_account.getInt("account_id");
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
            String selectSql = "SELECT * FROM password WHERE account_id='"+accountID+"'";
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
    public boolean isDBReady(){
        boolean isDBReady = false;
        try{
            if (databaseReady == false){
                controller.initializedDB();
                if (controller.isComplete() == true){
                    isDBReady = true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Database created");
        return isDBReady;
    }
    public int getAccountCount(){

        int accountCount = 0;
        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT COUNT(account_id) AS account_count FROM account";
            Statement statement = connection.createStatement();
            ResultSet rs_account = statement.executeQuery(selectSql);
            rs_account.first();
            accountCount = rs_account.getInt("account_count");
            accountCount += 1;
            statement.close();
            rs_account.close();
            dbHandler.closeConnection();
        }
        catch (Exception e){

        }
        return accountCount;
    }
    public int getRecipeCount(){
        int count = 0;

        try {
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT COUNT(recipe_id) AS recipe_count FROM recipe";
            Statement statement = connection.createStatement();
            ResultSet rs_recipe_count = statement.executeQuery(selectSql);
            rs_recipe_count.first();
            count =  rs_recipe_count.getInt("recipe_count");
            statement.close();
            rs_recipe_count.close();
            dbHandler.closeConnection();

        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public String getImage(int recipe_id){
        String imagepath = "";
        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT image FROM image WHERE recipe_id='"+recipe_id+"'";
            Statement statement = connection.createStatement();
            ResultSet rs_image = statement.executeQuery(selectSql);
            rs_image.first();
            imagepath = rs_image.getString("image");
            rs_image.close();
            statement.close();
            dbHandler.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return imagepath;
    }
    public void setAccountComments(int recipe_id){
        try{
            Connection connection = dbHandler.startConnection();
            String selectSql = "SELECT * FROM comment WHERE recipe_id='"+recipe_id+"'";
            Statement statement = connection.createStatement();
            ResultSet rs_comment = statement.executeQuery(selectSql);
            while(rs_comment.next()){
                commentList.add(rs_comment.getString("text"));
                accountIDList.add(rs_comment.getInt("account_id"));
            }
            rs_comment.close();
            statement.close();
            dbHandler.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void insertAccount(String username){
        try{
            Connection connection = dbHandler.startConnection();
            dbHandler.insertAccountToDB(username, 0);
            dbHandler.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void register(int accountID, String username, String password, String hashPassword, Date date){

        int account_id = 0;
        try{
            Connection connection = dbHandler.startConnection();
            dbHandler.insertPasswordToDB(accountID, password, hashPassword, String.valueOf(date));
            dbHandler.closeConnection();

        }
        catch (Exception e){
        }
    }
    public Date getDateToday(){
        java.util.Date now = new java.util.Date();
        return new java.sql.Date(now.getTime());
    }

    /*src: https://www.geeksforgeeks.org/sha-256-hash-in-java/*/
    public byte[] getSHA(String input) throws NoSuchAlgorithmException, NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
