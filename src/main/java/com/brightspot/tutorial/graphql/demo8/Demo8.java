package com.brightspot.tutorial.graphql.demo8;

import com.psddev.cms.db.Content;

public class Demo8 extends Content {

    private String title;

    private String description;

    @Embedded
    private Demo8Relation embeddedRelation;

    private Demo8Relation referenceRelation1;

    private Demo8Relation referenceRelation2;

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

    public Demo8Relation getEmbeddedRelation() {
        return embeddedRelation;
    }

    public void setEmbeddedRelation(Demo8Relation embeddedRelation) {
        this.embeddedRelation = embeddedRelation;
    }

    public Demo8Relation getReferenceRelation1() {
        return referenceRelation1;
    }

    public void setReferenceRelation1(Demo8Relation referenceRelation1) {
        this.referenceRelation1 = referenceRelation1;
    }

    public Demo8Relation getReferenceRelation2() {
        return referenceRelation2;
    }

    public void setReferenceRelation2(Demo8Relation referenceRelation2) {
        this.referenceRelation2 = referenceRelation2;
    }
}
