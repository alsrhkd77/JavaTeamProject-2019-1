package mainProgress;

import dataSet.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class History {
    private static List<RemovedIngredient> savedIngredient = new ArrayList<RemovedIngredient>();

    public History(){
    }

    public static void saveChangedIngredient(Ingredient ingredient){
        savedIngredient.add(new RemovedIngredient(ingredient));
    }

    public static Ingredient getremovedIngredient(){
        Ingredient ingredient = new Ingredient();

        if(savedIngredient.size()>0){
            ingredient = savedIngredient.get(savedIngredient.size() - 1).getSavedIngredient();
            savedIngredient.remove(savedIngredient.size() - 1);
        }

        return ingredient;
    }
}
