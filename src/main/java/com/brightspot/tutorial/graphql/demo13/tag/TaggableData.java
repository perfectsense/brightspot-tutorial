package com.brightspot.tutorial.graphql.demo13.tag;

import java.util.ArrayList;
import java.util.List;

import com.psddev.dari.db.Modification;

public class TaggableData extends Modification<Taggable> {

    @Indexed
    private List<Tag> tags;

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
