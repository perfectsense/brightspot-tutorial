package com.brightspot.tutorial.api.demo5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.psddev.cms.api.ApiClient;
import com.psddev.cms.api.ApiClientCredentials;
import com.psddev.cms.api.ApiClientPermissionsException;
import com.psddev.cms.api.ApiPermission;
import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.ApiRequestException;
import com.psddev.cms.api.JsonApiEndpoint;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;

@DisplayName("API Demo 05")
public class MyFifthApiEndpoint extends JsonApiEndpoint implements Singleton {

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-5");
    }

    @Override
    protected Object serveJson(ApiRequest request) {

        request.overrideClientWithCredentials(
            () -> new ApiClientCredentials(
                request.getOriginal().getHeader("X-Client-Id"),
                request.getOriginal().getHeader("X-Client-Secret")));

        IpAddressApiRequest ipRequest = request.getOriginal().as(IpAddressApiRequest.class);

        Optional.ofNullable(request.getOriginal().getHeader("X-ProxyUser-Ip"))
            .ifPresent(ipRequest::setIpAddress);

        ApiClient client = request.resolveClient();
        String ip = ipRequest.getIpAddress();

        return ObjectUtils.build(new LinkedHashMap<>(), map -> {
            map.put("client", client.getName());
            map.put("ip", ip);
        });
    }

    @Override
    protected void serveJsonError(ApiRequestException e) {
        if (e instanceof ApiClientPermissionsException) {
            e.setErrorStatusCode(403);
            e.setErrorStatus("Forbidden");
            e.setErrorResult(ObjectUtils.build(new LinkedHashMap<>(), result -> {

                List<Object> errors = new ArrayList<>();

                for (ApiPermission permission : ((ApiClientPermissionsException) e).getPermissions()) {

                    errors.add(ObjectUtils.build(new LinkedHashMap<>(), error -> {
                        error.put("type", permission.getState().getType().getLabel());
                        error.put("label", permission.getLabel());

                        if (permission instanceof IpAddressApiPermission) {
                            error.put("expected", ((IpAddressApiPermission) permission).getIpAddresses());
                            error.put("actual", e.getRequest().getOriginal().as(IpAddressApiRequest.class).getIpAddress());
                        }
                    }));
                }

                result.put("failedPermissions", errors);
            }));
        }
    }
}
