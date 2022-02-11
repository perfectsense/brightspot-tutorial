package com.brightspot.tutorial.graphql.demo14;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.dari.util.Utils;
import org.apache.commons.lang3.StringUtils;

public class Demo14 extends Content implements Directory.Item {

    @Required
    @Indexed(unique = true)
    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String createPermalink(Site site) {
        String normalizedTitle = Utils.toNormalized(getTitle());
        if (!StringUtils.isBlank(normalizedTitle)) {
            return "/demo-14-" + normalizedTitle;
        }
        return null;
    }
}
