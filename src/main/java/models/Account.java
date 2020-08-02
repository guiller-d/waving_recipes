package models;

public class Account {
    private String username;
    private int account_id;
    private int login_status;
    private MyRecipe myRecipe = new MyRecipe();

    public Account(){
        this.username = "N/E";
        account_id = -1;
        login_status = -1;
    }
    public void setUsername(String username){

        this.username = username;
    }
    public void setAccount_id(int account_id){

        this.account_id = account_id;
    }
    public void setLogin_status(int login_status){

        this.login_status = login_status;
    }
    public String getUsername() {

        return username;
    }
    public int getAccount_id() {

        return account_id;
    }
    public int getLogin_status() {

        return login_status;
    }
    public void setMyRecipe(int recipeID){
        myRecipe.setRecipe_id(recipeID);
    }
    public void setDatePosted(String datePosted){
        myRecipe.setDatePosted(datePosted);
    }
    public int getMyRecipe(){
        return myRecipe.getRecipe_id();
    }
    public String getDatePosted(){
        return myRecipe.getDatePosted();
    }

    class MyRecipe{
        int recipe_id = -1;
        int account_id = -1;
        String datePosted = "N/E";
        public void setRecipe_id(int recipe_id){
            this.recipe_id = recipe_id;
        }
        public void setDatePosted(String date){
            datePosted = date;
        }
        public int getRecipe_id(){
            return this.recipe_id;
        }
        public String getDatePosted(){
            return this.datePosted;
        }
    }

}
