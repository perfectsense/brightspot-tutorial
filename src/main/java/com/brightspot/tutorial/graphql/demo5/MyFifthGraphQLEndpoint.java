package com.brightspot.tutorial.graphql.demo5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.brightspot.tutorial.graphql.demo4.Demo4ViewModel;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * Schema Versioning (CDA)
 */
@DisplayName("GraphQL Demo 05")
public class MyFifthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-5";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Stream.of(Demo4ViewModel.class)
            .map(ContentDeliveryEntryPointField::new)
            .collect(Collectors.toList());
    }
}
