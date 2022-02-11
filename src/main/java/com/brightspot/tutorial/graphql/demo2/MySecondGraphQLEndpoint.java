package com.brightspot.tutorial.graphql.demo2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.psddev.cms.ui.form.DynamicNoteMethod;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.html.Node;
import com.psddev.dari.web.WebRequest;
import com.psddev.graphql.CustomGraphQLCorsConfiguration;
import com.psddev.graphql.GraphQLApiRequest;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.IntrospectionQueryRule;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOption;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOptionExplicit;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOptionImplicit;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

import static com.psddev.dari.html.Nodes.*;

/**
 * API Keys / CORS Configuration / Allow Introspection Queries (CDA)
 */
@DisplayName("GraphQL Demo 02")
public class MySecondGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Embedded
    @Required
    @DisplayName("Access")
    @Types({ ContentDeliveryApiAccessOptionImplicit.class, ContentDeliveryApiAccessOptionExplicit.class })
    private ContentDeliveryApiAccessOption accessOption = new ContentDeliveryApiAccessOptionExplicit();

    @Recordable.DisplayName("CORS Configuration")
    private CustomGraphQLCorsConfiguration corsConfiguration;

    @DynamicNoteMethod("getAllowIntrospectionQueriesNote")
    private Boolean allowIntrospectionQueries;

    @Override
    public ContentDeliveryApiAccessOption getAccessOption() {
        if (accessOption == null) {
            accessOption = new ContentDeliveryApiAccessOptionExplicit();
        }
        return accessOption;
    }

    public void setAccessOption(ContentDeliveryApiAccessOption accessOption) {
        this.accessOption = accessOption;
    }

    public CustomGraphQLCorsConfiguration getCorsConfiguration() {
        return corsConfiguration;
    }

    public void setCorsConfiguration(CustomGraphQLCorsConfiguration corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    public boolean getAllowIntrospectionQueries() {
        return Boolean.TRUE.equals(allowIntrospectionQueries);
    }

    public void setAllowIntrospectionQueries(boolean allowIntrospectionQueries) {
        this.allowIntrospectionQueries = allowIntrospectionQueries ? Boolean.TRUE : null;
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        // Forces proper initialization of defaults since the fields are required.
        ContentDeliveryApiAccessOption ao = getAccessOption();

        if (ao instanceof ContentDeliveryApiAccessOptionImplicit) {
            ((ContentDeliveryApiAccessOptionImplicit) ao).setContentDeliveryApiEndpoint(this);
        }
    }

    @Override
    protected String getPathSuffix() {
        return "/demo-2";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new ContentDeliveryEntryPointField(
                Demo2ViewModel.class
            )
        );
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration graphQLCorsConfiguration) {
        super.updateCorsConfiguration(graphQLCorsConfiguration);

        Optional.ofNullable(corsConfiguration)
            .map(CustomGraphQLCorsConfiguration::getAllowedOrigins)
            .ifPresent(dw -> dw.forEach(graphQLCorsConfiguration::addAllowedOrigin));

        Optional.ofNullable(corsConfiguration)
            .map(CustomGraphQLCorsConfiguration::getAllowedHeaders)
            .ifPresent(ah -> ah.forEach(graphQLCorsConfiguration::addAllowedHeader));
    }

    @Override
    public IntrospectionQueryRule getIntrospectionQueryRule() {
        return () -> Boolean.TRUE.equals(allowIntrospectionQueries)
            || ((GraphQLApiRequest) WebRequest.getCurrent().as(GraphQLApiRequest.class)).isExplorerQuery();
    }

    @SuppressWarnings("unused")
    private Node getAllowIntrospectionQueriesNote() {
        return DIV
            .with("Allows introspection queries to be performed outside the GraphQL Explorer when in production mode.")
            .with(BR)
            .with("It is recommended to only toggle this in production if your API is meant for public consumption.");
    }
}
