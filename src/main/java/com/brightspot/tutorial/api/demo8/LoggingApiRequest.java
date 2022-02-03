package com.brightspot.tutorial.api.demo8;

import com.psddev.dari.web.WebRequestExtension;

public class LoggingApiRequest extends WebRequestExtension {

    private boolean loggingDisabled;

    public boolean isLoggingDisabled() {
        return loggingDisabled;
    }

    public void setLoggingDisabled(boolean loggingDisabled) {
        this.loggingDisabled = loggingDisabled;
    }
}
