package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHandler {

    String user = "root";
    String password = "magnolia";

    public DBHandler (){

    }
    public Connection startConnection() throws Exception {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cooking_recipe?autoReconnect=true&useSSL=false", user, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
