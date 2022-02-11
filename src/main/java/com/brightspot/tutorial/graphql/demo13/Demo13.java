package com.brightspot.tutorial.graphql.demo13;

import com.brightspot.tutorial.Image;
import com.brightspot.tutorial.graphql.demo13.author.Author;
import com.brightspot.tutorial.graphql.demo13.link.Linkable;
import com.brightspot.tutorial.graphql.demo13.rte.toolbar.LargeRichTextToolbar;
import com.brightspot.tutorial.graphql.demo13.tag.Taggable;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.Utils;
import org.apache.commons.lang3.StringUtils;

public class Demo13 extends Content implements Directory.Item, Linkable, Taggable {

    @Required
    private String headline;

    @Indexed
    private Author author;

    private Image leadImage;

    @ToolUi.RichText(inline = false, toolbar = LargeRichTextToolbar.class)
    private String body;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Image getLeadImage() {
        return leadImage;
    }

    public void setLeadImage(Image leadImage) {
        this.leadImage = leadImage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getLabel() {
        return getHeadline();
    }

    @Override
    public String getLinkableText() {
        return getHeadline();
    }

    @Override
    public String createPermalink(Site site) {
        String normalizedHeadline = Utils.toNormalized(getHeadline());

        if (!StringUtils.isBlank(normalizedHeadline)) {
            return "/demo13/" + normalizedHeadline;
        }

        return null;
    }
}
