package com.brightspot.tutorial.graphql.demo13.rte.heading;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

@Recordable.DisplayName("Heading")
@RichTextElement.Tag(value = HeadingRichTextElement.TAG_NAME,
    block = true,
    preview = false,
    position = -60,
    readOnly = false,
    root = true,
    keymaps = { "Ctrl-Shift-h" },
    tooltip = "Add Heading",
    menu = "Enhancements",
    initialBody = "Heading"
)
@ToolUi.IconName("text_fields")
public class HeadingRichTextElement extends RichTextElement {

    public static final String TAG_NAME = "bsp-h";

    private static final String STYLE_ATTRIBUTE = "data-style";
    private static final String TEXT_ATTRIBUTE = "data-text";
    private static final String LEVEL_ATTRIBUTE = "data-level";
    private static final String LEVEL_LABEL_ATTRIBUTE = "data-level-label";

    @ToolUi.Placeholder("Heading 1")
    @Values({"Heading 2", "Heading 3", "Heading 4", "Heading 5", "Heading 6"})
    private String style;

    @ToolUi.Hidden
    private String value;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLevel() {
        if (style == null) {
            return 1;
        } else if (style.length() > 8) {
            return ObjectUtils.to(Integer.class, style.substring(8));
        } else {
            return null;
        }
    }

    @Override
    public void fromAttributes(Map<String, String> map) {
        style = Optional.ofNullable(map)
            .map(m -> m.get(STYLE_ATTRIBUTE))
            .orElse(null);
    }

    @Override
    public Map<String, String> toAttributes() {
        Map<String, String> map = new LinkedHashMap<>();

        if (style != null) {
            map.put(STYLE_ATTRIBUTE, style);

            if (style.length() > 8) {
                map.put(LEVEL_ATTRIBUTE, style.substring(8));
            }

            if (style.length() > 8) {
                map.put(LEVEL_LABEL_ATTRIBUTE, "H" + style.substring(8));
            }
        } else {
            map.put(STYLE_ATTRIBUTE, "Heading 1");
            map.put(LEVEL_ATTRIBUTE, "1");
            map.put(LEVEL_LABEL_ATTRIBUTE, "H1");
        }

        return map.isEmpty() ? null : map;
    }

    @Override
    public void fromBody(String body) {
        if (body != null) {
            value = body;
            System.out.println("heading fromBody: " + value);
        }
    }

    @Override
    public String toBody() {
        System.out.println("heading toBody: " + value);
        return value;
    }

    @Override
    public String getLabel() {
        return getValue();
    }
}
