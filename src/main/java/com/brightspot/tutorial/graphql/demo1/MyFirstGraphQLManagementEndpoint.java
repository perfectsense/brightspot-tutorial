package com.brightspot.tutorial.graphql.demo1;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cma.ContentManagementApiEndpoint;
import com.psddev.graphql.cma.ContentManagementEntryPointField;

@DisplayName("GraphQL Demo 01 (CMA)")
public class MyFirstGraphQLManagementEndpoint
    extends ContentManagementApiEndpoint
    implements Singleton {

    @Override
    protected String getPathSuffix() {
        return "/demo-1";
    }

    @Override
    public List<ContentManagementEntryPointField> getEntryFields() {
        return Collections.singletonList(
            new ContentManagementEntryPointField(
                Demo1.class, true
            )
        );
    }
}
