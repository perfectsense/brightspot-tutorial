package com.company.team;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.dari.util.StringUtils;

public class Team extends Content implements
        Directory.Item {

    @Required
    @Indexed(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String createPermalink(Site site) {

        String normalizedName = StringUtils.toNormalized(getName());
        if (!StringUtils.isBlank(normalizedName)) {
            return "/teams/" + normalizedName;
        }

        return null;
    }
}
