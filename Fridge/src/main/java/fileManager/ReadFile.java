package fileManager;

import dataSet.Ingredient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Read file.
 */
public class ReadFile {
    /**
     * The Result text.
     */
    private String resultText;
    private List<Ingredient> resultList;
    private File f;

    /**
     * Instantiates a new Read file.
     */
    public ReadFile(){   //임시 생성자 >>지울 것(테스트용)

    }

    public List<Ingredient> read(String filename){
        resultList = null;
        f = new File("./data/" + filename + ".dat");
        checkFileExists();
        return readArrayList();
    }

    /**
     * Instantiates a new Read file.
     *
     * @param fileName the file name
     */
    public ReadFile(String fileName){   //filename = history / 재료
        resultText = "";
        f = new File("./data/"+ fileName + ".dat");
    }

    private void checkFileExists(){ //파일 존재여부 확인
        if(!f.exists()){    //파일이 존재하지 않을 경우
            try {
                f.createNewFile();  //파일 생성
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("파일 생성 실패");
            }
        }
    }

    private String readFileText() {  //텍스트 파일 내용 읽어들이기 - String 으로 내용 반환
        StringBuilder result = new StringBuilder("");

        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(f));

            String line = "";
            while ((line = buff.readLine()) != null) {
                result.append(line.trim() + "\r\n");
            }
            buff.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileReader err!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("readLine err!");
        } finally {
            try {
                buff.close();
            } catch (IOException e) {
                e.printStackTrace();
                //TODO: 버퍼가 null을 참조하고있어 close 실패
            }
        }
        return result.toString();
    }

    private List<Ingredient> readArrayList(){
        List<Ingredient> result = null;
        ObjectInputStream oIn = null;
        BufferedInputStream bIn = null;
        FileInputStream fIn = null;
        try {
            fIn = new FileInputStream(f);
            bIn = new BufferedInputStream(fIn);
            oIn = new ObjectInputStream(bIn);
            result = (List<Ingredient>) oIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: 파일 열기 실패
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //TODO: 파일에서 객체 읽어들이기 실패
        }
        finally {
            try {
                oIn.close();
            } catch (IOException e) {
                e.printStackTrace();
                //TODO: 버퍼가 null을 참조하고있어 close실패
            }
        }
        return result;
    }
}
