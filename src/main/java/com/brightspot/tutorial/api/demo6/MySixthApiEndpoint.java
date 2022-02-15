package com.brightspot.tutorial.api.demo6;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.JsonApiEndpoint;
import com.psddev.cms.db.Directory;
import com.psddev.dari.db.ObjectField;
import com.psddev.dari.db.ObjectIndex;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.StringException;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Multiple Endpoint Configurations
 */
@DisplayName("API Demo 06")
public class MySixthApiEndpoint extends JsonApiEndpoint {

    private static final String BASE_PATH = "/api-6";

    @Required
    private String name;

    @Required
    @Indexed(unique = true)
    @DisplayName("Path")
    private String pathSuffix;

    public String getName() {
        return name;
    }

    public String getPathSuffix() {
        return pathSuffix;
    }

    private String getFullPath() {
        String normalizedPathSuffix = getNormalizedPath(getPathSuffix());

        if (normalizedPathSuffix == null) {
            return null;
        }

        return StringUtils.removeEnd(BASE_PATH + normalizedPathSuffix, "/");
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        // Fixes the path suffix in case of invalid URL characters
        pathSuffix = getNormalizedPath(getPathSuffix());
    }

    @Override
    protected boolean onDuplicate(ObjectIndex index) {

        if (Directory.PATHS_FIELD.equals(index.getField())) {
            ObjectField pathSuffixField = getState().getField("pathSuffix");
            String message = "Please choose a different path, this one is already taken!";

            getState().addError(pathSuffixField, new StringException(message));
            return false;

        } else {
            return super.onDuplicate(index);
        }
    }

    @Override
    public Set<String> getPaths() {
        return Stream.of(getFullPath())
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    @Override
    public String getLabel() {
        return Stream.of(getState().getType().getDisplayName(), getName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" - "));
    }

    @Override
    protected Object serveJson(ApiRequest request) {
        return ObjectUtils.build(new LinkedHashMap<>(), map -> {
            map.put("api", getName());
            map.put("path", getPathSuffix());
        });
    }
}
