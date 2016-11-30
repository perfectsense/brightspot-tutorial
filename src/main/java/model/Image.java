package model;

import com.psddev.cms.db.Content;
import com.psddev.dari.util.StorageItem;

public class Image extends Content {

    private String title;
    private StorageItem file;

    public String getTitle() {
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
}
