package com.motionadsltd.mwltd.Models;

public class UserModels {
    private String name,mail,phone,uid,status,create,account,refercode,referby,login,team;
    private int coin,refercount;

    public UserModels() {
    }

    public UserModels(String name, String mail, String phone, String uid, String status, String create, String account, String refercode, String referby, String login, String team, int coin, int refercount) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.uid = uid;
        this.status = status;
        this.create = create;
        this.account = account;
        this.refercode = refercode;
        this.referby = referby;
        this.login = login;
        this.team = team;
        this.coin = coin;
        this.refercount = refercount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }

    public String getReferby() {
        return referby;
    }

    public void setReferby(String referby) {
        this.referby = referby;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getRefercount() {
        return refercount;
    }

    public void setRefercount(int refercount) {
        this.refercount = refercount;
    }
}
