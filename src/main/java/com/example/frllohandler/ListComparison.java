package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.persons.egissoModifiedPerson;

import java.util.ArrayList;
import java.util.List;

public class ListComparison {
    public static int matchesNum=0;
    public static int newPersonsCount=0;
    public ListComparison(List<egissoModifiedPerson> personList, List<egissoModifiedPerson> lastMonthPersonList) {
        int count =0;
        List<egissoModifiedPerson> persons = new ArrayList<>();
        for(egissoModifiedPerson personNew : personList){
            for(egissoModifiedPerson personLast : lastMonthPersonList){
                String a = personNew.getSNILS_recip()+personNew.getDecision_date()+personNew.getDateFinish();
                String b = personLast.getSNILS_recip() + personLast.getDecision_date() + personLast.getDateFinish();
                if(a.equals(b))
                {
                    count++;
                }
                else{
                    if(!lastMonthPersonList.contains(personNew)){
                        persons.add(personNew);
                    }
                }
            }
        }
        matchesNum = count;
        newPersonsCount = persons.size();
    }
}
