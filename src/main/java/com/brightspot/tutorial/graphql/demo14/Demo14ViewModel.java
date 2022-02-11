package com.brightspot.tutorial.graphql.demo14;

import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class Demo14ViewModel extends ViewModel<Demo14> implements PageEntryView {

    public String getTitle() {
        return model.getTitle();
    }

    public String getDescription() {
        return model.getDescription();
    }
}
