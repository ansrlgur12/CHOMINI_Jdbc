package com.kh.jdbc.vo;

public class MenuVO {
    private String menuName;
    private int price;

    public MenuVO(String menuName, int price) {

        this.menuName = menuName;
        this.price = price;
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
