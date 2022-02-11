package com.brightspot.tutorial.graphql.demo13.rte.code;

import java.util.Map;

import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Inline Code")
@RichTextElement.Tag(value = "bsp-code-inline",
    tooltip = "Add Code Inline",
    keymaps = { "Ctrl-Shift-i", "Cmd-Shift-i" },
    position = -60,
    menu = "Enhancements")
@ToolUi.IconName("code")
public class CodeInlineRichTextElement extends RichTextElement {

    private String code;

    @Override
    public void fromAttributes(Map<String, String> map) {
    }

    @Override
    public Map<String, String> toAttributes() {
        return null;
    }

    @Override
    public void fromBody(String body) {
        code = body;
    }

    @Override
    public String toBody() {
        return code;
    }
}
