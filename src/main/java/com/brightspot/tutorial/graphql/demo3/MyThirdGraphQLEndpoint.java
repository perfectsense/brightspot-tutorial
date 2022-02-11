package com.brightspot.tutorial.graphql.demo3;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOption;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOptionImplicit;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;
import com.psddev.graphql.pqp.CustomAutomaticPersistedQueryProtocol;
import com.psddev.graphql.pqp.PersistedQueryProtocol;

/**
 * Persisted Query Protocol - Automatic vs. Static (CDA)
 */
@DisplayName("GraphQL Demo 03")
public class MyThirdGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Embedded
    private PersistedQueryProtocol persistedQueryProtocol = new CustomAutomaticPersistedQueryProtocol();

    @Override
    protected String getPathSuffix() {
        return "/demo-3";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo3ViewModel.class
            )
        );
    }

    @Override
    public PersistedQueryProtocol getPersistedQueryProtocol() {
        return persistedQueryProtocol;
    }

    public void setPersistedQueryProtocol(PersistedQueryProtocol persistedQueryProtocol) {
        this.persistedQueryProtocol = persistedQueryProtocol;
    }

    @Override
    public ContentDeliveryApiAccessOption getAccessOption() {
        return new ContentDeliveryApiAccessOptionImplicit();
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);

        corsConfiguration.addAllowedOrigin("localhost");
    }
}
