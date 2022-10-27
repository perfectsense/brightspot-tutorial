package com.brightspot.tutorial;

import java.util.Arrays;
import java.util.List;

import com.psddev.cms.rte.RichTextToolbar;
import com.psddev.cms.rte.RichTextToolbarAction;
import com.psddev.cms.rte.RichTextToolbarItem;
import com.psddev.cms.rte.RichTextToolbarSeparator;
import com.psddev.cms.rte.RichTextToolbarStyle;
import com.psddev.cms.rte.ExternalContentRichTextElement;

public class CustomRichTextToolbar extends ExternalContentRichTextElement implements RichTextToolbar {

    @Override
    public List<RichTextToolbarItem> getItems() {

        return Arrays.asList(
            RichTextToolbarStyle.BOLD,
            RichTextToolbarStyle.ITALIC,
            RichTextToolbarStyle.UNDERLINE,
            RichTextToolbarStyle.STRIKETHROUGH,
            RichTextToolbarStyle.SUPERSCRIPT,
            RichTextToolbarStyle.SUBSCRIPT,
            RichTextToolbarAction.CLEAR,

            RichTextToolbarSeparator.BLOCK,
            RichTextToolbarStyle.ALIGN_LEFT,
            RichTextToolbarStyle.ALIGN_CENTER,
            RichTextToolbarStyle.ALIGN_RIGHT,

            RichTextToolbarSeparator.BLOCK,
            RichTextToolbarStyle.UL,
            RichTextToolbarStyle.OL,
            RichTextToolbarAction.INDENT,
            RichTextToolbarAction.OUTDENT,

            RichTextToolbarSeparator.INLINE,
            RichTextToolbarStyle.LINK,
            RichTextToolbarStyle.HTML,
            RichTextToolbarItem.UPLOAD,
            RichTextToolbarItem.ELEMENTS,
            RichTextToolbarItem.CUSTOM,

            RichTextToolbarSeparator.BLOCK,
            RichTextToolbarAction.TABLE,

            RichTextToolbarSeparator.INLINE,
            RichTextToolbarAction.KEYBOARD,

            RichTextToolbarAction.FULLSCREEN
            );
    }
}
