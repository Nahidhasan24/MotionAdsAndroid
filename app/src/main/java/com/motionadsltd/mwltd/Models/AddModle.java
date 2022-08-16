package com.motionadsltd.mwltd.Models;

public class AddModle {
    private String uid,tranid,time;
    private int amount;

    public AddModle(String uid, String tranid, String time, int amount) {
        this.uid = uid;
        this.tranid = tranid;
        this.time = time;
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
