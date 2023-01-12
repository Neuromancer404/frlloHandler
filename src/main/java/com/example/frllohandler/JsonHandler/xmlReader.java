package com.example.frllohandler.JsonHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xmlReader {
    public int RootTagCount;
    private String _Path;
    private String _rootTag;
    private String _stratcherInfo;
    private String[] nodeNline = new String[2];
    public int Create(String Path, String rootTag) throws IOException {
        _Path = Path;
        _rootTag = rootTag;
        int i = 0, j = 0;
        File file = new File(Path);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            if(line.contains("<"+_rootTag+">")){
                i++;
            }
            if(line.contains("</"+_rootTag+">")){
                j++;
            }
            line = reader.readLine();
        }
        if(i==j){
            RootTagCount = i;
        }
        return RootTagCount;
    }
    public List<Map<String, String>> Read(String tag) throws IOException {
        List<Map<String, String>> lines = new ArrayList<>();

        boolean check = false;
        File file = new File(_Path);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            if(tag != null && line.contains("<"+tag+">")&& line.contains("</"+tag+">")){
                String[] answr = lineReader(line, 0, "<",">");
                _stratcherInfo = answr[1];
            }
            else {
                Map<String, String> dict = new HashMap<>();
                if(line.contains("<"+_rootTag+">")){
                    while(line != null){
                        if(line.contains("</"+_rootTag+">")){
                            break;
                        }
                        String[] a = lineReader(line, 0, "<", ">");
                        if(a[1]!= "" && a[1]!=null){
                            dict.put(a[0], a[1]);
                        }
                        nodeNline[0] = ""; nodeNline[1]="";
                        line = reader.readLine();
                    }
                    lines.add(dict);
                }
            }
            line = reader.readLine();
        }

        return lines;
    }

    private String[] lineReader(String line, int index, String nodeBegin, String nodeEnd) {
        int fu = line.indexOf(nodeBegin);
        int su = line.indexOf(nodeEnd);
        String data = "";

        for(int i = fu+1;i<su;i++){
            data += line.charAt(i);
        }
        nodeNline[index] = data;
        if(line.contains("</"+data+">")){
            lineReader(line, 1, ">", "</"+data+">");
        }
        return nodeNline;
    }
}
