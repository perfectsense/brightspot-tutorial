package com.brightspot.tutorial.graphql.demo13;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Rich Text
 */
@DisplayName("GraphQL Demo 13")
public class MyThirteenthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-13";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo13ViewModel.class
            )
        );
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration graphQLCorsConfiguration) {
        super.updateCorsConfiguration(graphQLCorsConfiguration);
        graphQLCorsConfiguration.addAllowedOrigin("localhost");
    }
}
