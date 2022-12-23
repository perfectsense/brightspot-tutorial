package com.brightspot.tutorial;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLApiAccessOption;
import com.psddev.graphql.GraphQLApiAccessOptionImplicit;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

@Recordable.DisplayName("GraphQL Schema Documentation")
public class GraphQLSchemaDocumentationEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    public Set<String> getPaths() {
        Set<String> paths = new HashSet<String>();
        paths.add("/graphql/delivery/graphql-schema-documentation");
        return paths;
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Stream.of(
            new ContentDeliveryEntryPointField(
                AllProfilesViewModel.class
            ), new ContentDeliveryEntryPointField(
                ProfileViewModel.class
            )
        ).collect(Collectors.toList());
    }

    public void updateCorsConfiguration() {
        GraphQLCorsConfiguration corsConfiguration = new GraphQLCorsConfiguration();
        corsConfiguration.addAllowedOrigin("localhost");
    }

    public GraphQLApiAccessOption getApiAccessOption() {
        return new GraphQLApiAccessOptionImplicit();
    }
}
