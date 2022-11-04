package com.brightspot.tutorial;
import java.util.UUID;

import com.psddev.cms.db.Content;
import com.psddev.dari.util.StorageItem;

public class Image extends Content {
    private String title;

    private StorageItem imageFile;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StorageItem getImageFile() {
        return imageFile;
    }

    public UUID getImageId() {
        return getId();
    }

    public void setImageFile(StorageItem imageFile) {
        this.imageFile = imageFile;
    }
}
