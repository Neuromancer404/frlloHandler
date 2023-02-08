package com.example.frllohandler.FrlloTools.FrlloPerson;

public class Case1Person {
    public String getDocument_id() {
        return document_id;
    }

    public String getDoc_date_time() {
        return doc_date_time;
    }

    public String getRegister_id() {
        return register_id;
    }

    public String getExt_citizen_id() {
        return ext_citizen_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstsurname() {
        return firstsurname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getSex() {
        return sex;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getSnils() {
        return snils;
    }

    public String getPolicy_sn() {
        return policy_sn;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress_type_code() {
        return address_type_code;
    }

    public String getFias_address() {
        return fias_address;
    }

    public String getFias_home() {
        return fias_home;
    }

    public String getHouse_num() {
        return house_num;
    }

    public String getBuild_num() {
        return build_num;
    }

    public String getStruc_num() {
        return struc_num;
    }

    public String getRoom_num() {
        return room_num;
    }

    public String getLive_start_date() {
        return live_start_date;
    }

    public String getBenefit_code() {
        return benefit_code;
    }

    public String getExt_benefit_code() {
        return ext_benefit_code;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getReceive_date() {
        return receive_date;
    }

    public String getCancel_date() {
        return cancel_date;
    }

    private String document_id;
    private String doc_date_time;
    private String register_id;
    private String ext_citizen_id;
    private String name;
    private String surname;
    private String lastname;
    private String firstsurname;
    private String birthdate;
    private String sex;
    private String citizenship;
    private String snils;
    private String policy_sn;
    private String region;
    private String address;
    private String address_type_code;
    private String fias_address;
    private String fias_home;
    private String house_num;
    private String build_num;
    private String struc_num;
    private String room_num;
    private String live_start_date;
    private String benefit_code;
    private String ext_benefit_code;
    private String diagnosis;
    private String receive_date;
    private String cancel_date;

    public void setData(String key, String value) {
        switch (key){
            case "document_id":
                document_id = value;
                break;
            case "doc_date_time":
                doc_date_time= value;
                break;
            case "register_id":
                register_id= value;
                break;
            case "ext_citizen_id":
                ext_citizen_id= value;
                break;
            case "name":
                name= value;
                break;
            case "surname":
                surname= value;
                break;
            case "lastname":
                lastname= value;
                break;
            case "firstsurname":
                firstsurname= value;
                break;
            case "birthdate":
                birthdate= value;
                break;
            case "sex":
                sex= value;
                break;
            case "citizenship":
                citizenship= value;
                break;
            case "snils":
                snils= value;
                break;
            case "policy_sn":
                policy_sn= value;
                break;
            case "region":
                region= value;
                break;
            case "address":
                address= value;
                break;
            case "address_type_code":
                address_type_code= value;
                break;
            case "fias_address":
                fias_address= value;
                break;
            case "fias_home":
                fias_home= value;
                break;
            case "house_num":
                house_num= value;
                break;
            case "build_num":
                build_num= value;
                break;
            case "struc_num":
                struc_num= value;
                break;
            case "room_num":
                room_num= value;
                break;
            case "live_start_date":
                live_start_date= value;
                break;
            case "benefit_code":
                benefit_code= value;
                break;
            case "ext_benefit_code":
                ext_benefit_code= value;
                break;
            case "diagnosis":
                diagnosis= value;
                break;
            case "receive_date":
                receive_date= value;
                break;
            case "cancel_date":
                cancel_date= value;
                break;
        }
    }
}
