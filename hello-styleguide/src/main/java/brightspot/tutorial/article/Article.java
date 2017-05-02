package brightspot.tutorial.article;

import brightspot.tutorial.image.Image;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.StringUtils;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;

public class Article extends Content implements
        Directory.Item {

    @Required
    private String headline;

    private Image leadImage;

    @ToolUi.RichText
    private String body;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Image getLeadImage() {
        return leadImage;
    }

    public void setLeadImage(Image leadImage) {
        this.leadImage = leadImage;
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
