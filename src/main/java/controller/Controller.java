package controller;

import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/*
 * TODO: Update Documents,
 *  Functional Requirements
 *  ERD Diagram to Schema
 *  Implementation
 *  Step by step of running the project (TEAM#_Project.zip)
 *  A lot of instruction to run the project (include images)
 *
 * */
public class Controller extends HttpServlet {

    boolean dbIsSetup = false;
    DBHandler dbHandler = new DBHandler();

    String accountPath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/account.xml";
    String commentPath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/comment.xml";
    String foodTypepath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/food_type.xml";
    String imagePath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/image.xml";
    String ingredientPath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/ingredient.xml";
    String passwordPath ="/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/password.xml";
    String postPath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/post.xml";
    String recipePath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/recipe.xml";
    String myRecipePath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/myfavorites.xml";
    String accessPath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/access.xml";
    String followingPath = "/Users/guillerdalit/IdeaProjects/cookingrecipe/xml/following.xml";

    //Dummy variables
    Account new_account = null;
    Post new_comment = null;
    Recipe new_recipeType = null;
    Recipe new_recipeImage = null;
    Recipe new_recipeIngredient = null;
    Password new_password = null;
    Post new_post = null;
    Recipe new_recipe = null;
    Account new_myrecipe = null;
    Access new_access = null;
    Follower new_follower = null;


    public Controller (){

    }
    public void initializedDB() throws Exception {

        Connection connection = dbHandler.startConnection();
        if (connection!= null){
            //Create Tables
            dbHandler.createTables(connection);
            //Insert values to tables using XML
            readXML(accountPath, "account");
            readXML(commentPath, "comment");
            readXML(foodTypepath, "food_type");
            readXML(imagePath, "image");
            readXML(ingredientPath, "ingredient");
            readXML(passwordPath, "password");
            readXML(postPath, "post");
            readXML(recipePath, "recipe");
            readXML(myRecipePath, "myrecipe");
            readXML(accessPath, "access");
            readXML(followingPath, "following");
            dbIsSetup = true;
            connection.close();
        }
        else{
            System.out.println("Connection to the Database failed");
        }
    }
    public boolean isComplete(){
        return dbIsSetup;
    }

    public void createInstances(String tagName){

        switch (tagName){
            case "account": new_account = new Account(); break;
            case "comment": new_comment = new Post(); break;
            case "food_type": new_recipeType = new Recipe(); break;
            case "image": new_recipeImage = new Recipe(); break;
            case "ingredient": new_recipeIngredient = new Recipe(); break;
            case "password": new_password = new Password(); break;
            case "post": new_post = new Post(); break;
            case "recipe": new_recipe = new Recipe(); break;
            case "myrecipe": new_myrecipe = new Account(); break;
            case "access": new_access = new Access(); break;
            case "following": new_follower = new Follower(); break;
        }
    }
    public void readXML(String path, String tagName){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document doc = builder.parse(path);
            NodeList list = doc.getElementsByTagName(tagName);

            for (int index = 0; index < list.getLength(); index++){
                Node node = list.item(index);
                createInstances(tagName);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element xmlElements = (Element)node;
                    NodeList xmlInfo = xmlElements.getChildNodes();
                    for (int ndx = 0; ndx < xmlInfo.getLength(); ndx++){
                        Node userInfo = xmlInfo.item(ndx);
                        if (userInfo.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element)userInfo;
                            if (new_account != null){
                                insertAccount(element, new_account);
                            }
                            if (new_comment != null){
                                insertComment(element, new_comment);
                            }
                            if (new_recipeType != null){

                                insertRecipeType(element, new_recipeType);
                            }
                            if (new_recipeImage != null){
                                insertRecipeImage(element, new_recipeImage);
                            }
                            if (new_recipeIngredient != null){
                                insertRecipeIngredient(element, new_recipeIngredient);
                            }
                            if (new_password != null){
                                insertPassword(element, new_password);
                            }
                            if (new_post != null){
                                insertPost(element, new_post);
                            }
                            if (new_recipe != null){
                                insertRecipe(element, new_recipe);
                            }
                            if (new_myrecipe != null){
                                insertMyRecipe(element, new_myrecipe);
                            }
                            if (new_access != null){
                                insertMyAccess(element, new_access);
                            }
                            if (new_follower != null){
                                insertMyNewFollower(element, new_follower);
                            }


                        }
                    }
                }
                new_account = null;
                new_comment = null;
                new_recipeType = null;
                new_recipeImage = null;
                new_recipeIngredient = null;
                new_password = null;
                new_post = null;
                new_recipe = null;
                new_myrecipe = null;
                new_access = null;
            }
        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertAccount(Element element, Account new_account) throws Exception {

        if (element.getTagName().compareTo("username") == 0){
            new_account.setUsername(element.getTextContent());
        }
        else if (element.getTagName().compareTo("login") == 0) {
            new_account.setLogin_status(Integer.parseInt(element.getTextContent()));
        }
        if (new_account.getUsername().compareTo("N/E") != 0 && new_account.getLogin_status() != -1){
            dbHandler.insertAccountToDB(new_account.getUsername(), new_account.getLogin_status());
        }

    }
    public void insertComment(Element element, Post new_comment) throws Exception {
        if (element.getTagName().compareTo("accountID") == 0){
            new_comment.setAccount_id(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("recipeID") == 0){
            new_comment.setRecipeId(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("username") == 0){
            new_comment.setUsername(element.getTextContent());
        }
        else if (element.getTagName().compareTo("text") == 0) {
            new_comment.setComment(element.getTextContent());
        }
        else if (element.getTagName().compareTo("dateposted") == 0){
            new_comment.setCommentDate(element.getTextContent());
        }

        if (new_comment.getAccount_id() != -1 && new_comment.getUsername().compareTo("N/E") != 0 && new_comment.getComment().compareTo("N/E") != 0 && new_comment.getDate_posted().compareTo("N/E") != 0  ){
            dbHandler.insertCommentToDB(new_comment.getAccount_id(), new_comment.getRecipeID(), new_comment.getUsername(), new_comment.getComment(), new_comment.getDate_posted());
        }
    }

    public void insertRecipeType(Element element, Recipe new_recipeType) throws Exception{

        if (element.getTagName().compareTo("recipe_id") == 0){
            new_recipeType.setRecipeID(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("type") == 0){
            new_recipeType.setFoodType(element.getTextContent());
        }

        if (new_recipeType.getRecipeID() != -1 && new_recipeType.getFoodType().compareTo("N/E") != 0){
            dbHandler.insertRecipeTypeToDB(new_recipeType.getRecipeID(), new_recipeType.getFoodType());
        }
    }
    public void insertRecipeImage(Element element, Recipe new_recipeImage) throws Exception {
        if (element.getTagName().compareTo("recipe_id") == 0){
            new_recipeImage.setRecipeID(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("image") == 0){
            new_recipeImage.setRecipeImage(element.getTextContent());
        }
        if (new_recipeImage.getRecipeID() != -1 && new_recipeImage.getImage().compareTo("N/E") != 0){
            dbHandler.insertRecipeImageToDB(new_recipeImage.getRecipeID(), new_recipeImage.getImage());
        }
    }
    public void insertRecipeIngredient(Element element, Recipe new_recipeIngredient) throws Exception{

        if (element.getTagName().compareTo("recipe_id") == 0){
            new_recipeIngredient.setRecipeID(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("ingredient") == 0){
            new_recipeIngredient.setIngredient(element.getTextContent());
        }
        if (new_recipeIngredient.getRecipeID() != -1 && new_recipeIngredient.getIngredient().compareTo("N/E")!=0){
            dbHandler.insertRecipeIngredientToDB(new_recipeIngredient.getRecipeID(),new_recipeIngredient.getIngredient());
        }

    }

    public void insertPassword(Element element, Password new_password) throws Exception{
        if (element.getTagName().compareTo("accountID") == 0){
            new_password.setAccountId(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("password") == 0){
            new_password.setPassword(element.getTextContent());
        }
        else if (element.getTagName().compareTo("hashPassword") == 0){
            new_password.setHashPassword(element.getTextContent());
        }
        else if (element.getTagName().compareTo("lastUpdate") == 0){
            new_password.setLastUpdate(element.getTextContent());
        }
        if (new_password.getAccountId() != -1 && new_password.getPassword().compareTo("N/E") != 0 && new_password.getHashPassword().compareTo("N/E")!=0 && new_password.getLastUpdate().compareTo("N/E") != 0){
            dbHandler.insertPasswordToDB(new_password.getAccountId(), new_password.getPassword(), new_password.getHashPassword(), new_password.getLastUpdate());
        }
    }

    public void insertPost(Element element, Post new_post) throws Exception {
        if (element.getTagName().compareTo("account_id") == 0){
            new_post.setAccount_id(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("date_posted") == 0){
            new_post.setCommentDate(element.getTextContent());
        }
        if (new_post.getAccount_id() != -1 && new_post.getDate_posted().compareTo("N/E") != 0){
            dbHandler.insertPostToDB(new_post.getAccount_id(), new_post.getDate_posted());
        }


    }

    public void insertRecipe(Element element, Recipe new_recipe) throws Exception {
        if (element.getTagName().compareTo("recipeName") == 0){
            new_recipe.setRecipeName(element.getTextContent());
        }
        else if (element.getTagName().compareTo("step") == 0){
            new_recipe.setRecipeSteps(element.getTextContent());
        }
        else if (element.getTagName().compareTo("privacy") == 0){
            new_recipe.setPrivacy(Integer.parseInt(element.getTextContent()));
        }
        if (new_recipe.getRecipeName().compareTo("N/E")!=0 && new_recipe.getRecipeSteps().compareTo("N/E")!=0 && new_recipe.getPrivacy() != -1){
            dbHandler.insertRecipeToDB(new_recipe.getRecipeName(), new_recipe.getRecipeSteps(), new_recipe.getPrivacy());

        }

    }
    public void insertMyRecipe(Element element, Account new_myrecipe) throws Exception {
        if (element.getTagName().compareTo("account_id") == 0){
            new_myrecipe.setAccount_id(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("recipe_id") == 0){
            new_myrecipe.setMyRecipe(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("date_added") == 0){
            new_myrecipe.setDatePosted(element.getTextContent());
        }
        if (new_myrecipe.getAccount_id() != -1 && new_myrecipe.getMyRecipe() != -1 && new_myrecipe.getDatePosted().compareTo("N/E") != 0){
            dbHandler.insertMyRecipeToDB(new_myrecipe.getAccount_id(), new_myrecipe.getMyRecipe(), new_myrecipe.getDatePosted());
        }

    }

    public void insertMyAccess(Element element, Access new_access) throws Exception {
        if (element.getTagName().compareTo("account_id") == 0){
            new_access.setAccount_id(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("recipe_id") == 0){
            new_access.setRecipe_id(Integer.parseInt(element.getTextContent()));
        }
        if (new_access.getAccount_id() != -1 && new_access.getRecipe_id() != -1){
            dbHandler.insertMyAccessToDB(new_access.getAccount_id(), new_access.getRecipe_id());
        }

    }
    public void insertMyNewFollower(Element element, Follower new_follower) throws Exception {
        if (element.getTagName().compareTo("account_id") == 0){
            new_follower.setAccountID(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("follower_id") == 0){
            new_follower.setFollower_id(Integer.parseInt(element.getTextContent()));
        }
        if (new_follower.getAccountID() != -1 && new_follower.getFollower_id() != -1){
            dbHandler.insertFollowingToDB(new_follower.getAccountID(), new_follower.getFollower_id());
        }

    }








}
