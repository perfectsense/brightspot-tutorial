package brightspot.tutorial.page;

import com.psddev.cms.db.Seo;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.PageEntryView;
import com.psddev.handlebars.HandlebarsTemplate;

import brightspot.tutorial.article.Article;
import brightspot.tutorial.article.ArticleViewModel;

@ViewInterface
@HandlebarsTemplate("brightspot/tutorial/page/Page")
public class PageViewModel extends ViewModel<Article> implements PageEntryView {

    public String getTitle() {
        return model.as(Seo.ObjectModification.class).findTitle();
    }

    public ArticleViewModel getBody() {
        return createView(ArticleViewModel.class, model);
    }
}
