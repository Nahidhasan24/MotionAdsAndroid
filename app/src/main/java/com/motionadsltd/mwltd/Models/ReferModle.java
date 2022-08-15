package com.motionadsltd.mwltd.Models;

public class ReferModle {
    private String code,uid;

    public ReferModle() {
    }

    public ReferModle(String code, String uid) {
        this.code = code;
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
