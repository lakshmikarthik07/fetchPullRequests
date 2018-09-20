package com.test.procore.fetchgithubdata.utils;

public class JsonPojoClass {

    private  String url;
    private  String id;
    private  String node_id;
    private  String html_utl;
    private  String diff_url;
    private  String state;
    private  String number;
    private  String title;

    public JsonPojoClass(String url, String id, String node_id, String html_utl, String diff_url, String state, String number, String title) {
        this.url = url;
        this.id = id;
        this.node_id = node_id;
        this.html_utl = html_utl;
        this.diff_url = diff_url;
        this.state = state;
        this.number = number;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getNode_id() {
        return node_id;
    }

    public String getHtml_utl() {
        return html_utl;
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
