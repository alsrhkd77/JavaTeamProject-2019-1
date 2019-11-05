package mainProgress;

import dataSet.Ingredient;

public class RemovedIngredient {
    private final Ingredient ingredient;

    public RemovedIngredient(Ingredient ingredient){
        this.ingredient = ingredient;
    }

    public Ingredient getSavedIngredient(){
        return this.ingredient;
    }
}
