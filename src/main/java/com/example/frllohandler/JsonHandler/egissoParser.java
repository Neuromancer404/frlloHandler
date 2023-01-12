package com.example.frllohandler.JsonHandler;

import com.example.frllohandler.JsonHandler.persons.egissoPerson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class egissoParser {
    private int nodeCount;
    public int getNodeCount(){return nodeCount;}
    private List<egissoPerson> personList = new ArrayList<>();
    public egissoParser(String file){
        xmlReader xmlRdr = new xmlReader();
        try {
            nodeCount = xmlRdr.Create(file, "fact");
            if(nodeCount>0){
                List<Map<String, String>> data = xmlRdr.Read("fact");
                for(Map<String, String> item : data){
                    egissoPerson person = new egissoPerson();
                    for(String key : item.keySet()){
                        person.setData(key, item.get(key));
                    }
                    personList.add(person);
                }
                show(personList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void show(List<egissoPerson> personList) {
        for(egissoPerson person : personList){
            System.out.println(person.getFirstName());
        }
    }
}
