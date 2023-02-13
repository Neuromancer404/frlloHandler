package com.example.frllohandler.FrlloTools;

import com.example.frllohandler.FrlloTools.FrlloPerson.Case1Person;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class DadataAdressParser {

    public static DadataAdressParserResult result;
    private String _URL = "https://cleaner.dadata.ru/api/v1/clean/address";
    private String _APIkey = "58f4203d83a2268cfcb532be7ca23f3580c1d570";
    private String _SecretKey = "6b3389c4c8d615174b1690bdfd6cd07166a9c807";
    public DadataAdressParser(FrlloCase1ParserResult frlloCase1HandlerResult) {
        result = new DadataAdressParserResult();
        List<Case1Person> personList = frlloCase1HandlerResult.getPersonList();
        for(Case1Person person : personList){
            String answer = sendPost(person.getFias_address());
            if(answer.length()>1){
                JsonReader readedData = new JsonReader(answer);
                Map<String, String> keyVal = readedData.getKeyVal();
                System.out.println(keyVal.get("source"));
            }
            break;
        }
    }

    private String sendPost(String fiasAddress) {
        int postDataLength = fiasAddress.length();
        String response="";
        try {
            URL url = new URL(_URL);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setRequestMethod("POST");
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty("Authorization", "Token "+_APIkey);
            conn.setRequestProperty("X-Secret", _SecretKey);
            conn.setDoOutput(true);

            String inputString = "[\""+fiasAddress+"\"]";
            byte[] input = inputString.getBytes("utf-8");

            try(OutputStream os = conn.getOutputStream()) {
                os.write(input, 0, input.length);
            }

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            for (int c; (c = in.read()) >= 0;){
                response += (char)c;
            }
        } catch (MalformedURLException e) {
            result.error = e.getMessage();
        } catch (IOException e) {
            result.error = e.getMessage();
        }
        return response;
    }
}
