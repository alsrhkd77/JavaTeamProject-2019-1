package dataSet;

public class Recipe {
    private String ingredient;
    private String recipe;
    private String recipeImg;

    public Recipe(){
    }

    public Recipe(String ingredient, String recipe, String recipeImg){
        add(ingredient, recipe, recipeImg);
    }

    public Recipe get(){
        return this;
    }

    public void add(String ingredient, String recipe, String recipeImg){
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.recipeImg = recipeImg;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getRecipeImg() {
        return recipeImg;
    }
}
