package com.brightspot.tutorial.api.demo1;

import java.util.Collections;
import java.util.Set;

import com.psddev.cms.api.ApiEndpoint;
import com.psddev.cms.api.ApiRequest;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.web.WebResponse;

/**
 * Hello API Framework
 */
@DisplayName("API Demo 01")
public class MyFirstApiEndpoint extends ApiEndpoint implements Singleton {

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-1");
    }

    @Override
    protected void serve(ApiRequest request, WebResponse response) {
        response.setHeader("Content-Type", "application/json");
        response.toBody().write("{\"status\":\"ok\"}");
    }
}
