package searchFood;

import dataSet.RecipeSet;

import java.util.ArrayList;

public class RecipeInfo extends GetHtmlDoc {
    private String urlWords;

    public RecipeInfo(String urlWords) {
        this.urlWords = urlWords;
    }

    @Override
    protected void makeUrl() {
        pageUrl = new StringBuilder("http://www.10000recipe.com");
        pageUrl.append(urlWords);
    }

    @Override
    protected void parseValue() {
        result1 = new ArrayList<String>(doc.select(".ready_ingre3").select("li").eachText());    //재료명, 필요양 가져오기
        String[] ingre = doc.select(".view_cont").select(".cont_ingre").select("dd").text().split(","); //재료명, 필요양 가져오기
        for(String s:ingre){
            result1.add(s);
        }
        result2 = new ArrayList<String>(doc.select(".view_step").select(".media-body").eachText());  //레시피 과정 가져오기
        result3 = new ArrayList<String>(doc.select(".view_step").select(".media").select("img").eachAttr("src")); //레시피 과정 이미지 가져오기
    }

    @Override
    public RecipeSet getValue() {
        RecipeSet recipeSet = new RecipeSet(result1, result2, result3);
        return recipeSet.getRecipeSet();
    }
}
