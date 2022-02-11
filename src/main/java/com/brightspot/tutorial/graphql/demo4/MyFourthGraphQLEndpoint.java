package com.brightspot.tutorial.graphql.demo4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOption;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOptionImplicit;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * Schema Documentation (CDA)
 */
@DisplayName("GraphQL Demo 04")
public class MyFourthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-4";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Stream.of(Demo4ViewModel.class)
            .map(ContentDeliveryEntryPointField::new)
            .collect(Collectors.toList());
    }

    @Override
    public ContentDeliveryApiAccessOption getAccessOption() {
        return new ContentDeliveryApiAccessOptionImplicit();
    }
}
