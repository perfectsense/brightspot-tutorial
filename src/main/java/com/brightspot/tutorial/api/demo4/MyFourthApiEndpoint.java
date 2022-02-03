package com.brightspot.tutorial.api.demo4;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.psddev.cms.api.ApiClient;
import com.psddev.cms.api.ApiClientCredentials;
import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.ApiSiteProvider;
import com.psddev.cms.api.JsonApiEndpoint;
import com.psddev.cms.db.Site;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;

@DisplayName("API Demo 4")
public class MyFourthApiEndpoint extends JsonApiEndpoint implements Singleton {

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-4");
    }

    @Override
    protected Object serveJson(ApiRequest request) {

        request.overrideClientWithCredentials(
            () -> new ApiClientCredentials(
                request.getOriginal().getHeader("X-Client-Id"),
                request.getOriginal().getHeader("X-Client-Secret")));

        request.overrideSite(ApiSiteProvider.byUrl(request, () ->
            request.getOriginal().getHeader("X-Site-Url")));

        ApiClient client = request.resolveClient();
        Site site = request.resolveSite();

        // Site specific business logic here...

        return ObjectUtils.build(new LinkedHashMap<>(), map -> {
            map.put("client", client.getName());
            map.put("site", site != null ? site.getName() : null);
        });
    }
}
