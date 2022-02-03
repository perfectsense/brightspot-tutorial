package com.brightspot.tutorial.api.demo8;

import com.psddev.cms.api.ApiClientCredentials;
import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.ApiRequestListener;

public class ClientAuthApiRequestListener implements ApiRequestListener {

    @Override
    public void initialize(ApiRequest request) {
        request.provideClientWithCredentials(() -> new ApiClientCredentials(
            request.getOriginal().getParameter(String.class, "clientId"),
            request.getOriginal().getParameter(String.class, "clientSecret")));
    }
}
