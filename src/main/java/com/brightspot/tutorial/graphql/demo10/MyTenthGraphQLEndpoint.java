package com.brightspot.tutorial.graphql.demo10;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Input Types
 */
@DisplayName("GraphQL Demo 10")
public class MyTenthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-10";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo10ViewModel.class
            )
        );
    }
}
