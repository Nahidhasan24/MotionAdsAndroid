package com.motionadsltd.mwltd.Models;

public class WithdrawModle {
    private String name,uid,number,date,method,status;
    private int amount;

    public WithdrawModle() {
    }

    public WithdrawModle(String name, String uid, String number, String date, String method, String status, int amount) {
        this.name = name;
        this.uid = uid;
        this.number = number;
        this.date = date;
        this.method = method;
        this.status = status;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
