package com.company.rte;

import java.util.Arrays;
import java.util.List;

import com.psddev.cms.rte.RichTextToolbar;
import com.psddev.cms.rte.RichTextToolbarAction;
import com.psddev.cms.rte.RichTextToolbarItem;
import com.psddev.cms.rte.RichTextToolbarStyle;

public class BasicRichTextToolbar implements RichTextToolbar {

    @Override
    public List<RichTextToolbarItem> getItems() {
        return Arrays.asList(
                RichTextToolbarStyle.BOLD,
                RichTextToolbarStyle.ITALIC,
                RichTextToolbarStyle.UNDERLINE,
                RichTextToolbarStyle.STRIKETHROUGH,
                RichTextToolbarStyle.SUPERSCRIPT,
                RichTextToolbarStyle.SUBSCRIPT,
                RichTextToolbarStyle.LINK,
                RichTextToolbarAction.CLEAR
        );
    }
}
