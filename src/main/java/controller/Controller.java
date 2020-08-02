package controller;

import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;


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
    
    String accessPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/access.xml";
    String accountPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/account.xml";
    String commentPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/comment.xml";
    String foodTypepath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/food_type.xml";
    String imagePath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/image.xml";
    String ingredientPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/ingredient.xml";
    String passwordPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/password.xml";
    String postPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/post.xml";
    String recipePath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/recipe.xml";
    String followingPath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/following.xml";
    String favoritePath = "/Users/guillerdalit/IdeaProjects/cs157a-cr-idea-finished/xml/myfavorites.xml";


    //Dummy variables
    Account new_account = null;
    Post new_comment = null;
    Recipe new_recipeType = null;
    Recipe new_recipeImage = null;
    Recipe new_recipeIngredient = null;
    Password new_password = null;
    Post new_post = null;
    Recipe new_recipe = null;
    Access new_access = null;
    Follower new_follower = null;
    Favorite new_favorite = null;


    public Controller (){

    }
    public void initializedDB() throws Exception {

        Connection connection = dbHandler.startConnection();
        if (connection!= null){
            //Create Tables
            System.out.println("Is here");
            dbHandler.createTables(connection);
            System.out.println("Is fassing");
            //Insert values to tables using XML
            readXML(accountPath, "account");
            readXML(commentPath, "comment");
            readXML(foodTypepath, "food_type");
            readXML(imagePath, "image");
            readXML(ingredientPath, "ingredient");
            readXML(passwordPath, "password");
            readXML(postPath, "post");
            readXML(recipePath, "recipe");
            readXML(accessPath, "access");
            readXML(followingPath, "following");
            readXML(favoritePath, "myfavorite");
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
            case "access": new_access = new Access(); break;
            case "following": new_follower = new Follower(); break;
            case "myfavorite": new_favorite = new Favorite(); break;
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
                            if (new_access != null){
                                System.out.println("here1");
                                insertMyAccess(element, new_access);
                            }
                            if (new_follower != null){
                                insertMyNewFollower(element, new_follower);
                            }
                            if (new_favorite != null){
                                insertMyNewFavorite(element, new_favorite);
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
                new_access = null;
                new_favorite = null;
                new_follower = null;
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

    public void insertMyAccess(Element element, Access new_access) throws Exception {
        if (element.getTagName().compareTo("account_id") == 0){
            new_access.setAccount_id(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("recipe_id") == 0){
            new_access.setRecipe_id(Integer.parseInt(element.getTextContent()));
        }
        if (new_access.getAccount_id() != -1 && new_access.getRecipe_id() != -1){
            System.out.println("here2");
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

    public void insertMyNewFavorite(Element element, Favorite new_favorite) throws Exception {
        if (element.getTagName().compareTo("account_id") == 0){
            new_favorite.setAccount_id(Integer.parseInt(element.getTextContent()));
        }
        else if (element.getTagName().compareTo("recipe_id") == 0){
            new_favorite.setRecipe_id(Integer.parseInt(element.getTextContent()));
        }
        if (new_favorite.getAccount_id() != -1 && new_favorite.getRecipe_id() != -1){
            dbHandler.insertMyFavoriteToDB(new_favorite.getAccount_id(), new_favorite.getRecipe_id());
        }

    }








}
