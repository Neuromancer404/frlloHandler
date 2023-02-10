package com.example.frllohandler.FrlloTools;

public class JsonReader {
    public JsonReader(String data) {
        data = removeBrackets(removeBrackets(data, '[', ']'), '{', '}');
        String[] dt = data.split(":");
        
    }

    private String removeBrackets(String data, char Br1, char Br2) {
        String newData=data;
        if(data.charAt(0)==Br1 && data.charAt(data.length()-1)==Br2){
            newData = data.substring(1, data.length()-2);
        }
        return newData;
    }
}
