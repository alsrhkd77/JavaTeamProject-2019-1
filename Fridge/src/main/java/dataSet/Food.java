package dataSet;

public class Food {
    private String foodName;
    private String foodUrl;
    private String foodImg;

    public Food(){
        foodName = "";
        foodUrl = "";
        foodImg = "";
    }

    public Food(String foodName, String foodUrl, String foodImg){
        this.foodName = foodName;
        this.foodUrl = foodUrl;
        this.foodImg = foodImg;
    }

    public Food getFood(){
        return this;
    }

    public String getFoodName(){
        return foodName;
    }

    public String getFoodUrl() {
        return foodUrl;
    }

    public String getFoodImg() {
        return foodImg;
    }
}
