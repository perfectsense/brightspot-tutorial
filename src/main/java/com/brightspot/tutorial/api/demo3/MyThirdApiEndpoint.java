package com.brightspot.tutorial.api.demo3;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.psddev.cms.api.ApiClient;
import com.psddev.cms.api.ApiClientCredentials;
import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.JsonApiEndpoint;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;

@DisplayName("API Demo 3")
public class MyThirdApiEndpoint extends JsonApiEndpoint implements Singleton {

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-3");
    }

    @Override
    protected Object serveJson(ApiRequest request) {

        request.overrideClientWithCredentials(
            () -> new ApiClientCredentials(
                request.getOriginal().getHeader("X-Client-Id"),
                request.getOriginal().getHeader("X-Client-Secret")));

        ApiClient client = request.resolveClient();

        return ObjectUtils.build(new LinkedHashMap<>(),
            map -> map.put("client", client.getName()));
    }
}
