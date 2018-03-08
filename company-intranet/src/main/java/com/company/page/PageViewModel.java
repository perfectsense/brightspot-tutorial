package com.company.page;

import com.psddev.styleguide.page.PageView;
import com.psddev.styleguide.page.PageViewBodyField;
import com.psddev.styleguide.page.PageViewNavigationField;

import com.psddev.cms.db.Content;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewModel;

public class PageViewModel extends ViewModel<Content> implements PageView, PageEntryView {

    @Override
    public CharSequence getTitle() {
        return "Company Intranet";
    }

    @Override
    public Iterable<? extends PageViewNavigationField> getNavigation() {
        return null;
    }

    @Override
    public Iterable<? extends PageViewBodyField> getBody() {
        return createViews(PageViewBodyField.class, model);
    }
}
