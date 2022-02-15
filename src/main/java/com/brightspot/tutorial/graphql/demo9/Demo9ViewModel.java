package com.brightspot.tutorial.graphql.demo9;

import com.psddev.cms.db.Content;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewKey;
import com.psddev.cms.view.ViewModel;

@ViewInterface("DemoNine")
public class Demo9ViewModel extends ViewModel<Demo9> {

    @ViewKey("_id")
    public String getId() {
        return model.getId().toString();
    }

    @ViewKey("_Title_")
    public String getTitle() {
        return model.getTitle();
    }

    @ViewKey("THE_DESCRIPTION")
    public String getDescription() {
        return model.getDescription();
    }

    @ViewKey("isThisADraft")
    public boolean getIsDraft() {
        return model.as(Content.ObjectModification.class).isDraft();
    }
}
