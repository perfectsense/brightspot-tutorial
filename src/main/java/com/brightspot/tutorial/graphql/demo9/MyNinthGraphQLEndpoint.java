package com.brightspot.tutorial.graphql.demo9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Full Endpoint Customization
 */
@DisplayName("GraphQL Demo 09")
public class MyNinthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-9";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Arrays.asList(
            new ContentDeliveryEntryPointField(
                Demo9SearchResultViewModel.class,
                "demoNineSearch",
                null),
            new ContentDeliveryEntryPointField(
                Demo9ViewModel.class));
    }

    @Override
    public List<ContentDeliveryEntryPointField> getMutationEntryFields() {
        return Collections.singletonList(new ContentDeliveryEntryPointField(
            Demo9UpdateViewModel.class,
            "demoNineSave",
            null));
    }
}
