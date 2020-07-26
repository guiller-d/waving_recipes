package models;

public class Post {

    private int account_id;
    private String date_posted;
    public Comment comment = new Comment();


    public Post() {
        account_id = -1;
        date_posted = "N/E";
    }
    public void setRecipeId(int recipeId){
        this.comment.setRecipe_id(recipeId);
    }
    public void setComment(String comment){
        this.comment.setText(comment);
    }
    public void setCommentDate(String date_posted){
        this.date_posted = date_posted;
        this.comment.setDate_posted(date_posted);
    }
    public void setAccount_id(int account_id) {
        this.comment.setAccount_id(account_id);
        this.account_id = account_id;
    }
    public void setUsername(String username) {
        this.comment.setUsername(username);
    }


    public String getDate_posted(){ return this.date_posted; }
    public String getComment(){
        return this.comment.getText();
    }
    public int getAccount_id() {
        return account_id;
    }
    public String getUsername() {
        return this.comment.getUsername();
    }
    public int getRecipeID(){
        return this.comment.getRecipe_id();
    }

    public static class Comment{

        private int account_id;
        private int recipe_id;

        public int getRecipe_id() {
            return recipe_id;
        }

        public void setRecipe_id(int recipe_id) {
            this.recipe_id = recipe_id;
        }

        private String username;
        private String text;
        private String date_posted;

        public Comment(){
            account_id = -1;
            username = "N/E";
            text = "N/E";
            date_posted = "N/E";
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getDate_posted() {
            return date_posted;
        }

        public void setDate_posted(String date_posted) {
            this.date_posted = date_posted;
        }
    }



}
