package com.kh.jdbc.vo;

public class OrdercheckVO {
    private String mName;
    private String size;
    private String bread;
    private String cheese;
    private String vege;
    private String source;
    private int price;
    private int howMany;
    private int sPrice;


    public OrdercheckVO(String mName, String size, String bread, String cheese, String vege, String source,int price, int howMany, int sPrice) {
        this.mName = mName;
        this.size = size;
        this.bread = bread;
        this.cheese = cheese;
        this.vege = vege;
        this.price = price;
        this.source = source;
        this.howMany = howMany;
        this.sPrice = sPrice;
    }

    public int getsPrice() {
        return sPrice;
    }

    public void setsPrice(int sPrice) {
        this.sPrice = sPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
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

    public String getVege() {
        return vege;
    }

    public void setVege(String vege) {
        this.vege = vege;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }


}
