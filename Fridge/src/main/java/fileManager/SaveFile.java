package fileManager;

import dataSet.Ingredient;

import java.io.*;
import java.util.List;

/**
 * The type Save file.
 */
public class SaveFile {
    private File f; //파일을 열 객체

    public SaveFile(){
    }

    public void setFile(String fileName, String valueText){
        f = new File("./data/" + fileName + ".dat");
        checkFileExists();
        saveText(valueText);
    }

    public void setFile(String fileName, List<Ingredient> valueList){
        f = new File("./data/" + fileName + ".dat");
        checkFileExists();
        saveArrayList(valueList);
    }

    private void checkFileExists(){ //파일 존재여부 확인
        if(!f.exists()){    //파일이 존재하지 않을 경우
            try {
                f.createNewFile();  //파일 생성
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveText(String valueText){    //String을 저장하기 -- 저장할 내용 넣어주기
        if(valueText != null) {
            BufferedWriter buff = null;
            try {
                buff = new BufferedWriter(new FileWriter(f));
                buff.write(valueText);
                buff.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("FileWriter err!");
                //TODO: 파일열기 실패
            } finally {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //TODO: 버퍼가 null을 참조하고있어 close 실패
                }
            }
        }
        else{
            System.out.println("String valueText point null!");
        }
    }

    private void saveArrayList(List<Ingredient> valueList){    //ArrayList를 저장하기
        FileOutputStream fOut = null;
        BufferedOutputStream bOout = null;
        ObjectOutputStream oOut = null;

        try {
            fOut = new FileOutputStream(f);
            bOout = new BufferedOutputStream(fOut);
            oOut = new ObjectOutputStream(bOout);

            oOut.writeObject(valueList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("파일 오픈 실패");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("출력 실패");
        } finally {
            try {
                oOut.close();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("버퍼가 null을 참조하고있어 close 실패");
            }
        }
    }

}

