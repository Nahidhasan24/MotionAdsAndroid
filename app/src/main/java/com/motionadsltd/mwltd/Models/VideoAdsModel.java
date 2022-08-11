package com.motionadsltd.mwltd.Models;

public class VideoAdsModel {
    private String ad1,ad2,ad3,ad4,ad5,uid,time,last;
    int use;

    public VideoAdsModel() {
    }

    public VideoAdsModel(String ad1, String ad2, String ad3, String ad4, String ad5, String uid, String time, String last, int use) {
        this.ad1 = ad1;
        this.ad2 = ad2;
        this.ad3 = ad3;
        this.ad4 = ad4;
        this.ad5 = ad5;
        this.uid = uid;
        this.time = time;
        this.last = last;
        this.use = use;
    }

    public String getAd1() {
        return ad1;
    }

    public void setAd1(String ad1) {
        this.ad1 = ad1;
    }

    public String getAd2() {
        return ad2;
    }

    public void setAd2(String ad2) {
        this.ad2 = ad2;
    }

    public String getAd3() {
        return ad3;
    }

    public void setAd3(String ad3) {
        this.ad3 = ad3;
    }

    public String getAd4() {
        return ad4;
    }

    public void setAd4(String ad4) {
        this.ad4 = ad4;
    }

    public String getAd5() {
        return ad5;
    }

    public void setAd5(String ad5) {
        this.ad5 = ad5;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}
