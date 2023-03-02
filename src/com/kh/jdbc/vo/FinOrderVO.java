package com.kh.jdbc.vo;
import java.sql.Date;

    public class FinOrderVO {
        private Date date;
        private int orderNo;
        private int customNo;
        private String MenuName;
        private String size;
        private String bread;
        private String cheese;
        private String vegetable;
        private String sauce;
        private int howMany;
        private int total;

        public FinOrderVO(Date date, int orderNo, int customNo, String menuName, String size, String bread, String cheese, String vegetable, String sauce, int howMany/*, int total*/) {
            this.date = date;
            this.orderNo = orderNo;
            this.customNo = customNo;
            this.MenuName = menuName;
            this.size = size;
            this.bread = bread;
            this.cheese = cheese;
            this.vegetable = vegetable;
            this.sauce = sauce;
            this.howMany = howMany;
           // this.total = total;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public int getCustomNo() {
            return customNo;
        }

        public void setCustomNo(int customNo) {
            this.customNo = customNo;
        }

        public String getMenuName() {
            return MenuName;
        }

        public void setMenuName(String menuName) {
            MenuName = menuName;
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
    }

