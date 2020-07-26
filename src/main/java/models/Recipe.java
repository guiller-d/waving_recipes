package models;

import javax.swing.*;
import java.awt.*;

public class Recipe {

    private int recipeID;
    private String recipeName;
    private String recipeSteps;
    private int privacy;
    public RecipeImage recipeImage;
    public Ingredient ingredient;
    public FoodType foodType;

    public Recipe(){
        this.recipeImage = new RecipeImage();
        this.ingredient = new Ingredient();
        this.foodType = new FoodType();
        recipeID = -1;
        recipeName = "N/E";
        recipeSteps = "N/E";
        privacy = -1;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
    public void setRecipeName(String recipeName) {

        this.recipeName = recipeName;
    }
    public void setRecipeSteps(String recipeSteps) {

        this.recipeSteps = recipeSteps;
    }
    public void setPrivacy(int privacy) {

        this.privacy = privacy;
    }
    public void setRecipeImage(String imagePath){

        /*ImageIcon image = new ImageIcon(new ImageIcon(this.getClass().getResource(imagePath)).getImage()
                .getScaledInstance(590, 90, Image.SCALE_DEFAULT));*/
        recipeImage.setImage(imagePath);
        recipeImage.setRecipeID(recipeID);
    }
    public void setIngredient(String ingredient){

        this.ingredient.setIngredients(ingredient);
        this.ingredient.setRecipeID(recipeID);
    }
    public void setFoodType(String foodType){
        this.foodType.setType(foodType);
        this.foodType.setRecipeID(recipeID);
    }


    public int getRecipeID() {
        return recipeID;
    }
    public String getRecipeName() {
        return recipeName;
    }
    public String getRecipeSteps() {
        return recipeSteps;
    }
    public int getPrivacy() {
        return privacy;
    }
    public String getImage(){
        return recipeImage.getImage();
    }
    public String getIngredient(){
        return this.ingredient.getIngredients();
    }
    public String getFoodType(){
        return this.foodType.getType();
    }



    class RecipeImage {

        private int recipeID;
        private String image;

        public RecipeImage(){
            recipeID = -1;
            image = "N/E";
        }
        public int getRecipeID() {
            return recipeID;
        }

        public void setRecipeID(int recipeID) {
            this.recipeID = recipeID;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    class Ingredient{

        private int recipeID;
        private String ingredients;

        public Ingredient(){
            recipeID = -1;
            ingredients = "N/E";
        }
        public int getRecipeID() {

            return recipeID;
        }

        public void setRecipeID(int recipeID) {

            this.recipeID = recipeID;
        }

        public String getIngredients() {

            return ingredients;
        }

        public void setIngredients(String ingredients) {

            this.ingredients = ingredients;
        }
    }
    class FoodType{
        private int recipeID;
        private String type;

        public FoodType(){
            recipeID = -1;
            type = "N/E";
        }
        public int getRecipeID() {
            return recipeID;
        }

        public void setRecipeID(int recipeID) {
            this.recipeID = recipeID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
