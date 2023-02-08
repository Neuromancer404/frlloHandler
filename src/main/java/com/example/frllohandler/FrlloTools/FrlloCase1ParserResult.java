package com.example.frllohandler.FrlloTools;

import com.example.frllohandler.FrlloTools.FrlloPerson.Case1Person;

import java.util.List;

public class FrlloCase1ParserResult {
    public String error=null;

    public int getNodeCount() {
        return nodeCount;
    }
    private int nodeCount;
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public List<Case1Person> getPersonList() {
        return personList;
    }
    private List<Case1Person> personList;
    public void setPersonList(List<Case1Person> personList) {
        this.personList = personList;
    }
}
