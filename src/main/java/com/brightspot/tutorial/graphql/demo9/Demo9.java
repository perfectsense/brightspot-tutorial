package com.brightspot.tutorial.graphql.demo9;

import com.psddev.cms.db.Content;

public class Demo9 extends Content {

    @Required
    @Indexed(unique = true)
    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
