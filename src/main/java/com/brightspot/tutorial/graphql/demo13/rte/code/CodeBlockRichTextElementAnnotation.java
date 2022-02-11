package com.brightspot.tutorial.graphql.demo13.rte.code;

import java.util.Optional;

import com.brightspot.tutorial.graphql.demo13.rte.toolbar.SmallRichTextToolbar;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Recordable.DisplayName("Annotation")
@Recordable.Embedded
public class CodeBlockRichTextElementAnnotation extends Record {

    @ToolUi.ValueGeneratorClass(CodeBlockRichTextElementAnnotationLineValueGenerator.class)
    private String lineNumber;

    @ToolUi.Hidden
    private String codeSnippet;

    @ToolUi.RichText(toolbar = SmallRichTextToolbar.class)
    private String annotation;

    private transient CodeBlockRichTextElement parent;

    public Integer getLineNumber() {
        return ObjectUtils.to(Integer.class, lineNumber);
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber != null ? lineNumber.toString() : null;
    }

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public CodeBlockRichTextElement getParent() {
        return parent;
    }

    public void setParent(CodeBlockRichTextElement parent) {
        this.parent = parent;
    }

    public String getLabel() {
        if (getLineNumber() == null) {
            return null;
        }
        return "Line " + getLineNumber() + " - " + Optional.ofNullable(annotation)
            .map(Jsoup::parseBodyFragment)
            .map(Document::text)
            .orElse("");
    }
}
