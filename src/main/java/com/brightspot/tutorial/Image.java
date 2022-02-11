package com.brightspot.tutorial;

import java.util.Optional;

import com.brightspot.tutorial.graphql.demo13.rte.toolbar.SmallRichTextToolbar;
import com.brightspot.tutorial.graphql.demo13.tag.Taggable;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.ui.form.DynamicPlaceholderMethod;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.StorageItem;

@Recordable.PreviewField("file")
@Recordable.DisplayName("Image")
public class Image extends Content implements Taggable {

    @DynamicPlaceholderMethod("getTitlePlaceHolder")
    private String title;

    private StorageItem file;

    @ToolUi.RichText(toolbar = SmallRichTextToolbar.class)
    private String caption;

    private String credit;

    private String altText;

    @ToolUi.Hidden
    @Indexed
    public String getTitle() {
        if (title == null) {
            return getTitlePlaceHolder();
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StorageItem getFile() {
        return file;
    }

    public void setFile(StorageItem file) {
        this.file = file;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    @Override
    public String getLabel() {
        return getTitle();
    }

    private String getTitlePlaceHolder() {
        return Optional.of(getFile())
            .map(StorageItem::getMetadata)
            .map(m -> m.get("originalFilename"))
            .map(Object::toString)
            .orElse(null);
    }
}
