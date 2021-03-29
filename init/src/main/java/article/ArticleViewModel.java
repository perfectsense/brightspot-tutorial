package article;

import com.psddev.cms.rte.RichTextViewBuilder;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.util.StorageItem;
import com.psddev.styleguide.article.ArticleView;

public class ArticleViewModel extends ViewModel<Article> implements PageEntryView, ArticleView {

    @Override
    public CharSequence getHeadline() {
        return model.getHeadline();
    }

    @Override
    public CharSequence getBody() {
        String body = model.getBody();
        return RichTextViewBuilder.buildHtml(model.getState().getDatabase(),body,(rte) ->null);
    }

    @Override
    public CharSequence getImageUrl() {
        // TODO: implement getPublicUrl();
        return "";

        // StorageItem image = model.getImage();
        // return image.getPublicUrl();
    }

}
