package com.brightspot.tutorial.graphql.demo7;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.brightspot.tutorial.Image;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cma.ContentManagementApiEndpoint;
import com.psddev.graphql.cma.ContentManagementEntryPointField;

/**
 * CMA - File Upload
 */
@DisplayName("GraphQL Demo 07")
public class MySeventhGraphQLEndpoint extends ContentManagementApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-7";
    }

    @Override
    public List<ContentManagementEntryPointField> getEntryFields() {
        return Stream.of(Image.class)
            .map(clazz -> new ContentManagementEntryPointField(clazz, true))
            .collect(Collectors.toList());
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);
        corsConfiguration.addAllowedOrigin("localhost");
    }
}
