package com.hanhaiwang.androidutils;

public class DataBean {

    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String url;

    public DataBean(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
