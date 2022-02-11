package com.brightspot.tutorial.graphql.demo13.link;

import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("External")
public class ExternalLink extends Link {

    public ExternalLink() {
        super();
        this.setTarget(Target.NEW);
    }

    @Required
    @ToolUi.Note("Start all external links with http://")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getLinkUrl(Site site) {
        return getUrl();
    }
}
