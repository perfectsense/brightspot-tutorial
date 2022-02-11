package com.brightspot.tutorial.graphql.demo6;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;

public class Demo6 extends Content {

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ToolUi.Hidden
    @Indexed(unique = true)
    public String getFullName() {
        return Stream.of(getFirstName(), getLastName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }
}
