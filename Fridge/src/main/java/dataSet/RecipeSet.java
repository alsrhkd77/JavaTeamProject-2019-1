package dataSet;

import java.util.Iterator;
import java.util.List;

public class RecipeSet implements Aggregate {
    protected Recipe[] recipeList = new Recipe[50];
    protected int size;

    public RecipeSet(){
        size = 0;
    }

    public RecipeSet(String ingredient, String recipe, String img){
        size = 0;
        add(ingredient, recipe, img);
    }

    public RecipeSet(List<String> ingredient, List<String> recipe, List<String> img){
        size = 0;
        add(ingredient, recipe, img);
    }

    public void add(String ingredient, String recipe, String img){
        recipeList[size] = new Recipe(ingredient, recipe, img);
        size++;
    }

    public void add(List<String> ingredient, List<String> recipe, List<String> img){
        Iterator ingredientIter = ingredient.iterator();
        Iterator recipeIter = recipe.iterator();
        Iterator imgIter = img.iterator();

        while (ingredientIter.hasNext() || recipeIter.hasNext()){
            if(!recipeIter.hasNext()){
                recipeList[size] = new Recipe(ingredientIter.next().toString(), "", "");
                size++;
            }
            else if(!ingredientIter.hasNext()){
                recipeList[size] = new Recipe("", recipeIter.next().toString(), imgIter.next().toString());
                size++;
            }
            else{
                recipeList[size] = new Recipe(ingredientIter.next().toString(), recipeIter.next().toString(), imgIter.next().toString());
                size++;
            }
        }
    }

    public int size(){
        return size;
    }

    public Recipe getRecipeSet(int index){
        return recipeList[index].get();
    }

    public RecipeSet getRecipeSet(){
        return this;
    }

    public String getIngredient(int index){
        return recipeList[index].getIngredient();
    }

    public String[] getIngredient(){
        String[] result = new String[size];
        for(int i=0;i<size;i++){
            result[i] = recipeList[i].getIngredient();
        };
        return result;
    }

    public String getRecipe(int index){
        return recipeList[index].getRecipe();
    }

    public String getRecipeImg(int index){
        return recipeList[index].getRecipeImg();
    }

    public dataSet.Iterator iterator() {
        return new RecipeIterator(this);
    }
}
