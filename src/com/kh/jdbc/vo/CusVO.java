package com.kh.jdbc.vo;

public class CusVO {
    private int cusNo;
    private String cusName;
    private String phone;

    public CusVO(int cusNo, String cusName, String phone) {
        this.cusNo = cusNo;
        this.cusName = cusName;
        this.phone = phone;
    }

    public int getCusNo() {
        return cusNo;
    }

    public void setCusNo(int cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
