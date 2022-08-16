package com.motionadsltd.mwltd.Models;

public class Appconfig {
    String addmoneynotice,addmoneynumber,notice,server;
    int minwithdraw,vcode;

    public Appconfig() {
    }

    public Appconfig(String addmoneynotice, String addmoneynumber, String notice, String server, int minwithdraw, int vcode) {
        this.addmoneynotice = addmoneynotice;
        this.addmoneynumber = addmoneynumber;
        this.notice = notice;
        this.server = server;
        this.minwithdraw = minwithdraw;
        this.vcode = vcode;
    }

    public String getAddmoneynotice() {
        return addmoneynotice;
    }

    public void setAddmoneynotice(String addmoneynotice) {
        this.addmoneynotice = addmoneynotice;
    }

    public String getAddmoneynumber() {
        return addmoneynumber;
    }

    public void setAddmoneynumber(String addmoneynumber) {
        this.addmoneynumber = addmoneynumber;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getMinwithdraw() {
        return minwithdraw;
    }

    public void setMinwithdraw(int minwithdraw) {
        this.minwithdraw = minwithdraw;
    }

    public int getVcode() {
        return vcode;
    }

    public void setVcode(int vcode) {
        this.vcode = vcode;
    }
}
