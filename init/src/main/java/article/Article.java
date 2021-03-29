package article;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;

public class Article extends Content {

    @Required
    private String headline;

    @ToolUi.RichText
    private String body;

    public String getHeadline() {
        return headline;
    }

    public String getBody() {
        return body;
    }
}
