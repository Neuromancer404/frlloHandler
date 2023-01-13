package com.example.frllohandler.JsonHandler.persons;

import java.net.ProtocolException;

public class egissoPerson {
    protected String OSZCode;
    protected String SNILS;
    protected String FamilyName;
    protected String FirstName;
    protected String Patronymic;
    protected String Gender;
    protected String BirthDate;
    protected String LMSZID;
    protected String categoryID;
    protected String decision_date;
    protected String dateStart;
    protected String dateFinish;
    protected String usingSign;
    protected String amount;
    protected String OKEICode;
    protected String monetization;
    protected String lastChanging;

    public void setData(String key, String value){
        switch (key){
            case "OSZCode":
                OSZCode = value;
                break;
            case "SNILS":
                SNILS = value;
                break;
            case "FamilyName":
                FamilyName = value;
                break;
            case "FirstName":
                FirstName = value;
                break;
            case "Patronymic":
                Patronymic = value;
                break;
            case "Gender":
                Gender = value;
                break;
            case "BirthDate":
                BirthDate = value;
                break;
            case "LMSZID":
                LMSZID = value;
                break;
            case "categoryID":
                categoryID = value;
                break;
            case "decision_date":
                decision_date = value;
                break;
            case "dateStart":
                dateStart = value;
                break;
            case "dateFinish":
                dateFinish = value;
                break;
            case "usingSign":
                usingSign = value;
                break;
            case "amount":
                amount = value;
                break;
            case "OKEICode":
                OKEICode = value;
                break;
            case "monetization":
                monetization = value;
                break;
            case "lastChanging":
                lastChanging = value;
                break;
        }
    }

}
