package dataSet;

import java.util.ArrayList;
import java.util.List;

public class RecipeSetBuilder implements Builder {
    private List<String> ingredientList;
    private List<String> recipeList;
    private List<String> recipeImgList;

    public RecipeSetBuilder(){
        ingredientList = new ArrayList<String>();
        recipeList = new ArrayList<String>();
        recipeImgList = new ArrayList<String>();
    }

    public void ingredientList(List<String> ingredientList){
        this.ingredientList = new ArrayList<String>(ingredientList);
    }

    public void recipeList(List<String> recipeList){
        this.recipeList = new ArrayList<String>(recipeList);
    }

    public void recipeImgList(List<String> recipeImgList){
        this.recipeImgList = new ArrayList<String>(recipeImgList);
    }

    public RecipeSet build() {
        return new RecipeSet(ingredientList, recipeList, recipeImgList);
    }
}
