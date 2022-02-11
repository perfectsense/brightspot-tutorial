package com.brightspot.tutorial.graphql.demo13.author;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;

public class Author extends Content {

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    @ToolUi.Placeholder(dynamicText = "${content.getDisplayNameFallback()}", editable = true)
    private String displayName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ToolUi.Hidden
    @Indexed
    public String getDisplayName() {
        if (displayName == null) {
            return getDisplayNameFallback();
        }
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayNameFallback() {
        return Stream.of(getFirstName(), getLastName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }

    @Override
    public String getLabel() {
        return Stream.of(getFirstName(), getLastName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }
}
