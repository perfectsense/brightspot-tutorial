package com.brightspot.tutorial;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;

public class Article extends Content {

    @Required
    private String Headline;

    private String Subheadline;

    @ToolUi.RichText(toolbar=CustomRichTextToolbar.class)
    private String body;
}
