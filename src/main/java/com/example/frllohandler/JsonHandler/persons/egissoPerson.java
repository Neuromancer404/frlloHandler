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
    private String LMSZID;
    private String categoryID;
    protected String decision_date;
    protected String dateStart;
    protected String dateFinish;
    private String usingSign;
    private String amount;
    private String OKEICode;
    protected String monetization;
    private String lastChanging;

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


    public String getOSZCode() {
        return OSZCode;
    }

    public String getSNILS() {
        return SNILS;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public String getGender() {
        return Gender;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public String getLMSZID() {
        return LMSZID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getDecision_date() {
        return decision_date;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public String getUsingSign() {
        return usingSign;
    }

    public String getAmount() {
        return amount;
    }

    public String getOKEICode() {
        return OKEICode;
    }

    public String getMonetization() {
        return monetization;
    }

    public String getLastChanging() {
        return lastChanging;
    }
}
