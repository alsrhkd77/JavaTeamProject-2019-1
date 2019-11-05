package dataSet;

public class IngredientPriceSet {
    private String[] ingredientList = new String[30];
    private String[] unitList = new String[30];
    private String[] priceList = new String[30];
    private int size;

    public IngredientPriceSet(){
        size = 0;
    }

    public IngredientPriceSet(String ingredient, String unit, String price){
        size = 0;
        add(ingredient, unit, price);
    }

    public void add(String ingredient, String unit, String price){
        ingredientList[size] = ingredient;
        unitList[size] = unit;
        priceList[size] = price;
        size++;
    }

    public int size(){
        return size;
    }

    public String[] getIngredientList() {
        return ingredientList;
    }

    public String getIngredient(int index) {
        return ingredientList[index];
    }

    public String[] getUnitList() {
        return unitList;
    }

    public String getUnit(int index) {
        return unitList[index];
    }

    public String[] getPriceList() {
        return priceList;
    }

    public String getPrice(int index) {
        return priceList[index];
    }

    public String toString(int index){
        return new String("(" + ingredientList[index] + " " + unitList[index] + " " + priceList[index] + ")");
    }
}
