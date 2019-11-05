package searchFood;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class GetHtmlDoc {
    protected List<String> result1; //결과 저장할 객체
    protected List<String> result2; //결과 저장할 객체
    protected List<String> result3; //결과 저장할 객체
    protected StringBuilder pageUrl;    //Url 만들 변수
    protected Document doc; //받아온 html 저장 변수

    public GetHtmlDoc(){
    }

    public void run(){
        makeUrl();
        getHtml();
        if(zeroCheck()){
            parseValue();
        }
        else{
            System.out.println("결과 없음");
        }
    }

    protected abstract void makeUrl();

    private void getHtml(){
        doc = new Document("");
        try {
            doc = Jsoup.connect(pageUrl.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Logging - html파싱 실패
        }
    }

    protected abstract void parseValue();

    //검색 결과가 있는지 검색(결과 있을 경우 true, 없을경우 false)
    private Boolean zeroCheck(){
        Boolean result = true;
        String getValue = doc.select("div").html();  //원하는거 찾아서 html형식으로 getValue에 저장

        if(doc.equals("") || getValue.contains("result_none")){
            result = false;
            //TODO: Logging-검색결과 없음
        }

        return result;
    }

    public abstract Object getValue();

}
