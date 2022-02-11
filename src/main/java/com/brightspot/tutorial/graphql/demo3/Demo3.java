package com.brightspot.tutorial.graphql.demo3;

import com.psddev.cms.db.Content;

public class Demo3 extends Content {

    private String message;

    private String byline;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }
}
