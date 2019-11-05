package mainProgress;

import apiManager.ApiManager;
import dataSet.*;
import fileManager.FileManager;
import searchFood.RecipeInfomation;
import searchFood.SearchFoods;

import java.util.Iterator;
import java.util.List;

public class MainProgress {
    SearchFoods searchFoods;
    RecipeInfomation recipeInfomation;
    ApiManager apiManager;

    public MainProgress(){
        apiManager = new ApiManager();
        if(!IngredientList.getState()){
            readIngredientFile();   //재료 파일 읽어오기
        }
    }

    public void saveIngredient(Ingredient ingredient){  //재료목록 추가
        IngredientList.add(ingredient);
        saveIngredientFile();
    }

    public void removeIngredient(List<String> item){  //재료목록 제거
        Ingredient ingredient = new Ingredient();
        Iterator<String> iter = item.iterator();
        while(iter.hasNext()){
            String s = iter.next();
            ingredient = IngredientList.remove(s);
            History.saveChangedIngredient(ingredient);
        }
        saveIngredientFile();
    }

    public String[] getColdIngredient(){    //냉장 재료 받아오기
        return IngredientList.getCold();
    }

    public String[] getFrozenIngredient(){  //냉동 재료 받아오기
        return IngredientList.getFrozen();
    }

    public void saveIngredientFile(){   //재료목록 파일 저장
        FileManager.save.setFile("ingredient", IngredientList.getIngredientList()); //재료목록 저장
    }

    public void readIngredientFile(){   //재료 파일 읽어오기
        IngredientList.set(FileManager.read.read("ingredient"));
    }

    public FoodSet searchFoodSet(){     //있는 재료로 요리 찾기
        searchFoods = new SearchFoods();
        searchFoods.run();
        return searchFoods.getValue();
    }

    public RecipeSet getRecipeSet(String input){    //상세 요리 레시피 찾기
        RecipeSet result = new RecipeSet();
        if(input.contains("/recipe/")){
            recipeInfomation = new RecipeInfomation(input);
            recipeInfomation.run();
            result = recipeInfomation.getValue();
        }
        else{
            result = getApiRecipe(input);
        }
        return result;
    }

    public RecipeSet getApiRecipe(String input){    //api에서 요리 레시피 찾기
        RecipeSet result = new RecipeSet();
        result = apiManager.searchRecipe(input);
        if(result.size()==0){ //api에서 결과 없으면 html에서 찾기
            searchFoods = new SearchFoods(input);
            searchFoods.run();
            recipeInfomation = new RecipeInfomation(searchFoods.getValue().getFoodUrl(0));
            result = recipeInfomation.getValue();
        }
        else{
            result = getIngredientPrice(result);    //재료가격 붙이기
        }
        return result;
    }

    public RecipeSet getIngredientPrice(RecipeSet recipeSet){   //레시피의 재료목록에 재료 가격 붙이기
        RecipeSet result = new RecipeSet();
        IngredientPriceSet ingredientList = new IngredientPriceSet();

        ingredientList = apiManager.searchIngredientPrice(recipeSet.getIngredient());

        for(int i=0;i<recipeSet.size();i++){
            result.add(recipeSet.getIngredient(i)+"  "+ingredientList.toString(i), recipeSet.getRecipe(i), recipeSet.getRecipeImg(i));
        }
        return result;
    }

    public String restoreRemovedIngredient(){ //삭제한 항목 복원
        String result = "복원할 항목 없음";;
        Ingredient ingredient = History.getremovedIngredient();
        if(!ingredient.getName().equals("")){
            IngredientList.add(History.getremovedIngredient());
            result = "복원 완료!!";
        }
        return result;
    }

}
