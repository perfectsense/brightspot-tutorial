package com.brightspot.tutorial.graphql.demo13.mark;

import com.psddev.cms.db.RichTextElement;
import com.psddev.dari.mark.Mark;

public class RichTextMark {

    private final Mark mark;
    private final RichTextElement richTextElement;

    RichTextMark(Mark mark, RichTextElement richTextElement) {
        this.mark = mark;
        this.richTextElement = richTextElement;
    }

    public Mark getMark() {
        return mark;
    }

    public RichTextElement getRichTextElement() {
        return richTextElement;
    }
}
