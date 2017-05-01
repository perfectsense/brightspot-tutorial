package brightspot.tutorial.image;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.StorageItem;

@Recordable.PreviewField("file")
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
