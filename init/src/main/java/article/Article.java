package article;

import com.psddev.cms.db.Content;

public class Article extends Content {
    private String headline;
    private String body;

    public String getHeadline() {
        return headline;
    }

    public String getBody() {
        return body;
    }
}
