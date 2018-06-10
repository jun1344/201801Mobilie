package com.example.junsic.receiptdiary;

/**
 * Created by junsic on 2018-05-27.
 */

public class Receipt {
    private String date;
    private String name;
    private String type;
    private String money;

    public Receipt(String date, String name, String type, String money) {
        this.date = date;
        this.name = name;
        this.type = type;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}

