package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.persons.egissoModifiedPerson;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ListComparison {
    public static int matchesNum=0;
    public static int newPersonsCount=0;
    public ListComparison(List<egissoModifiedPerson> personList, List<egissoModifiedPerson> lastMonthPersonList) {
        int count =0;
        boolean check=false;
        Set<egissoModifiedPerson> persons = new LinkedHashSet<>();
        System.out.println(personList.size()+"  "+lastMonthPersonList.size());
        for(egissoModifiedPerson personNew : personList){
            check = false;
            String a = personNew.getSNILS_recip()+personNew.getDecision_date()+personNew.getDateFinish();
            for(egissoModifiedPerson personLast : lastMonthPersonList){
                String b = personLast.getSNILS_recip() + personLast.getDecision_date() + personLast.getDateFinish();
                if(a.equals(b))
                {
                    check = true;
                    count++;
                    break;
                }
            }
            if(check == false){
                persons.add(personNew);
            }
        }
        matchesNum = count;
        newPersonsCount = persons.size();
    }
}
