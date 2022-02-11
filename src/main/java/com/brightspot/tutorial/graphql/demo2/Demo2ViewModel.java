package com.brightspot.tutorial.graphql.demo2;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.web.WebRequest;

@ViewInterface
public class Demo2ViewModel extends ViewModel<MySecondGraphQLEndpoint> {

    public String getTitle() {
        return WebRequest.getCurrent().getHeader("X-Foo");
    }

    public String getDescription() {
        return WebRequest.getCurrent().getHeader("X-Bar");
    }
}
