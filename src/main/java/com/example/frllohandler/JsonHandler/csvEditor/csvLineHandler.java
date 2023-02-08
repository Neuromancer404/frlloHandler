package com.example.frllohandler.JsonHandler.csvEditor;

import com.example.frllohandler.JsonHandler.persons.egissoModifiedPerson;

import java.util.HashMap;
import java.util.Map;

public class csvLineHandler {
    private String _separator = ";";
    public static int _n = 0;
    public static String[] _arr;
    public csvLineHandler(String line) {
        _n = wordCounter(line);
    }
    private int wordCounter(String line) {
        _arr = line.split(_separator);
        return _arr.length;
    }

    public egissoModifiedPerson forkPerson(String[] persData) {
        Map<String, String> data = new HashMap<>();
        egissoModifiedPerson prsn = new egissoModifiedPerson();
        prsn.setData("RecType", persData[0]);
        prsn.setData("LMSZID", persData[1]);
        prsn.setData("categoryID", persData[2]);
        prsn.setData("ONMSZCode", persData[3]);
        prsn.setData("SNILS_recip", persData[4]);
        prsn.setData("FamilyName_recip", persData[5]);
        prsn.setData("Name_recip", persData[6]);
        prsn.setData("Patronymic_recip", persData[7]);
        prsn.setData("Gender_recip", persData[8]);
        prsn.setData("BirthDate_recip", persData[9]);
        prsn.setData("doctype_recip", persData[10]);
        prsn.setData("doc_Series_recip", persData[11]);
        prsn.setData("doc_Number_recip", persData[12]);
        prsn.setData("doc_IssueDate_recip", persData[13]);
        prsn.setData("doc_Issuer_recip", persData[14]);
        prsn.setData("SNILS_reason", persData[15]);
        prsn.setData("FamilyName_reason", persData[16]);
        prsn.setData("Name_reason", persData[17]);
        prsn.setData("Patronymic_reason", persData[18]);
        prsn.setData("Gender_reason", persData[19]);
        prsn.setData("BirthDate_reason", persData[20]);
        prsn.setData("doctype_reason", persData[21]);
        prsn.setData("doc_Series_reason", persData[22]);
        prsn.setData("doc_Number_reason", persData[23]);
        prsn.setData("doc_IssueDate_reason", persData[24]);
        prsn.setData("doc_Issuer_reason", persData[25]);
        prsn.setData("decision_date", persData[26]);
        prsn.setData("dateStart", persData[27]);
        prsn.setData("dateFinish", persData[28]);
        prsn.setData("usingSign", persData[29]);
        prsn.setData("criteria", persData[30]);
        prsn.setData("FormCode", persData[31]);
        prsn.setData("amount", persData[32]);
        prsn.setData("measuryCode", persData[33]);
        prsn.setData("monetization", persData[34]);
        prsn.setData("content", persData[35]);
        prsn.setData("comment", persData[36]);
        prsn.setData("equivalentAmount", persData[37]);

        return prsn;
    }
}
