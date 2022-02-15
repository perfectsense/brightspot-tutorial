package com.brightspot.tutorial.graphql.demo15;

import java.util.List;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

/**
 * rCDA - Query Fields + Inverse Indexes
 */
@ToolUi.Hidden
@Deprecated
@DisplayName("GraphQL Demo 15")
public class MyFifteenthGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-15";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return null;
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);
        corsConfiguration.addAllowedOrigin("localhost");
    }
}
