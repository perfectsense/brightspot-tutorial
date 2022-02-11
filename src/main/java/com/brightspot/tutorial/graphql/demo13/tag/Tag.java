package com.brightspot.tutorial.graphql.demo13.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Taxon;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Query;

public class Tag extends Content implements Taxon {

    @Required
    @Indexed(unique = true)
    private String name;

    @Indexed
    private Tag parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    @Override
    public Collection<? extends Taxon> getChildren() {
        return Query.from(Tag.class)
            .where("parent = ?", this)
            .selectAll();
    }

    @Ignored(false)
    @ToolUi.Hidden
    public List<Tag> getAncestors() {
        List<Tag> ancestors = new ArrayList<>();

        if (parent != null) {
            ancestors.add(parent);
            ancestors.addAll(parent.getAncestors());
        }

        return ancestors;
    }

    @Override
    public String getLabel() {
        return getName();
    }
}
