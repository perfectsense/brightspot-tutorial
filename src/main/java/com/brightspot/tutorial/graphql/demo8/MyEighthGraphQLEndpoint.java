package com.brightspot.tutorial.graphql.demo8;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.cms.db.ToolUser;
import com.psddev.dari.db.ObjectField;
import com.psddev.dari.db.ObjectType;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.graphql.GraphQLCorsConfiguration;
import com.psddev.graphql.cma.ContentManagementApiEndpoint;
import com.psddev.graphql.cma.ContentManagementEntryPointField;

/**
 * CMA - Field Type Filter
 */
@DisplayName("GraphQL Demo 08")
public class MyEighthGraphQLEndpoint extends ContentManagementApiEndpoint implements Singleton {

    private boolean excludeGlobals;

    private Set<String> includedReferenceFields;

    @Override
    protected String getPathSuffix() {
        return "/demo-8";
    }

    @Override
    public List<ContentManagementEntryPointField> getEntryFields() {
        return Stream.of(Demo8.class)
            .map(clazz -> ObjectUtils.build(new ContentManagementEntryPointField(clazz, false), entryField -> {
                entryField.setReferenceTypeFilter(referenceTypeFilter());
                entryField.setReferenceFieldFilter(referenceFieldFilter());
            }))
            .collect(Collectors.toList());
    }

    @Override
    protected void updateCorsConfiguration(GraphQLCorsConfiguration corsConfiguration) {
        super.updateCorsConfiguration(corsConfiguration);
        corsConfiguration.addAllowedOrigin("localhost");
    }

    @Override
    public boolean excludeGlobalModifications() {
        return excludeGlobals;
    }

    protected Predicate<ObjectField> referenceFieldFilter() {
        return field -> includedReferenceFields != null
            && includedReferenceFields.contains(field.getInternalName());
    }

    protected Predicate<ObjectType> referenceTypeFilter() {
        return type -> false;
    }
}
