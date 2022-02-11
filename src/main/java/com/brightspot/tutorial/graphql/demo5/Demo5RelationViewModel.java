package com.brightspot.tutorial.graphql.demo5;

import com.brightspot.tutorial.graphql.demo2.MySecondGraphQLEndpoint;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class Demo5RelationViewModel extends ViewModel<MySecondGraphQLEndpoint> {

    public String getAlpha() {
        return null;
    }

    public Integer getBravo() {
        return null;
    }

    public boolean getCharlie() {
        return true;
    }
}
