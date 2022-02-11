package com.brightspot.tutorial.graphql.demo13.code;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Interchangeable;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public abstract class CodeSnippetCode extends Content implements Interchangeable {

    public abstract String getCode();

    public abstract void setCode(String code);

    public abstract String getCodeType();

    @Override
    public boolean loadTo(Object target) {
        if (target instanceof CodeSnippetCode) {
            ((CodeSnippetCode) target).setCode(getCode());
            return true;
        }
        return false;
    }

    @Override
    public boolean loadFrom(Object source) {
        if (source instanceof CodeSnippetCode) {
            setCode(((CodeSnippetCode) source).getCode());
        }
        return false;
    }
}
