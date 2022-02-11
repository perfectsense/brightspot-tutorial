package com.brightspot.tutorial.graphql.demo13.rte.rawhtml;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.tool.ToolPageContext;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

@Recordable.DisplayName("Raw HTML")
@RichTextElement.Tag(value = RawHtmlRichTextElement.TAG_NAME,
    block = true,
    initialBody = "",
    preview = true,
    position = -60,
    readOnly = true,
    root = true,
    keymaps = { "Ctrl-Shift-h", "Cmd-Shift-h" },
    tooltip = "Add Raw HTML",
    menu = "Enhancements"
)
@ToolUi.IconName("code")
public class RawHtmlRichTextElement extends RichTextElement {

    public static final String TAG_NAME = "bsp-raw-html";

    private static final String STATE_ATTRIBUTE = "data-state";

    @Required
    private String internalName;

    // Using HTML highlight mode; a css/javascript mode "text/htmlembedded" also exists
    @ToolUi.CodeType("text/html")
    private String rawHtml;

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    @Override
    public void fromAttributes(Map<String, String> attributes) {
        if (attributes != null) {
            String stateString = attributes.get(STATE_ATTRIBUTE);
            if (stateString != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> simpleValues = (Map<String, Object>) ObjectUtils.fromJson(stateString);
                getState().setValues(simpleValues);
            }
        }
    }

    @Override
    public Map<String, String> toAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put(STATE_ATTRIBUTE, ObjectUtils.toJson(getState().getSimpleValues()));
        return attributes;
    }

    @Override
    public String toBody() {
        return internalName;
    }

    @Override
    public void fromBody(String body) {
        // do nothing
    }

    @Override
    public void writePreviewHtml(ToolPageContext page) throws IOException {
        super.writePreviewHtml(page);
    }
}
