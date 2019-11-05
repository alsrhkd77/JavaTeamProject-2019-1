package apiManager;

import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

public class FindFoodInfo extends GetInfo{
    private String foodName;    //입력한 이름
    private String recipeName;  //검색한 레시피 이름
    private String recipeId;    //레시피 코드

    public FindFoodInfo(){  //안써도될듯?
        key = ServiceKey.FindRecipeKey;
        connectApi();
    }

    public FindFoodInfo(String foodName){   //검색 할 요리이름 넣어주기
        this.foodName = foodName;
        key = ServiceKey.FindRecipeKey;
        connectApi();
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeId() {
        return recipeId;
    }

    @Override
    protected void makeUrl() {
        buildUrl = new StringBuilder("http://211.237.50.150:7080/openapi/");  //레시피 정보 URL
        buildUrl.append(key);
        buildUrl.append("/xml/Grid_20150827000000000226_1/1/537");
    }

    @Override
    protected void selectItem() {
        NodeList nodeList = null;
        try {
            XPathExpression expr = xPath.compile("/Grid_20150827000000000226_1//row"); //레시피
            nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
            for (int i=0;i<nodeList.getLength();i++){
                NodeList child = nodeList.item(i).getChildNodes();
                if(child.item(2).getTextContent().contains(foodName)){
                    recipeId = child.item(1).getTextContent();      //레시피 코드
                    recipeName = child.item(2).getTextContent();    //레시피 이름
                    //recipeSumry = child.item(3).getTextContent();   //레시피 요약설명
                    //calorie = child.item(9).getTextContent();       //음식 칼로리
                    //recipeImg = child.item(14).getTextContent();    //요리 이미지
                    break;
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            System.out.println("태그 찾기 실패");
        }
    }
}
