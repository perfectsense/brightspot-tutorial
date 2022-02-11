package com.brightspot.tutorial.graphql.demo13.link;

import com.psddev.cms.db.Site;
import com.psddev.dari.db.Recordable;

public interface Linkable extends Recordable {

    default String getLinkableUrl(Site site) {
        return DirectoryItemUtils.getCanonicalUrl(site, this);
    }

    String getLinkableText();
}
