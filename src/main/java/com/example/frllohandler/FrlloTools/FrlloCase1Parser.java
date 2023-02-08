package com.example.frllohandler.FrlloTools;

import com.example.frllohandler.FrlloTools.FrlloPerson.Case1Person;
import com.example.frllohandler.JsonHandler.csvEditor.csvLineHandler;
import com.example.frllohandler.JsonHandler.persons.egissoModifiedPerson;
import com.example.frllohandler.JsonHandler.persons.egissoPerson;
import com.example.frllohandler.JsonHandler.xmlReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrlloCase1Parser {

    public FrlloCase1ParserResult getFrlloCase1ParserResult() {
        return frlloCase1ParserResult;
    }

    private FrlloCase1ParserResult frlloCase1ParserResult;
    private List<Case1Person> personList = new ArrayList<>();
    private int nodeCount=0;
    public FrlloCase1Parser(String file) {
        xmlReader xmlRdr = new xmlReader();
        frlloCase1ParserResult = new FrlloCase1ParserResult();
        try{
            nodeCount = xmlRdr.Create(file, "document");
            if(nodeCount>0){
                List<Map<String, String>> data = xmlRdr.Read("document");
                for(Map<String, String> item : data){
                    Case1Person person = new Case1Person();
                    for(String key : item.keySet()){
                        person.setData(key, item.get(key));
                    }
                    personList.add(person);
                }
            }
            frlloCase1ParserResult.setNodeCount(nodeCount);
            frlloCase1ParserResult.setPersonList(personList);
        } catch (FileNotFoundException e) {
            frlloCase1ParserResult.error = e.getMessage();
        } catch (IOException e) {
            frlloCase1ParserResult.error = e.getMessage();
        }

    }
}
