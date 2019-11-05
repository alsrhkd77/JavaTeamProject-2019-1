package apiManager;

import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Find recipe.
 */
public class FindRecipe extends GetInfo {
    private String recipeID;
    private List<String> cookList;     //조리 과정 리스트
    private List<String> cookImgList;  //조리 과정 사진

    /**
     * Instantiates a new Find recipe.
     */
    public FindRecipe(){
        key = ServiceKey.FindRecipeKey;
        connectApi();
    }

    public FindRecipe(String recipeID){
        this.recipeID = recipeID;
        key = ServiceKey.FindRecipeKey;
        connectApi();
    }

    public List<String> getCookImgList() {
        return cookImgList;
    }

    public List<String> getCookList() {
        return cookList;
    }

    @Override
    protected void makeUrl() {
        buildUrl = new StringBuilder("http://211.237.50.150:7080/openapi/");  //레시피 정보 URL
        buildUrl.append(key);
        buildUrl.append("/xml/Grid_20150827000000000228_1/1/5?RECIPE_ID=");
        buildUrl.append(recipeID); //레시피코드 넣어주기
    }

    @Override
    protected void selectItem() {
        NodeList nodeList = null;
        cookList = new ArrayList<String>();
        cookImgList = new ArrayList<String>();
        try {
            XPathExpression expr = xPath.compile("/Grid_20150827000000000228_1//row"); //상세 레시피
            nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
            for (int i=0;i<nodeList.getLength();i++){
                NodeList child = nodeList.item(i).getChildNodes();
                cookList.add(child.item(3).getTextContent());
                if(child.item(4).getTextContent().contains("http")){
                    cookImgList.add(child.item(4).getTextContent());
                }
                else{
                    cookImgList.add(new String("none"));
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            System.out.println("태그 찾기 실패");
        }
    }
}
