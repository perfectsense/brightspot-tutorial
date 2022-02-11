package com.brightspot.tutorial.graphql.demo11;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryApiThemeable;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Images
 *
 * @see com.brightspot.tutorial.graphql.demo16.MySixteenthGraphQLEndpoint
 */
@Deprecated
@DisplayName("GraphQL Demo 11")
public class MyEleventhGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton,
    ContentDeliveryApiThemeable {

    @Override
    protected String getPathSuffix() {
        return "/demo-11";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo11ViewModel.class
            )
        );
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration graphQLCorsConfiguration) {
        super.updateCorsConfiguration(graphQLCorsConfiguration);
        graphQLCorsConfiguration.addAllowedOrigin("localhost");
    }
}
