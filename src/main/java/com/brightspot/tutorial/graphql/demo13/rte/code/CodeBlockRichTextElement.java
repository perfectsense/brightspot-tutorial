package com.brightspot.tutorial.graphql.demo13.rte.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.tool.ToolPageContext;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.State;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@Recordable.DisplayName("Code Block")
@RichTextElement.Tag(value = "bsp-code-block",
    block = true,
    readOnly = true,
    root = true,
    preview = true,
    tooltip = "Add Code Block",
    keymaps = { "Ctrl-Shift-b", "Cmd-Shift-b" },
    position = -60,
    menu = "Enhancements")
@ToolUi.IconName("settings_ethernet")
public class CodeBlockRichTextElement extends RichTextElement {

    private static final int PREVIEW_LINES_COUNT = 10;

    private String title;

    @ToolUi.CodeType("text/plain")
    private String code;

    @Embedded
    private List<CodeBlockRichTextElementAnnotation> annotations;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CodeBlockRichTextElementAnnotation> getAnnotations() {
        if (annotations == null) {
            annotations = new ArrayList<>();
        }
        return annotations;
    }

    public void setAnnotations(List<CodeBlockRichTextElementAnnotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public String getLabel() {
        return getTitle();
    }

    @Override
    public void fromAttributes(Map<String, String> map) {
        if (map != null) {
            this.title = map.get("data-title");
            this.code = map.get("data-code");

            annotations = new ArrayList<>();

            String annotationsListJsonString = map.get("data-annotations");

            if (annotationsListJsonString != null) {
                Object annotationsListJsonList = ObjectUtils.fromJson(annotationsListJsonString);

                if (annotationsListJsonList instanceof Iterable) {
                    for (Object annotationJsonObject : (Iterable<?>) annotationsListJsonList) {

                        if (annotationJsonObject instanceof Map) {
                            CodeBlockRichTextElementAnnotation annotation = new CodeBlockRichTextElementAnnotation();
                            annotation.getState().setValues((Map<String, Object>) annotationJsonObject);
                            annotation.setParent(this);

                            annotations.add(annotation);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Map<String, String> toAttributes() {

        Map<String, String> attributes = new LinkedHashMap<>();

        if (code != null) {
            attributes.put("data-code", code);
        }

        if (title != null) {
            attributes.put("data-title", title);
        }

        if (annotations != null) {
            attributes.put("data-annotations", ObjectUtils.toJson(annotations.stream()
                .map(State::getInstance)
                .map(State::getSimpleValues)
                .collect(Collectors.toList())));

            annotations.forEach(annotation -> annotation.setParent(this));
        }

        return attributes;
    }

    @Override
    public void writePreviewHtml(ToolPageContext page) throws IOException {
        page.writeStart("pre");
        if (code != null) {
            int newLineAt = StringUtils.ordinalIndexOf(code, "\n", PREVIEW_LINES_COUNT);
            if (newLineAt >= 0) {
                page.writeHtml(code.substring(0, newLineAt));
                page.writeHtml("\n...");
                page.writeHtml("\n" + (StringUtils.countMatches(code, "\n") - PREVIEW_LINES_COUNT) + " more lines");
            } else {
                page.writeHtml(code);
            }
        }
        page.writeEnd();
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        if (annotations != null) {
            annotations.forEach(annotation -> annotation.setParent(this));
        }
    }
}
