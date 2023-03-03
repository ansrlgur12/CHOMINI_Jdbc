package com.kh.jdbc.vo;
import java.sql.Date;
public class TempBasketVO {

    private int tempOrderNo;
    private String menuName;
    private String size;
    private String bread;
    private String cheese;
    private String vegetable;
    private String sauce;
    private int howMany;
    private int total;

    public TempBasketVO(int tempOrderNo, String menuName, String size, String bread, String cheese, String vegetable, String sauce, int howMany, int total) {
        this.tempOrderNo = tempOrderNo;
        this.menuName = menuName;
        this.size = size;
        this.bread = bread;
        this.cheese = cheese;
        this.vegetable = vegetable;
        this.sauce = sauce;
        this.howMany = howMany;
        this.total = total;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        menuName = menuName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getVegetable() {
        return vegetable;
    }

    public void setVegetable(String vegetable) {
        this.vegetable = vegetable;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTempOrderNo() {
        return tempOrderNo;
    }
}
