package com.brightspot.tutorial.api.demo7;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psddev.cms.api.ApiEndpoint;
import com.psddev.cms.api.ApiRequest;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.web.WebRequest;

public class MySeventhApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ApiEndpoint endpoint = WebRequest.getCurrent().as(ApiRequest.class).getEndpoint();
        MySeventhApiEndpoint mySeventhApi = (MySeventhApiEndpoint) endpoint;

        Map<String, Object> responseMap = new LinkedHashMap<>();

        responseMap.put("status", "ok");
        responseMap.put("result", ObjectUtils.build(new LinkedHashMap<>(), result -> {
            result.put("message", mySeventhApi.getMessage());
            result.put("servletPath", request.getServletPath());
            result.put("pathInfo", request.getPathInfo());
        }));

        response.setContentType("application/json");
        response.getWriter().write(ObjectUtils.toJson(responseMap, true));
    }
}
