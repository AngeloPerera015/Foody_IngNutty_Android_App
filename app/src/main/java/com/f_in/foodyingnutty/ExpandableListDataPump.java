package com.f_in.foodyingnutty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> listItem1 = new ArrayList<String>();
        listItem1.add("Munchee Super Cream Cracker - 190g");
        listItem1.add("Revello Cashew - 170g");
        listItem1.add("Tiara Chocolate Sponge Layer Cake - 480g");
        listItem1.add("Maliban Chocolate Cream Biscuit - 400g");
        listItem1.add("Snickers Chocolate - 51g");
        listItem1.add("Cadbury Dairy Milk, Hazelnut - 160g");
        listItem1.add("Kist Magic Cho-co -160g");
        listItem1.add("Samaposha - 200g");
        listItem1.add("Magna Jumbo Peanuts - 80g");
        listItem1.add("Rancrisp Cassava Chips Hot & Spicy - 100g");

        List<String> listItem2 = new ArrayList<String>();
        listItem2.add("");
        listItem2.add("");
        listItem2.add("");
        listItem2.add("");
        listItem2.add("");

        List<String> listItem3 = new ArrayList<String>();
        listItem3.add("");
        listItem3.add("");
        listItem3.add("");
        listItem3.add("");
        listItem3.add("");

        List<String> listItem4 = new ArrayList<String>();
        listItem4.add("");
        listItem4.add("");
        listItem4.add("");
        listItem4.add("");
        listItem4.add("");

        List<String> listItem5 = new ArrayList<String>();
        listItem5.add("");
        listItem5.add("");
        listItem5.add("");
        listItem5.add("");
        listItem5.add("");

        List<String> listItem6 = new ArrayList<String>();
        listItem6.add("");
        listItem6.add("");
        listItem6.add("");
        listItem6.add("");
        listItem6.add("");

        expandableListDetail.put("Snack & Confectionery", listItem1);
        expandableListDetail.put("Beverages", listItem2);
        expandableListDetail.put("Dairy", listItem3);
        expandableListDetail.put("Frozen", listItem4);
        expandableListDetail.put("Meat", listItem5);
        expandableListDetail.put("Desserts", listItem6);
        return expandableListDetail;
    }
}
