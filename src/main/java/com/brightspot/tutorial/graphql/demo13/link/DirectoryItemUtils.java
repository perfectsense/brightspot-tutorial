package com.brightspot.tutorial.graphql.demo13.link;

import java.util.Objects;

import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.cms.tool.CmsTool;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class DirectoryItemUtils {

    /**
     * Look for url in the following order:
     * <ol>
     * <li>look for a site permalink on the recordable from the requested Site</li>
     * <li>look for a site permalink on the recordable from its owner Site</li>
     * <li>look for the first site permalink on the recordable from any of its accessible Sites</li>
     * <li>look for a global permalink on the recordable</li>
     * </ol>
     */
    public static String getCanonicalUrl(Site site, Recordable recordable) {

        String url;

        if (recordable == null || recordable.getState() == null) {
            return null;
        }

        // #1
        url = recordable.getState().as(Directory.ObjectModification.class).getSitePermalink(site);
        if (!StringUtils.isBlank(url)) {
            if (site == null) {
                CmsTool cmsSettings = Singleton.getInstance(CmsTool.class);
                String siteUrl = StringUtils.removeEnd(cmsSettings.getDefaultSiteUrl(), "/");
                url = (siteUrl != null ? siteUrl : "") + url;
            }
        }

        // #2
        if (StringUtils.isBlank(url) && !ObjectUtils.equals(
            site,
            recordable.as(Site.ObjectModification.class).getOwner())) {
            Site ownerSite = recordable.getState().as(Site.ObjectModification.class).getOwner();
            if (ownerSite != null) {
                url = recordable.getState().as(Directory.ObjectModification.class).getSitePermalink(ownerSite);
            }
        }

        // #3
        if (StringUtils.isBlank(url)) {
            url = recordable.getState().as(Site.ObjectModification.class).getConsumers().stream()
                .filter(Objects::nonNull)
                .map(consumer -> recordable.getState()
                    .as(Directory.ObjectModification.class)
                    .getSitePermalink(consumer))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        }

        // #4
        if (StringUtils.isBlank(url) && site != null) {
            String siteUrl = StringUtils.removeEnd(site.getPrimaryUrl(), "/");
            String permalink = recordable.getState().as(Directory.ObjectModification.class).getSitePermalink(null);
            if (permalink == null || siteUrl == null) {
                url = null;
            } else {
                url = siteUrl + permalink;
            }
        }
        return url;
    }

    public static String getRelativeCanonicalUrl(Site site, Recordable recordable) {

        String url;

        if (recordable == null || recordable.getState() == null) {
            return null;
        }

        // #1
        url = recordable.getState().as(Directory.ObjectModification.class).getSitePermalinkPath(site);

        // #2
        if (StringUtils.isBlank(url) && !ObjectUtils.equals(
            site,
            recordable.as(Site.ObjectModification.class).getOwner())) {
            Site ownerSite = recordable.getState().as(Site.ObjectModification.class).getOwner();
            if (ownerSite != null) {
                url = recordable.getState().as(Directory.ObjectModification.class).getSitePermalink(ownerSite);
            }
        }

        // #3
        if (StringUtils.isBlank(url)) {
            url = recordable.getState().as(Site.ObjectModification.class).getConsumers().stream()
                .filter(Objects::nonNull)
                .map(consumer -> recordable.getState()
                    .as(Directory.ObjectModification.class)
                    .getSitePermalink(consumer))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        }

        // #4
        if (StringUtils.isBlank(url) && site != null) {
            url = recordable.getState().as(Directory.ObjectModification.class).getSitePermalink(null);
        }

        return url;
    }
}
