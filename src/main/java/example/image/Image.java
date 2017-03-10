package example.image;

import com.psddev.cms.db.Content;
import com.psddev.dari.util.StorageItem;
import example.module.ModuleItem;

public class Image extends Content implements ModuleItem {

    @Required
    private String altText;

    @Required
    private StorageItem file;

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public StorageItem getFile() {
        return file;
    }

    public void setFile(StorageItem file) {
        this.file = file;
    }
}
