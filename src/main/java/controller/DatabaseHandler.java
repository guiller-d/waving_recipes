package controller;

import models.Account;
import models.Comment;
import models.Password;
import models.Recipe;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DatabaseHandler{

    private ArrayList<Account> accountArrayList = new ArrayList<Account>();
    private ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
    private ArrayList<Comment> commentArrayList = new ArrayList<Comment>();
    private ArrayList<Password> passwordArrayList = new ArrayList<Password>();

    public DatabaseHandler(){

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
    public void readXML(String path, String tagName){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{

            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document doc = builder.parse(path);
            NodeList list = doc.getElementsByTagName(tagName);


            //Dummy variables
            Account new_account = null;
            Recipe new_recipe = null;
            Comment new_comment = null;
            Password new_password = null;


            for (int index = 0; index < list.getLength(); index++){
                Node node = list.item(index);

                if (tagName.compareTo("account") == 0) {
                    new_account = new Account();
                }
                if (tagName.compareTo("recipe") == 0) {
                    new_recipe = new Recipe();
                }
                if (tagName.compareTo("password") == 0) {
                    new_comment = new Comment();
                }
                if (tagName.compareTo("comment") == 0) {
                    new_password = new Password();
                }

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element xmlElements = (Element)node;
                    NodeList xmlInfo = xmlElements.getChildNodes();
                    for (int ndx = 0; ndx < xmlInfo.getLength(); ndx++){
                        Node userInfo = xmlInfo.item(ndx);
                        if (userInfo.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element)userInfo;
                            //account XML
                            if (new_account != null){

                                if (element.getTagName().compareTo("accountID") == 0){
                                    new_account.setAccount_id(Integer.parseInt(element.getTextContent()));
                                }
                                else if (element.getTagName().compareTo("username") == 0){
                                    new_account.setUsername(element.getTextContent());
                                }
                                else if (element.getTagName().compareTo("login") == 0) {
                                    new_account.setLogin_status(Integer.parseInt(element.getTextContent()));
                                }
                            }
                            //recipe XML
                            if (new_recipe != null){
                                if (element.getTagName().compareTo("recipeID") == 0){
                                    new_recipe.setRecipeID(Integer.parseInt(element.getTextContent()));
                                }
                                else if (element.getTagName().compareTo("recipeName") == 0){
                                    new_recipe.setRecipeName(element.getTextContent());
                                }
                                else if (element.getTagName().compareTo("step") == 0){
                                    new_recipe.setRecipeSteps(element.getTextContent());
                                }
                                else if (element.getTagName().compareTo("privacy") == 0){
                                    new_recipe.setPrivacy(Integer.parseInt(element.getTextContent()));
                                }
                            }
                            if (new_password != null){
                                if (element.getTagName().compareTo("accountID") == 0){

                                }
                                else if (element.getTagName().compareTo("password") == 0){

                                }
                                else if (element.getTagName().compareTo("hashPassword") == 0){

                                }
                                else if (element.getTagName().compareTo("lastUpdate") == 0){

                                }
                            }
                            if (new_comment != null){

                            }

                        }
                    }
                    if (tagName.compareTo("account") == 0) {
                        accountArrayList.add(new_account);
                    }
                    if (tagName.compareTo("recipe") == 0) {
                        recipeArrayList.add(new_recipe);
                    }
                    if (tagName.compareTo("password") == 0) {
                        new_comment = new Comment();
                    }
                    if (tagName.compareTo("comment") == 0) {
                        new_password = new Password();
                    }

                }
            }
        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int index = 0 ; index < accountArrayList.size(); index++){
            System.out.println("Account Id " + accountArrayList.get(index).getAccount_id());
            System.out.println("Account Username " + accountArrayList.get(index).getUsername());
            System.out.println("Login Status " + accountArrayList.get(index).getLogin_status());
        }
        for (int index = 0 ; index < recipeArrayList.size(); index++){
            System.out.println("Account Id " + recipeArrayList.get(index).getRecipeID());
            System.out.println("Account Username " + recipeArrayList.get(index).getRecipeName());
            System.out.println("Login Status " + recipeArrayList.get(index).getRecipeSteps());
        }
    }
    public void insertAccountToDB(int accountID, String username, int login) throws Exception {

        Connection connection = startConnection();
        String insertSql = "INSERT INTO account (account_id, username, login) " + "VALUES ('account_id', 'username', 'login')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertRecipeToDB(int recipeId, String recipeName, String step, int privacy) throws Exception {

        Connection connection = startConnection();
        String insertSql = "INSERT INTO account (account_id, username, login) " + "VALUES ('account_id', 'username', 'login')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertCommentToDB(int accountID, String username, int login) throws Exception {

        Connection connection = startConnection();
        String insertSql = "INSERT INTO account (account_id, username, login) " + "VALUES ('account_id', 'username', 'login')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }
    public void insertPasswordToDB(int accountID, String password, String hashPassword, String lastUpdate) throws Exception {

        Connection connection = startConnection();
        String insertSql = "INSERT INTO account (account_id, username, login) " + "VALUES ('account_id', 'username', 'login')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);
        statement.close();
    }

}
