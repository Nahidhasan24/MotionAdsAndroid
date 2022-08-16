package com.motionadsltd.mwltd.Models;

public class PlanModle {
    private int price,paiduser,freeuser;

    public PlanModle() {
    }

    public PlanModle(int price, int paiduser, int freeuser) {
        this.price = price;
        this.paiduser = paiduser;
        this.freeuser = freeuser;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPaiduser() {
        return paiduser;
    }

    public void setPaiduser(int paiduser) {
        this.paiduser = paiduser;
    }

    public int getFreeuser() {
        return freeuser;
    }

    public void setFreeuser(int freeuser) {
        this.freeuser = freeuser;
    }
}
