package com.brightspot.tutorial.graphql.demo13;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.brightspot.tutorial.graphql.demo13.article.ArticleViewModel;
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
        return Stream.of(ArticleViewModel.class, Demo13ViewModel.class)
            .map(ContentDeliveryEntryPointField::new)
            .collect(Collectors.toList());
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration graphQLCorsConfiguration) {
        super.updateCorsConfiguration(graphQLCorsConfiguration);
        graphQLCorsConfiguration.addAllowedOrigin("localhost");
    }
}
