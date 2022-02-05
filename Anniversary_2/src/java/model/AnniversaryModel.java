package model;

import java.util.*;

public class AnniversaryModel {
    private int year;
    private Map<Integer, String> anniversaryMap;
    
    public AnniversaryModel(){
        anniversaryMap = new HashMap<>(); 
        this.populateMap();
    }
    
    private void populateMap(){
        anniversaryMap.put(1, "紙");
        anniversaryMap.put(2, "棉花");
        anniversaryMap.put(3, "皮革");
        anniversaryMap.put(4, "絲綢");
        anniversaryMap.put(5, "木頭");
        anniversaryMap.put(6, "鐵");
        anniversaryMap.put(7, "羊毛");
        anniversaryMap.put(8, "青銅");
        anniversaryMap.put(9, "陶器");
        anniversaryMap.put(10, "錫");
        anniversaryMap.put(11, "鋼");
        anniversaryMap.put(12, "麻");
        anniversaryMap.put(13, "花邊");
        anniversaryMap.put(14, "象牙");
        anniversaryMap.put(15, "水晶");
        anniversaryMap.put(20, "瓷器");
        anniversaryMap.put(25, "銀");
        anniversaryMap.put(30, "珍珠");
        anniversaryMap.put(35, "珊瑚");
        anniversaryMap.put(40, "紅寶石");
        anniversaryMap.put(45, "藍寶石");
        anniversaryMap.put(50, "金");
        anniversaryMap.put(55, "翡翠");
        anniversaryMap.put(60, "鑽石");     
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String getMaterial(){
        String result = "??";
        if(anniversaryMap.containsKey(year)){
            result = anniversaryMap.get(year);
        }
        return result;
    }
}









