package brightspot.tutorial.page;

import styleguide.tutorial.page.PageView;
import styleguide.tutorial.page.PageViewBodyField;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Seo;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewModel;

public class PageViewModel extends ViewModel<Content> implements PageView, PageEntryView {

    @Override
    public CharSequence getTitle() {
        return model.as(Seo.ObjectModification.class).findTitle();
    }

    @Override
    public Iterable<? extends PageViewBodyField> getBody() {
        return createViews(PageViewBodyField.class, model);
    }
}
