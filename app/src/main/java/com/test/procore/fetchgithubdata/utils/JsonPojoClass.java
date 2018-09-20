package com.test.procore.fetchgithubdata.utils;

public class JsonPojoClass {

    private  String id;
    private  String diff_url;
    private  String state;
    private  String number;
    private  String title;

    public JsonPojoClass(String id, String diff_url, String state, String number, String title) {
        this.id = id;
        this.diff_url = diff_url;
        this.state = state;
        this.number = number;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public String getState() {
        return state;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }
}
