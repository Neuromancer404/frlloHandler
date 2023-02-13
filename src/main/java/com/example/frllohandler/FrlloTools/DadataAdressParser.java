package com.example.frllohandler.FrlloTools;

import com.example.frllohandler.FrlloTools.FrlloPerson.Case1Person;
import com.example.frllohandler.Tools.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DadataAdressParser {

    public static DadataAdressParserResult result;
    private String _URL;
    private String _APIkey;
    private String _SecretKey;
    public DadataAdressParser(FrlloCase1ParserResult frlloCase1HandlerResult, String secretKey, String APIkey, String URL) {
        _URL = URL;
        _APIkey = APIkey;
        _SecretKey = secretKey;

        result = new DadataAdressParserResult();
        List<Case1Person> personList = frlloCase1HandlerResult.getPersonList();
        for(Case1Person person : personList){
            String answer = sendPost(person.getFias_address());
            if(answer.length()>1){
                CompletableFuture<Map<String, String>> future = CompletableFuture.supplyAsync(() -> {
                    Map<String, String> keyVal = new HashMap<>();
                    try {
                        JsonReader readedData = new JsonReader(answer);
                        keyVal = readedData.getKeyVal();
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        result.error = e.getMessage();
                    }
                    return keyVal;
                });

                try {
                    Map<String, String> result = future.get();
                    if(result.get("fias_id")==null){
                        System.out.println("AAA");
                    }else{
                        person.setFias_address(result.get("fias_id"));
                    }
                } catch (InterruptedException e) {
                    result.error = e.getMessage();
                } catch (ExecutionException e) {
                    result.error = e.getMessage();
                }
            }
        }
    }

    private String sendPost(String fiasAddress) {
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
