package com.brightspot.tutorial.api.demo2;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.JsonApiEndpoint;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;

@DisplayName("API Demo 2")
public class MySecondApiEndpoint extends JsonApiEndpoint implements Singleton {

    private String message;

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-2");
    }

    @Override
    protected Object serveJson(ApiRequest request) {
        return ObjectUtils.build(new LinkedHashMap<>(),
            map -> map.put("message", message));
    }
}
