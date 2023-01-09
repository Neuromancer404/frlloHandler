package com.example.frllohandler.JsonHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {
    public ReaderResult parse(String path) {
        String fileContaisn = readerFle(path);
        ReaderResult rdrRes = new ReaderResult();
        if(!fileContaisn.equals("")){
            readConfig(path);
            rdrRes.setParserStatus(true);
            rdrRes.setFirstStageURL(config.get("DowmloadAppointmentAdress"));
            return rdrRes;
        }
        else{
            rdrRes.setParserStatus(false);
            return rdrRes;
        }
    }
    private Map<String, String> config;

    public Map<String, String> getConfig(){
        return config;
    }
    private void readConfig(String path) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            config = new HashMap<>();
            while (line != null) {
                if(line.contains("\":\"")){
                    String[] keyValue = readKeyValFromLine(line);
                    config.put(keyValue[0], keyValue[1]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] readKeyValFromLine(String line) {
        String[] kv = line.split("\":\"");
        kv[0] = deleteExcessSymbols(kv[0]);
        kv[1] = deleteExcessSymbols(kv[1]);

        return kv;
    }

    private String deleteExcessSymbols(String s) {
        s = s.replace("\"", "");
        s = s.replace("\t", "");
        s = s.replace(",", "");
        s = s.replace("\n", "");
        return s;
    }

    private String readerFle(String path) {
        String container = "";
        try(FileReader reader = new FileReader(path))
        {
            int c;
            while((c=reader.read())!=-1){
                container += (char)c;
            }
        }
        catch(FileNotFoundException ex){
            File f = new File(path);
            try {
                if (f.createNewFile())
                    System.out.println("File created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return container;
    }

    public void writeConfig(Map<String, String> config) {
        try(FileWriter writer = new FileWriter("config.json", false))
        {
            writer.write("{\n");
            int cnt = 1;
            for (Map.Entry<String, String> entry : config.entrySet()) {
                cnt++;
                writer.write("\t\""+entry.getKey()+"\"" + ":" + "\""+entry.getValue()+"\"");
                if(config.size() >= cnt){
                    writer.write(",\n");
                }
                else{
                    writer.write("\n");
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
