package content.article;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;
import com.psddev.cms.db.ToolUi;
import content.article.Page;
import com.psddev.dari.util.StringUtils;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;

public class Article extends Content implements Page, Directory.Item {

    @Recordable.Required
    private String headline;

    @ToolUi.RichText
    private String body;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    @Override
    public String createPermalink(Site site) {
        return StringUtils.toNormalized(getHeadline());
    }

}