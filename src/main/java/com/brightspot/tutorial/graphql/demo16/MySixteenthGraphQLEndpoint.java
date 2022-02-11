package com.brightspot.tutorial.graphql.demo16;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryApiThemeable;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Images / Theme Fields / Preview
 */
@Recordable.DisplayName("GraphQL Demo 16")
public class MySixteenthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton,
    ContentDeliveryApiThemeable {

    @Override
    protected String getPathSuffix() {
        return "/demo-16";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo16ViewModel.class
            )
        );
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);
        corsConfiguration.addAllowedOrigin("localhost");
    }
}
