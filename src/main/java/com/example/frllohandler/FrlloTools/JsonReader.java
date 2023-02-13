package com.example.frllohandler.FrlloTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonReader {
    private Map<String, String> keyVal = new HashMap<>();

    public Map<String, String> getKeyVal() {
        return keyVal;
    }

    public JsonReader(String data) {
        data = removeBrackets(removeBrackets(data, '[', ']'), '{', '}');
        char[] arr = data.toCharArray();
        //Чтение ключей
        List<String> keyArr = new ArrayList<>();
        for(int i=0; i< arr.length;i++){
            String key = "";
            if(arr[i]=='"'&& arr[++i]==':'){
                int j = i-2;
                while(arr[j]!='"'){
                    j=j-1;
                }
                for(int n = j+1; n<= i-2;n++){
                    key+=arr[n];
                }
            }
            if(key!=""){
                keyArr.add(key);
            }
        }
        //Чтение значений
        for(int i =0;i<keyArr.size();i++){
            String val="";
            String k1 = "\""+keyArr.get(i)+"\":";
            int inx1 = data.indexOf(k1)+k1.length();
            int inx2 = data.length();
            if(i != keyArr.size()-1){
                String k2 = "\""+keyArr.get(i+1)+"\":";
                inx2 = data.indexOf(k2);
            }
            val = data.substring(inx1, inx2);
            char[] valArr = val.toCharArray();
            if(valArr[valArr.length-1]==','){
                val = val.substring(0, val.length()-1);
            }
            val = removeBrackets(val, '"', '"');
            System.out.println(keyArr.get(i)+":  "+val);
            keyVal.put(keyArr.get(i), val);
        }
    }

    private String removeBrackets(String data, char Br1, char Br2) {
        String newData=data;
        if(data.charAt(0)==Br1 && data.charAt(data.length()-1)==Br2){
            newData = data.substring(1, data.length()-1);
        }
        return newData;
    }
}
