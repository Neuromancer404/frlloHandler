package com.example.frllohandler.JsonHandler;

import com.example.frllohandler.JsonHandler.csvEditor.csvLineHandler;
import com.example.frllohandler.JsonHandler.persons.egissoModifiedPerson;
import com.example.frllohandler.JsonHandler.persons.egissoPerson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class egissoParser {
    private int nodeCount=0;
    public int getNodeCount(){return nodeCount;}
    private List<egissoPerson> personList = new ArrayList<>();
    public List<egissoModifiedPerson> modedPersonList = new ArrayList<>();
    public egissoParser(String file) throws FileNotFoundException{
        if(file.contains(".xml")){
            readXML(file);
        }else if(file.contains(".csv")){
            readCSV(file);
        }
    }

    private void readCSV(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            int n =0;
            while (line != null) {
                csvLineHandler lh = new csvLineHandler(line);
                if(lh._n == n){
                    if(n == 38){
                        egissoModifiedPerson modPers = lh.forkPerson(lh._arr);
                        modedPersonList.add(modPers);
                        nodeCount++;
                    }
                }else{
                    n = lh._n;
                }
                line = reader.readLine();
            }

            fr.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readXML(String file) throws FileNotFoundException{
        xmlReader xmlRdr = new xmlReader();
        try {
            try{
                nodeCount = xmlRdr.Create(file, "fact");
            }catch (FileNotFoundException ex){
                throw ex;
            }
            if(nodeCount>0){
                List<Map<String, String>> data = xmlRdr.Read("fact");
                for(Map<String, String> item : data){
                    egissoPerson person = new egissoPerson();
                    for(String key : item.keySet()){
                        person.setData(key, item.get(key));
                    }
                    personList.add(person);
                }
                modify(personList);
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<egissoModifiedPerson> modPersonList = new ArrayList<>();
    private void modify(List<egissoPerson> personList) {
        for(egissoPerson person : personList){
            egissoModifiedPerson prsn = new egissoModifiedPerson();
            prsn.setData(person);
            modPersonList.add(prsn);
        }
    }
}
