package dataSet;

import java.util.Iterator;
import java.util.List;

public class FoodSet implements Aggregate {
    private Food[] foodList = new Food[30];;
    private int size;

    public FoodSet(){
        size = 0;
    }

    public FoodSet(String foodName, String foodUrl, String foodImg){
        size = 0;
        add(foodName, foodUrl, foodImg);
    }

    public FoodSet(List<String> foodName, List<String> foodUrl, List<String> foodImg){
        size = 0;
        add(foodName, foodUrl, foodImg);
    }

    public void add(String foodName, String foodUrl, String foodImg){
        foodList[size] = new Food(foodName, foodUrl, foodImg);
        size++;
    }

    public void add(List<String> foodName, List<String> foodUrl, List<String> foodImg){
        Iterator foodNameIter = foodName.iterator();
        Iterator foodUrlIter = foodUrl.iterator();
        Iterator foodImgIter = foodImg.iterator();

        while (foodNameIter.hasNext() && foodUrlIter.hasNext() && foodImgIter.hasNext()){
            String name = foodNameIter.next().toString();
            String url = foodUrlIter.next().toString();
            String img = foodImgIter.next().toString();
            foodList[size] = new Food(name, url, img);
            size++;
        }

    }

    public int size(){
        return size;
    }

    public FoodSet getFoodSet(){
        return this;
    }

    public Food getFood(int index){
        return foodList[index].getFood();
    }

    public String getFoodName(int index){
        return foodList[index].getFoodName();
    }

    public String getFoodUrl(int index){
        return foodList[index].getFoodUrl();
    }

    public String getFoodImg(int index){
        return foodList[index].getFoodImg();
    }

    public dataSet.Iterator iterator() {
        return new FoodIterator(this);
    }
}
