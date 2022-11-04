package com.brightspot.tutorial;

import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;

import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOption;
import com.psddev.graphql.cda.ContentDeliveryApiAccessOptionImplicit;

@DisplayName("Images GraphQL")
public class ImageGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        List <ContentDeliveryEntryPointField> endpointFields = new ArrayList<ContentDeliveryEntryPointField>();
        endpointFields.add(new ContentDeliveryEntryPointField(ImageGraphQLEndpointViewModel.class, "Images", null));
        endpointFields.add(new ContentDeliveryEntryPointField(ImageViewModel.class, "Image", null));
        return endpointFields;
        // return  (new ContentDeliveryEntryPointField(
        //     ImageGraphQLEndpointViewModel.class,
        //     "Images",
        //     null));
    }

    @Override
    protected String getPathSuffix() {
        return "/images";
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
