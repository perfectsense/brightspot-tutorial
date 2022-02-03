package com.brightspot.tutorial.api.demo8;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.psddev.cms.api.ApiRequest;
import com.psddev.cms.api.ApiRequestException;
import com.psddev.cms.api.ApiRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingApiRequestListener implements ApiRequestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingApiRequestListener.class);

    @Override
    public void initialize(ApiRequest request) {
        request.getOriginal().as(LoggingApiRequest.class).setLoggingDisabled(
            !request.getOriginal().getParameter(boolean.class, "log"));
    }

    @Override
    public void beforeProcess(ApiRequest request) {
        logRequest(request, "1. beforeProcess");
    }

    @Override
    public void onSuccess(ApiRequest request) {
        logRequest(request, "2. onSuccess");
    }

    @Override
    public void onError(ApiRequestException error) {
        logRequest(error.getRequest(), "2. onError", error.getMessage());
    }

    @Override
    public void afterProcess(ApiRequest request) {
        logRequest(request, "3. afterProcess");
    }

    private void logRequest(ApiRequest request, String method, String... messages) {
        if (!request.getOriginal().as(LoggingApiRequest.class).isLoggingDisabled()) {
            String message = Arrays.stream(messages)
                .map(m -> ": " + m)
                .collect(Collectors.joining());

            LOGGER.info("{}: {}{}",
                method, request.getEndpoint().getLabel(), message);
        }
    }
}
