package article;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.StorageItem;
import com.psddev.dari.util.StringUtils;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import author.Author;

public class Article extends Content  implements Directory.Item  {

    @Required
    private String headline;

    @ToolUi.RichText
    private String body;
    private Author author;
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

    @Override
    public String createPermalink(Site site) {
        return StringUtils.toNormalized(getHeadline());
    }

}
