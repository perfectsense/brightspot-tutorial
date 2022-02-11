package com.brightspot.tutorial.graphql.demo6;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cma.ContentManagementApiEndpoint;
import com.psddev.graphql.cma.ContentManagementEntryPointField;

/**
 * CMA - CRUD ops
 */
@DisplayName("GraphQL Demo 06")
public class MySixthGraphQLEndpoint extends ContentManagementApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-6";
    }

    @Override
    public List<ContentManagementEntryPointField> getEntryFields() {
        return Stream.of(Demo6.class)
            .map(clazz -> new ContentManagementEntryPointField(clazz, true))
            .collect(Collectors.toList());
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);
        corsConfiguration.addAllowedOrigin("localhost");
    }
}
