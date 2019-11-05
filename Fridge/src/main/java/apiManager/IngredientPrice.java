package apiManager;

import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

/**
 * The type Ingredient price.
 */
public class IngredientPrice extends GetInfo {
    private String ingredient;  //재료명
    private String unit;        //단위 무게
    private String price;    //가격

    public IngredientPrice(){
        unit = "";
        price = "가격 정보 없음";
        key = ServiceKey.IngredientPriceKey;
        connectApi();
    }

    public IngredientPrice(String ingredient){
        unit = "";
        price = "가격 정보 없음";
        this.ingredient = ingredient.substring(0,ingredient.indexOf(" "));
        key = ServiceKey.IngredientPriceKey;
        connectApi();
    }

    public String getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }

    @Override
    protected void makeUrl() {
        buildUrl = new StringBuilder("http://www.kamis.co.kr/service/price/xml.do?action=dailySalesList");  //재료가격 URL
        buildUrl.append("&p_cert_key=");
        buildUrl.append(key);
        buildUrl.append("&p_cert_id=alsrhkd77&p_returntype=xml");
    }

    @Override
    protected void selectItem() {
        NodeList nodeList = null;
        try {
            XPathExpression expr = xPath.compile("/document//price//item");   //재료가격
            nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
            for (int i=0;i<nodeList.getLength();i++){
                NodeList child = nodeList.item(i).getChildNodes();
                if(child.item(1).getTextContent().equals("소매") && child.item(6).getTextContent().contains(ingredient)){
                    unit = child.item(8).getTextContent() + "당";   //단위 무게
                    price = child.item(10).getTextContent() + "원"; //가격
                    break;
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            System.out.println("태그 찾기 실패");
            //TODO: Logging
        }
    }
}
