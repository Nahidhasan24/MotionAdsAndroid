package com.motionadsltd.mwltd.Models;

public class TeamsModel {
    private String refercode,uid,team;

    public TeamsModel() {
    }

    public TeamsModel(String refercode, String uid, String team) {
        this.refercode = refercode;
        this.uid = uid;
        this.team = team;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
