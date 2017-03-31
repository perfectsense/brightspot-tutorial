package content.article;

 import com.psddev.cms.db.Content;
 import com.psddev.cms.view.ViewModel;
 import com.psddev.cms.view.PageEntryView;
 import styleguide.content.article.PageFooterView;
 import styleguide.content.article.PageHeaderView;
 import styleguide.content.article.PageView;
 import styleguide.content.article.PageViewFooterField;
 import styleguide.content.article.PageViewHeaderField;
 import styleguide.content.article.PageViewMainField;
 import java.util.Collections;

 public class PageViewModel extends ViewModel<Page> implements PageView, PageEntryView {

     @Override
     public String getTitle() {
         return "Brightspot Extraterrestrial";
     }

     @Override
     public Iterable<? extends PageViewHeaderField> getHeader() {
         return Collections.singletonList(new PageHeaderView.Builder().build());
     }

     @Override
     public Iterable<? extends PageViewMainField> getMain() {
         return Collections.singletonList(createView(PageViewMainField.class, model));
     }

     @Override
     public Iterable<? extends PageViewFooterField> getFooter() {
         return Collections.singletonList(new PageFooterView.Builder().build());
     }

 }