package com.brightspot.tutorial.graphql.demo13.rte.delimiter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.tool.ToolPageContext;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Delimiter")
@RichTextElement.Tag(value = DelimiterRichTextElement.TAG_NAME,
    block = true,
    preview = false,
    position = -60,
    readOnly = true,
    root = true,
    keymaps = { "Ctrl-Shift-d", "Cmd-Shift-d" },
    tooltip = "Add Delimiter",
    menu = "Enhancements"
)
@ToolUi.IconName("horizontal_rule")
public class DelimiterRichTextElement extends RichTextElement {

    public static final String TAG_NAME = "bsp-hr";

    private static final String STYLE_ATTRIBUTE = "data-style";

    @ToolUi.Placeholder("None")
    @Values({"Cloud", "Dashed Line", "Flaired Edges", "Glyph", "Gradient", "Inset", "Single Direction"})
    private String style;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public void fromAttributes(Map<String, String> map) {
        style = Optional.ofNullable(map)
            .map(m -> m.get(STYLE_ATTRIBUTE))
            .orElse(null);
    }

    @Override
    public Map<String, String> toAttributes() {
        if (style != null) {
            return ImmutableMap.of(STYLE_ATTRIBUTE, style);
        } else {
            return null;
        }
    }

    @Override
    public void fromBody(String body) {
    }

    @Override
    public String toBody() {
        return null;
    }

    @Override
    public void writePreviewHtml(ToolPageContext page) throws IOException {
        page.writeStart("div", "style", "text-align:center;font-size:20px;");
        page.writeHtml("***");
        page.writeEnd();
    }

    @Override
    public String getLabel() {
        return "Hello";
    }
}
