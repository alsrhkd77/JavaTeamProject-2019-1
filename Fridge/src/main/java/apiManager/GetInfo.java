package apiManager;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Get info.
 */
/*팩토리 패턴*/
public abstract class GetInfo {
    /**
     * The Key.
     */
    protected String key;
    /**
     * The Build url.
     */
    protected StringBuilder buildUrl;

    protected XPath xPath = null;
    protected Document doc = null;

    /**
     * Instantiates a new Get info.
     */
    protected GetInfo(){
    }

    /**
     * Make url.
     */
    protected abstract void makeUrl();  //요청URL 생성

    /**
     * Select item.
     */
    protected abstract void selectItem();   //원하는 값 꺼내오기

    /**
     * Connect api.
     */
    protected void connectApi(){
        makeUrl();
        request();
        selectItem();
    }

    private void request(){
        StringBuilder resultUrl;
        try {
            URL url = new URL(buildUrl.toString());
            HttpURLConnection urlconnection =(HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String line;
            resultUrl = new StringBuilder("");
            while ((line = br.readLine()) != null) {
                resultUrl.append(line.trim());
            }

            DocumentBuilder builder;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            builder =factory.newDocumentBuilder();

            doc = builder.parse(new InputSource(new StringReader(resultUrl.toString())));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            xPath = xPathFactory.newXPath();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException 발생");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("paser가 문서 가져오기 실패");
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println("실패");
        }
    }
}
