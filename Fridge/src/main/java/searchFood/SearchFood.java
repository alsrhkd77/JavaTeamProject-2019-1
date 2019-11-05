package searchFood;

import dataSet.FoodSet;
import dataSet.IngredientList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The type Search food.
 */
public class SearchFood extends GetHtmlDoc{
    private String foodName;
    /**
     * Instantiates a new Search food.
     */
    public SearchFood(){
        foodName = "";
    }

    public SearchFood(String foodName){
        this.foodName = foodName;
    }

    @Override
    protected void makeUrl() {
        pageUrl = new StringBuilder("http://www.10000recipe.com/recipe/list.html?q=");
        if(foodName.equals("")){
            for (int i = 0; i < IngredientList.size(); i++) {
                if (i != 0) {
                    pageUrl.append("+");
                }
                pageUrl.append(IngredientList.getName(i));
            }
        }
        else{
            pageUrl.append(foodName);
        }
    }

    @Override
    protected void parseValue() {
        result1 = new ArrayList<String>(doc.select("h4.ellipsis_title2").eachText());  //현재 페이지의 요리 목록
        result2 = new ArrayList<String>(doc.select("div.col-xs-4").select("a.thumbnail").eachAttr("href")); //요리 상세 레시피 주소
        result3 = new ArrayList<String>(doc.select("div.col-xs-4").not("span").select("img").eachAttr("src"));  //요리 이미지
        Iterator<String> iter = result3.iterator();
        while(iter.hasNext()){
            String s = iter.next();
            if(s.contains("http://recipe1.ezmember.co.kr/img/thumb_over.png") || s.contains("http://recipe1.ezmember.co.kr/img/icon_vod.png")){
                iter.remove();
            }
        }
    }

    @Override
    public FoodSet getValue() {
        FoodSet foodSet = new FoodSet(result1, result2, result3);
        return foodSet.getFoodSet();
    }
}