package com.brightspot.tutorial.graphql.demo1;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;
import com.psddev.graphql.cda.rda.RecordableDeliveryEntryPointField;

@DisplayName("GraphQL Demo 01 (rCDA)")
public class MyFirstGraphQLRecordableDeliveryEndpoint
    extends ContentDeliveryApiEndpoint
    implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-1r";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(
            new RecordableDeliveryEntryPointField(
                Demo1.class
            )
        );
    }
}
