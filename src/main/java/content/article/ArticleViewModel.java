package content.article;

import com.psddev.cms.view.ViewModel;
import styleguide.content.article.ArticleView;

public class ArticleViewModel extends ViewModel<Article> implements ArticleView {

    @Override
    public String getBody() {
        return model.getBody();
    }

    @Override
    public String getHeadline() {
        return model.getHeadline();
    }
}
