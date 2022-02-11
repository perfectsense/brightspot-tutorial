package com.brightspot.tutorial.graphql.demo1;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryApiEndpointV1;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

@DisplayName("GraphQL Demo 01 (vCDA)")
public class MyFirstGraphQLViewModelDeliveryEndpoint
    extends ContentDeliveryApiEndpoint
    implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-1v";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo1ViewModel.class
            )
        );
    }
}
