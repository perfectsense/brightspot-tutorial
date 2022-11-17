package com.brightspot.tutorial;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.psddev.graphql.GraphQLApiAccessOption;
import com.psddev.graphql.GraphQLApiAccessOptionImplicit;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.graphql.GraphQLCorsConfiguration;

@DisplayName("Images GraphQL")
public class ImageGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        List <ContentDeliveryEntryPointField> endpointFields = new ArrayList<ContentDeliveryEntryPointField>();
        endpointFields.add(new ContentDeliveryEntryPointField(ImageGraphQLEndpointViewModel.class, "Images", null));
        endpointFields.add(new ContentDeliveryEntryPointField(ImageViewModel.class, "Image", null));
        return endpointFields;
    }

    @Override
    public Set<String> getPaths() {
            Set<String> endpointPaths = new HashSet<String>();
            endpointPaths.add("/graphql/delivery/images");
            return endpointPaths;
    };

    @Override
    public GraphQLApiAccessOption getApiAccessOption() {
        return new GraphQLApiAccessOptionImplicit();
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);
        corsConfiguration.addAllowedOrigin("localhost");
    }
}
