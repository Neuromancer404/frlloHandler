package com.example.frllohandler.Tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JsonWriter {
    public JsonWriter(Map<String, String> data, String file){
        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write("{");
            int cnt = 1;
            for (Map.Entry<String, String> entry : data.entrySet()) {
                cnt++;
                writer.write("\""+entry.getKey()+"\"" + ":" + "\""+entry.getValue()+"\"");
                if(data.size() >= cnt){
                    writer.write(",");
                }
                else{
                    writer.write("");
                }
            }
            writer.write("}");

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
