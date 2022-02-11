package com.brightspot.tutorial.graphql.demo5;

import com.brightspot.tutorial.graphql.demo2.MySecondGraphQLEndpoint;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class Demo5ViewModel extends ViewModel<MySecondGraphQLEndpoint> {

    public String getTitle() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    // TODO: Delete
    public String getShortDescription() {
        return null;
    }

    // TODO: Deprecate
    public Integer getDay() {
        return null;
    }

    // TODO: Change return type to String
    public Integer getMonth() {
        return null;
    }

    // TODO: Rename to getYearValue
    public Integer getYear() {
        return null;
    }

    // TODO: Add new methods (uncomment)
//    public Long getDate() {
//        return null;
//    }
//
//    public Demo2RelationViewModel getRelation() {
//        return null;
//    }
}
