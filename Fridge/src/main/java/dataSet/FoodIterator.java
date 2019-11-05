package dataSet;

public class FoodIterator implements Iterator {
    private FoodSet food;
    private int index;

    public FoodIterator(FoodSet food){
        this.food = food;
        index = 0;
    }

    public boolean hasNext() {
        boolean result = false;
        if(index<food.size()){
            result = true;
        }
        return result;
    }

    public Food next() {
        Food food = this.food.getFood(index);
        index++;
        return food;
    }
}
