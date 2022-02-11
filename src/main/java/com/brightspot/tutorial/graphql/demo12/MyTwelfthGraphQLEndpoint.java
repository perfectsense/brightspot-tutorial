package com.brightspot.tutorial.graphql.demo12;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryApiThemeable;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Theme Fields
 */
@DisplayName("GraphQL Demo 12")
public class MyTwelfthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton,
    ContentDeliveryApiThemeable {

    @Override
    protected String getPathSuffix() {
        return "/demo-12";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo12ViewModel.class
            )
        );
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration graphQLCorsConfiguration) {
        super.updateCorsConfiguration(graphQLCorsConfiguration);
        graphQLCorsConfiguration.addAllowedOrigin("localhost");
    }
}
