package com.brightspot.tutorial.graphql.demo13.code;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Markdown")
public class CodeSnippetMarkdown extends CodeSnippetCode {

    @ToolUi.CodeType("text/x-markdown")
    private String code;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCodeType() {
        return "Markdown";
    }
}
