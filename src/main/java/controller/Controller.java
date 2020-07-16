package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import models.Account;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Controller {

    public Controller(){

    }
    public void startCookingRecipe() throws Exception {

        Connection connection = startConnection();
        if (connection != null){
            createTable(connection);
            readAccountXML();
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
    public void insertAccountToSQL(int accountID, String username, int login) throws Exception {

        Connection connection = startConnection();
        String insertSql = "INSERT INTO account (account_id, username, login) " + "VALUES ('account_id', 'username', 'login')";
        Statement statement = connection.createStatement();
        statement.execute(insertSql);// Query
    }
    //XML required
    public void readAccountXML(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document doc = builder.parse("/Users/guillerdalit/Desktop/Workplace/IntelliJ-projects/cs157a-cr-eclipse-git/cr-xml/account.xml");
            NodeList accountList = doc.getElementsByTagName("account");
            for (int index = 0; index < accountList.getLength(); index++){
                Node a = accountList.item(index);
                if (a.getNodeType() == Node.ELEMENT_NODE){
                    Element account = (Element)a;
                    NodeList accountInfo = account.getChildNodes();
                    for (int ndx = 0; ndx < accountInfo.getLength(); ndx++){
                        Node userInfo = accountInfo.item(ndx);
                        if (userInfo.getNodeType() == Node.ELEMENT_NODE){
                            Element user = (Element)userInfo;
                            System.out.println("Account " + user.getTagName() +  " " + user.getTextContent());
                        }
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
    }
    public void readRecipeXML(){

    }

}
