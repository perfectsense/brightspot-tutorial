package com.brightspot.tutorial.graphql.demo13.code;

import com.psddev.cms.db.Content;

public class CodeSnippet extends Content {

    private String title;

    @Required
    private CodeSnippetCode code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CodeSnippetCode getCode() {
        if (code == null) {
            code = new CodeSnippetUnknown();
        }
        return code;
    }

    public void setCode(CodeSnippetCode code) {
        this.code = code;
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        if (code == null) {
            code = new CodeSnippetUnknown();
        }
    }
}
