package brightspot.tutorial.article;

import brightspot.tutorial.image.Image;
import com.psddev.styleguide.tutorial.article.ArticleView;

import com.psddev.cms.rte.RichTextViewBuilder;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.util.StorageItem;

public class ArticleViewModel extends ViewModel<Article> implements ArticleView {

    @Override
    public CharSequence getHeadline() {
        return model.getHeadline();
    }

    @Override
    public CharSequence getLeadImageUrl() {
        Image leadImage = model.getLeadImage();

        if (leadImage != null) {
            StorageItem file = leadImage.getFile();

            if (file != null) {
                return file.getPublicUrl();
            }
        }

        return null;
    }

    @Override
    public CharSequence getBody() {
        String body = model.getBody();

        if (body != null) {
            return RichTextViewBuilder.buildHtml(body, (rte) -> null);
        }

        return null;
    }
}
