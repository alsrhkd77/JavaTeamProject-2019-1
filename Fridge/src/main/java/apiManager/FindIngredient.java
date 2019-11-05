package apiManager;

import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

public class FindIngredient extends GetInfo {
    private String recipeID;
    private List<String> ingredientList;   //재료이름
    private List<String> cpctyList;    //재료 필요양
    private List<String> ingredient;

    public FindIngredient(){
        key = ServiceKey.FindRecipeKey;
        connectApi();
    }

    public FindIngredient(String recipeID){
        this.recipeID = recipeID;
        key = ServiceKey.FindRecipeKey;
        connectApi();
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    @Override
    protected void makeUrl() {
        buildUrl = new StringBuilder("http://211.237.50.150:7080/openapi/");  //레시피 정보 URL
        buildUrl.append(key);
        buildUrl.append("/xml/Grid_20150827000000000227_1/1/5?RECIPE_ID=");
        buildUrl.append(recipeID); //레시피코드 넣어주기
    }

    @Override
    protected void selectItem() {
        NodeList nodeList = null;
        ingredientList = new ArrayList<String>();
        cpctyList = new ArrayList<String>();
        ingredient = new ArrayList<String>();
        try {
            XPathExpression expr = xPath.compile("/Grid_20150827000000000227_1//row"); //상세 레시피
            nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
            for (int i=0;i<nodeList.getLength();i++){
                NodeList child = nodeList.item(i).getChildNodes();
                ingredientList.add(child.item(3).getTextContent()); //재료이름 넣어주기
                cpctyList.add(child.item(4).getTextContent());  //재료 필요한 양 넣어주기
            }
            for(int i=0;i<ingredientList.size();i++){
                ingredient.add(ingredientList.get(i) + " " + cpctyList.get(i));
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            System.out.println("태그 찾기 실패");
        }
    }
}
