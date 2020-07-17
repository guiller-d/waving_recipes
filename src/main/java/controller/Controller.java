package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import models.Account;
import models.Comment;
import models.Password;
import models.Recipe;
import webpages.LoginPage;
import webpages.MainPage;


public class Controller {

    private DatabaseHandler dbhandler = new DatabaseHandler();
    private WebpageHandler webpageHandler = new WebpageHandler();

    public Controller(){

    }
    public void startCookingRecipe() throws Exception {

        boolean dbIsSetup = false;
        Connection connection = dbhandler.startConnection();
        if (connection != null){
            dbhandler.createTable(connection);
            dbhandler.readXML("/Users/guillerdalit/Desktop/Workplace/IntelliJ-projects/cs157a-cr-eclipse-git/cr-xml/account.xml", "account");
            dbhandler.readXML("/Users/guillerdalit/Desktop/Workplace/IntelliJ-projects/cs157a-cr-eclipse-git/cr-xml/recipe.xml", "recipe");
            //dbhandler.readXML("/Users/guillerdalit/Desktop/Workplace/IntelliJ-projects/cs157a-cr-eclipse-git/cr-xml/password.xml", "password");
            //dbhandler.readXML("/Users/guillerdalit/Desktop/Workplace/IntelliJ-projects/cs157a-cr-eclipse-git/cr-xml/comment.xml", "comment");
            dbIsSetup = true;
        }
        else{
            System.out.println("Connection to the Database failed");
        }
        if (dbIsSetup == true){
            webpageHandler.startLoginPage();
        }
    }

}
