package dataSet;

import java.util.ArrayList;
import java.util.List;

public class IngredientList{
    private static List<Ingredient> ingredientList = new ArrayList<Ingredient>();
    private static boolean setting = false;

    public static boolean getState(){
        return setting;
    }

    public static void set(List<Ingredient> List){   //넣어주기
        ingredientList = List;
        setting = true;
    }

    public static void add(String name, String keep,String parentCategory, String subCategory){
        ingredientList.add(new Ingredient(name, keep, parentCategory, subCategory));
    }

    public static void add(Ingredient ingredient){
        ingredientList.add(ingredient);
    }

    public static Ingredient remove(String item){
        Ingredient ingredient = new Ingredient();
        for(int i=0;i<ingredientList.size();i++){
            if(getIngredient(i).toString().equals(item)){
                ingredient = getIngredient(i);
                ingredientList.remove(i);
                break;
            }
        }
        return ingredient;
    }

    public static String[] getCold(){
        List<String> result = new ArrayList<String>();
        for(int i=0;i< ingredientList.size();i++){
            if(ingredientList.get(i).getKeep().equals("냉장")){
                result.add(ingredientList.get(i).toString());
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static String[] getFrozen(){
        List<String> result = new ArrayList<String>();
        for(int i=0;i< ingredientList.size();i++){
            if(ingredientList.get(i).getKeep().equals("냉동")){
                result.add(ingredientList.get(i).toString());
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static int size(){
        return ingredientList.size();
    }

    public static Ingredient getIngredient(int index){
        return ingredientList.get(index).getIngredient();
    }

    public static String getName(int index){
        return ingredientList.get(index).getName();
    }

    public static String toString(int index){
        return ingredientList.get(index).toString();
    }

    public static List<Ingredient> getIngredientList(){
        return ingredientList;
    }
}
