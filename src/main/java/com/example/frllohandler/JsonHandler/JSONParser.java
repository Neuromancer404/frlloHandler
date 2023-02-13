package com.example.frllohandler.JsonHandler;

import com.example.frllohandler.Tools.JsonWriter;
import com.example.frllohandler.Tools.JsonReader;

import java.io.*;
import java.util.Map;

public class JSONParser {
    private ReaderResult readerResult = new ReaderResult();
    public ReaderResult parse(String path) {
        String fileContaisn = readerFle(path);
        if(!fileContaisn.equals("")){
            readConfig(fileContaisn);
            readerResult.setParserStatus(true);
            readerResult.setFirstStageURL(config.get("DowmloadAppointmentAdress"));
            readerResult.setSecondStageURL(config.get("downloadBenefitAdress"));
            readerResult.setThirhStageURL(config.get("DownloadReleaseAdress"));
            readerResult.setEgissoUrl(config.get("downloadEGISSO"));
            return readerResult;
        }
        else{
            readerResult.setParserStatus(false);
            return readerResult;
        }
    }
    private Map<String, String> config;

    public Map<String, String> getConfig(){
        return config;
    }
    private void readConfig(String data) {
        JsonReader reader = new JsonReader(data);
        config = reader.getKeyVal();
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
                container+=(char)c;
            }
        }
        catch(IOException ex){
            readerResult.setError(ex.getMessage());
        }
        return container;
    }

    public void writeConfig(Map<String, String> config, String fileName) {
        JsonWriter writer = new JsonWriter(config, fileName);
    }
}
