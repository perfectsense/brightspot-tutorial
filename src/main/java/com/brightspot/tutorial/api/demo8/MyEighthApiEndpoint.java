package com.brightspot.tutorial.api.demo8;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.psddev.analytics.cms.api.ApiClientTracking;
import com.psddev.analytics.cms.api.ApiEndpointTracking;
import com.psddev.cms.api.ApiClient;
import com.psddev.cms.api.ApiEndpoint;
import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.JsonApiEndpoint;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;

@DisplayName("API Demo 08")
public class MyEighthApiEndpoint extends JsonApiEndpoint implements Singleton {

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-8");
    }

    @Override
    protected Object serveJson(ApiRequest request) {

        ApiClient client = request.resolveClient();
        ApiEndpoint endpoint = request.getEndpoint();

        long endpointRequestCount = endpoint.as(ApiEndpointTracking.class).getRequestCount() + 1;
        String clientName = client.getName();
        long clientRequestCount = client.as(ApiClientTracking.class).getRequestCount() + 1;

        return ObjectUtils.build(new LinkedHashMap<>(), map -> {
            map.put("endpointRequests", endpointRequestCount);
            map.put("client", clientName);
            map.put("clientRequests", clientRequestCount);
        });
    }
}
