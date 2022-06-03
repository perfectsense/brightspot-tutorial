package com.brightspot.tutorial.graphql.demo13.rte.link;

import java.util.Optional;

import com.brightspot.tutorial.graphql.demo13.link.ExternalLink;
import com.brightspot.tutorial.graphql.demo13.link.InternalLink;
import com.brightspot.tutorial.graphql.demo13.link.Link;
import com.brightspot.tutorial.graphql.demo13.link.Target;
import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.db.Site;
import com.psddev.cms.page.CurrentSite;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class LinkRichTextElementViewModel extends ViewModel<LinkRichTextElement> implements RichTextElementView {

    @CurrentSite
    private Site site;

    public CharSequence getContentId() {
        Link link = model.getLink();
        return (link == null || link instanceof ExternalLink || ((InternalLink) link).getItem() == null)
            ? null
            : ((InternalLink) link).getItem().getState().getId().toString();
    }

    public CharSequence getUrl() {
        Link link = model.getLink();
        return link == null ? null : link.getLinkUrl(site);
    }

    public CharSequence getTarget() {
        return Optional.ofNullable(model.getLink())
            .map(Link::getTarget)
            .map(Target::getValue)
            .orElse(null);
    }
}
