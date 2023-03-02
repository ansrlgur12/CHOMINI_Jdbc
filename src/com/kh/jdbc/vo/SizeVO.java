package com.kh.jdbc.vo;

public class SizeVO {
    private String size;
    private int sPrice;

    public SizeVO() {
    }

    public SizeVO(String size, int sPrice) {
        this.size = size;
        this.sPrice = sPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getsPrice() {
        return sPrice;
    }

    public void setsPrice(int sPrice) {
        this.sPrice = sPrice;
    }
}
