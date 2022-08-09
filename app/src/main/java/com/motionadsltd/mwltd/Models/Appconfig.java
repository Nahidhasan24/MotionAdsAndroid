package com.motionadsltd.mwltd.Models;

public class Appconfig {
    String addmoneynotice,addmoneynumber,notice,server;

    public Appconfig() {
    }

    public Appconfig(String addmoneynotice, String addmoneynumber, String notice, String server) {
        this.addmoneynotice = addmoneynotice;
        this.addmoneynumber = addmoneynumber;
        this.notice = notice;
        this.server = server;
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
}
