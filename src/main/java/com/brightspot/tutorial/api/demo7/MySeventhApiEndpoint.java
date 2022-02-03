package com.brightspot.tutorial.api.demo7;

import java.util.Collections;
import java.util.Set;

import com.psddev.cms.api.ServletApiEndpoint;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Singleton;

@DisplayName("API Example 7")
public class MySeventhApiEndpoint extends ServletApiEndpoint<MySeventhApiServlet> implements Singleton {

    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public Set<String> getPaths() {
        return Collections.singleton("/api-7");
    }
}
