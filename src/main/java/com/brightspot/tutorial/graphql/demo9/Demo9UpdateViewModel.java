package com.brightspot.tutorial.graphql.demo9;

import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.ApiRequestIllegalArgumentException;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUser;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.db.Query;
import com.psddev.dari.web.WebRequest;
import com.psddev.dari.web.annotation.WebParameter;
import org.apache.commons.lang3.StringUtils;

@ViewInterface("DemoNineUpdate")
public class Demo9UpdateViewModel extends ViewModel<MyNinthGraphQLEndpoint> {

    @WebParameter
    private String title;

    @WebParameter
    private boolean saveAsDraft;

    private Demo9 demo9;

    @Override
    protected void onCreate(ViewResponse response) {
        super.onCreate(response);

        if (StringUtils.isBlank(title)) {
            throw new ApiRequestIllegalArgumentException(
                "[title] is required!",
                WebRequest.getCurrent().as(ApiRequest.class));
        }

        demo9 = Query.from(Demo9.class)
            .where("title = ?", title)
            .findFirst()
            .orElseGet(Demo9::new);

        demo9.setTitle(title);
        demo9.as(Content.ObjectModification.class).setDraft(saveAsDraft);

        Content.Static.publish(demo9, null, Query.from(ToolUser.class).first());
    }

    public Demo9ViewModel getResult() {
        return createView(Demo9ViewModel.class, demo9);
    }
}
