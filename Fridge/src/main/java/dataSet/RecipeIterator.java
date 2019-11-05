package dataSet;

public class RecipeIterator implements Iterator {
    private RecipeSet recipe;
    private int index;

    public RecipeIterator(RecipeSet recipeSet){
        this.recipe = recipeSet;
        index = 0;
    }

    public boolean hasNext() {
        boolean result = false;
        if(index<recipe.size()){
            result = true;
        }
        return result;
    }

    public Recipe next() {
        Recipe recipe = this.recipe.getRecipeSet(index);
        index++;
        return recipe;
    }
}
