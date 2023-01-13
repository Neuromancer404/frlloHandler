package com.example.frllohandler.JsonHandler.persons;

public class egissoModifiedPerson extends egissoPerson{
    private String RecType = "Fact";
    private String LMSZID = "f4849e7f-4a90-433f-8c88-05e05807f8df";
    private String categoryID = "9fd55f95-2ccf-4b69-84d2-b6527068527a";
    private String ONMSZCode;
    private String SNILS_recip;
    private String FamilyName_recip;
    private String Name_recip;
    private String Patronymic_recip;
    private String Gender_recip;
    private String BirthDate_recip;
    private String doctype_recip;
    private String doc_Series_recip;
    private String doc_Number_recip;
    private String doc_IssueDate_recip;
    private String doc_Issuer_recip;
    private String SNILS_reason;
    private String FamilyName_reason;
    private String Name_reason;
    private String Patronymic_reason;
    private String Gender_reason;
    private String BirthDate_reason;
    private String doctype_reason;
    private String doc_Series_reason;
    private String doc_Number_reason;
    private String doc_IssueDate_reason;
    private String doc_Issuer_reason;
    private String decision_date;
    private String dateStart;
    private String dateFinish;
    private String usingSign;
    private String criteria;
    private String FormCode = "02";
    private String amount = "0";
    private String measuryCode = "03";
    private String monetization;
    private String content;
    private String comment;
    private String equivalentAmount = "0";

    public void setData(String key, String value){
        switch (key){

        }
    }

    public void setData(egissoPerson person) {
        FamilyName_recip = person.FamilyName;
        Name_recip = person.FirstName;
        Patronymic_recip = person.Patronymic;
        BirthDate_recip = formatData(person.BirthDate);
        ONMSZCode = person.OSZCode;
        SNILS_recip = person.SNILS;

        if(person.Gender.equals("Male")){
            Gender = "М";
        }else{
            Gender = "Ж";
        }

        decision_date = formatData(person.decision_date);
        dateStart = formatData(person.dateStart);
        dateFinish = formatData(person.dateFinish);
    }

    private String formatData(String date) {
        if(date == null){
            return "";
        }
        String[] dates = date.split("-");
        return dates[2]+"."+dates[1]+"."+dates[0];
    }

    public String getRecType() {
        return RecType;
    }

    public String getLMSZID() {
        return LMSZID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getONMSZCode() {
        return ONMSZCode;
    }

    public String getSNILS_recip() {
        return SNILS_recip;
    }

    public String getFamilyName_recip() {
        return FamilyName_recip;
    }

    public String getName_recip() {
        return Name_recip;
    }

    public String getPatronymic_recip() {
        return Patronymic_recip;
    }

    public String getGender_recip() {
        return Gender_recip;
    }

    public String getBirthDate_recip() {
        return BirthDate_recip;
    }

    public String getDoctype_recip() {
        return doctype_recip;
    }

    public String getDoc_Series_recip() {
        return doc_Series_recip;
    }

    public String getDoc_Number_recip() {
        return doc_Number_recip;
    }

    public String getDoc_IssueDate_recip() {
        return doc_IssueDate_recip;
    }

    public String getDoc_Issuer_recip() {
        return doc_Issuer_recip;
    }

    public String getSNILS_reason() {
        return SNILS_reason;
    }

    public String getFamilyName_reason() {
        return FamilyName_reason;
    }

    public String getName_reason() {
        return Name_reason;
    }

    public String getPatronymic_reason() {
        return Patronymic_reason;
    }

    public String getGender_reason() {
        return Gender_reason;
    }

    public String getBirthDate_reason() {
        return BirthDate_reason;
    }

    public String getDoctype_reason() {
        return doctype_reason;
    }

    public String getDoc_Series_reason() {
        return doc_Series_reason;
    }

    public String getDoc_Number_reason() {
        return doc_Number_reason;
    }

    public String getDoc_IssueDate_reason() {
        return doc_IssueDate_reason;
    }

    public String getDoc_Issuer_reason() {
        return doc_Issuer_reason;
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

    public String getCriteria() {
        return criteria;
    }

    public String getFormCode() {
        return FormCode;
    }

    public String getAmount() {
        return amount;
    }

    public String getMeasuryCode() {
        return measuryCode;
    }

    public String getMonetization() {
        return monetization;
    }

    public String getContent() {
        return content;
    }

    public String getComment() {
        return comment;
    }

    public String getEquivalentAmount() {
        return equivalentAmount;
    }
}
