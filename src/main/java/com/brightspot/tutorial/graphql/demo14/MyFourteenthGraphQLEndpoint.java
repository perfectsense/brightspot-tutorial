package com.brightspot.tutorial.graphql.demo14;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.CustomGraphQLCorsConfiguration;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * CDA - Segmentation
 */
@DisplayName("GraphQL Demo 14")
public class MyFourteenthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Recordable.DisplayName("CORS Configuration")
    private CustomGraphQLCorsConfiguration corsConfiguration;

    public CustomGraphQLCorsConfiguration getCorsConfiguration() {
        return corsConfiguration;
    }

    public void setCorsConfiguration(CustomGraphQLCorsConfiguration corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    @Override
    protected String getPathSuffix() {
        return "/demo-14";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo14ViewModel.class
            )
        );
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration graphQLCorsConfiguration) {
        super.updateCorsConfiguration(graphQLCorsConfiguration);

        graphQLCorsConfiguration.addAllowedOrigin("localhost");

        Optional.ofNullable(corsConfiguration)
            .map(CustomGraphQLCorsConfiguration::getAllowedOrigins)
            .ifPresent(dw -> dw.forEach(graphQLCorsConfiguration::addAllowedOrigin));

        Optional.ofNullable(corsConfiguration)
            .map(CustomGraphQLCorsConfiguration::getAllowedHeaders)
            .ifPresent(ah -> ah.forEach(graphQLCorsConfiguration::addAllowedHeader));
    }
}
