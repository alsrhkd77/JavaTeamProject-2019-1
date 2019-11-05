package apiManager;

import dataSet.IngredientPriceSet;
import dataSet.RecipeSet;
import dataSet.RecipeSetBuilder;

public class ApiManager {

    public ApiManager(){
    }

    public IngredientPriceSet searchIngredientPrice(String[] ingredientList){   //각 재료 가격 가져오기
        IngredientPriceSet ingredientPriceSet = new IngredientPriceSet();
        for(int i=0;i<ingredientList.length;i++){   //각 재료 가격 검색 후 객체에 추가
            String temp = ingredientList[i].substring(0, ingredientList[i].indexOf(" ",0));
            IngredientPrice ingredientPrice = new IngredientPrice(ingredientList[i]);
            ingredientPriceSet.add(temp, ingredientPrice.getUnit(), ingredientPrice.getPrice());
        }
        return ingredientPriceSet;
    }

    public RecipeSet searchRecipe(String foodName){ //요리 레시피 검색
        String recipeID;    //레시피 코드
        RecipeSetBuilder recipeSetBuilder = new RecipeSetBuilder();
        FindIngredient ingredient;  //레시피에 들어가는 재료 검색
        FindRecipe recipe;  //레시피 과정, 과정별 이미지 검색
        FindFoodInfo foodInfo = new FindFoodInfo(foodName); //요리 이름으로 요리코드 검색

        recipeID = foodInfo.getRecipeId();
        ingredient = new FindIngredient(recipeID);
        recipe = new FindRecipe(recipeID);

        recipeSetBuilder.ingredientList(ingredient.getIngredient());
        recipeSetBuilder.recipeList(recipe.getCookList());
        recipeSetBuilder.recipeImgList(recipe.getCookImgList());

        return recipeSetBuilder.build();
    }
}
