package article;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.StorageItem;

public class Article extends Content {

    @Required
    private String headline;

    @ToolUi.RichText
    private String body;

    private StorageItem image;

    public String getHeadline() {
        return headline;
    }

    public String getBody() {
        return body;
    }

    public StorageItem getImage() {
        return image;
    }
}
