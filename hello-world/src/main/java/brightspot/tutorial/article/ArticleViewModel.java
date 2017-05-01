package brightspot.tutorial.article;

 import brightspot.tutorial.image.Image;

 import com.psddev.cms.rte.RichTextViewBuilder;
 import com.psddev.cms.view.ViewInterface;
 import com.psddev.cms.view.ViewModel;
 import com.psddev.dari.util.StorageItem;
 import com.psddev.handlebars.HandlebarsTemplate;

@ViewInterface
@HandlebarsTemplate("brightspot/tutorial/article/Article")
public class ArticleViewModel extends ViewModel<Article> {

    public String getHeadline() {
        return model.getHeadline();
    }

    public String getLeadImageUrl() {
        Image leadImage = model.getLeadImage();
        if (leadImage != null) {
            StorageItem file = leadImage.getFile();
            if (file != null) {
                return file.getPublicUrl();
            }
        }
        return null;
    }

    public CharSequence getBody() {
        String body = model.getBody();
        if (body != null) {
            return RichTextViewBuilder.buildHtml(body, (rte) -> null);
        }
        return null;
    }
}
