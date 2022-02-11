package com.brightspot.tutorial.graphql.demo13.link;

import java.util.Optional;

import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Internal")
public class InternalLink extends Link {

    @Required
    @ToolUi.DisplayFirst
    @ToolUi.OnlyPathed
    @ToolUi.NestedRepeatable
    private Linkable item;

    public Linkable getItem() {
        return item;
    }

    public void setItem(Linkable item) {
        this.item = item;
    }

    @Override
    public String getLinkUrl(Site site) {
        return Optional.ofNullable(getItem())
            .map(i -> i.getLinkableUrl(site))
            .orElse(null);
    }

    @Override
    public String getLinkTextFallback() {
        Linkable item = getItem();
        return item != null ? item.getLinkableText() : super.getLinkTextFallback();
    }
}
