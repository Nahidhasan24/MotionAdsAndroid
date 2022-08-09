package com.motionadsltd.mwltd.Models;

public class SliderItem {
    private String url,click;

    public SliderItem() {
    }

    public SliderItem(String url, String click) {
        this.url = url;
        this.click = click;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }
}
