package com.brightspot.tutorial.graphql.demo7;

import java.util.Optional;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.ui.form.DynamicPlaceholderMethod;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.StorageItem;

@Deprecated
@ToolUi.Hidden
@Recordable.PreviewField("file")
@Recordable.DisplayName("Demo 7 Image")
public class Demo7Image extends Content {

    @DynamicPlaceholderMethod("getTitlePlaceHolder")
    private String title;

    private StorageItem file;

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
