package dataSet;

public class FoodBuilder implements Builder {
    private String foodName;
    private String foodUrl;
    private String foodImg;

    public FoodBuilder(){
        foodName = "";
        foodUrl = "";
        foodImg = "";
    }

    public void foodName(String foodName){
        this.foodName = foodName;
    }

    public void foodUrl(String foodUrl){
        this.foodUrl = foodUrl;
    }

    public void sFoodImg(String foodImg){
        this.foodImg = foodImg;
    }

    @Override
    public Food build() {
        return new Food(foodName, foodUrl, foodImg);
    }
}

