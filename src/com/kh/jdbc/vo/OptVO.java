package com.kh.jdbc.vo;

public class OptVO {
        private int orderNum;
        private String breadType;
        private String cheeseType;
        private String vegType;
        private String sauceType;

        public OptVO(int orderNum, String breadType, String cheeseType, String vegType, String sauceType) {
            this.orderNum = orderNum;
            this.breadType = breadType;
            this.cheeseType = cheeseType;
            this.vegType = vegType;
            this.sauceType = sauceType;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getBreadType() {
            return breadType;
        }

        public void setBreadType(String breadType) {
            this.breadType = breadType;
        }

        public String getCheeseType() {
            return cheeseType;
        }

        public void setCheeseType(String cheeseType) {
            this.cheeseType = cheeseType;
        }

        public String getVegType() {
            return vegType;
        }

        public void setVegType(String vegType) {
            this.vegType = vegType;
        }

        public String getSauceType() {
            return sauceType;
        }

        public void setSauceType(String sauceType) {
            this.sauceType = sauceType;
        }
    }
