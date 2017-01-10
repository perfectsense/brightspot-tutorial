package content.article;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.PageFilter;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewBinding;
import com.psddev.dari.db.Recordable;

@ViewBinding(value = ArticleViewModel.class, types = PageFilter.PAGE_VIEW_TYPE)
public class Article extends Content {

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
}
