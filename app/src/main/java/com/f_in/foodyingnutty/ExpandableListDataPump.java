package com.f_in.foodyingnutty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//this is to create data that shows in the expandable list
public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        //adding the child list
        List<String> listItem1 = new ArrayList<String>();
        listItem1.add("Munchee Super Cream Cracker - 190g");
        listItem1.add("Revello Cashew - 170g");

        List<String> listItem2 = new ArrayList<String>();
        listItem2.add("");

        List<String> listItem3 = new ArrayList<String>();
        listItem3.add("");

        List<String> listItem4 = new ArrayList<String>();
        listItem4.add("");

        List<String> listItem5 = new ArrayList<String>();
        listItem5.add("");

        List<String> listItem6 = new ArrayList<String>();
        listItem6.add("");
        //adding the headers
        expandableListDetail.put("Snack & Confectionery", listItem1);
        expandableListDetail.put("Beverages", listItem2);
        expandableListDetail.put("Dairy", listItem3);
        expandableListDetail.put("Frozen", listItem4);
        expandableListDetail.put("Meat", listItem5);
        expandableListDetail.put("Desserts", listItem6);
        return expandableListDetail;
    }
}