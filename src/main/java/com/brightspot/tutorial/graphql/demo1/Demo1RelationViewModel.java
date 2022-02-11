package com.brightspot.tutorial.graphql.demo1;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class Demo1RelationViewModel extends ViewModel<Demo1Relation> {

    public String getName() {
        return model.getName();
    }
}
