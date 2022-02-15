package com.brightspot.tutorial.graphql.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Demo 6 - Employee")
public class Demo6Employee extends Content {

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    private String title;

    private List<Demo6ProjectRole> projects;

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

    public List<Demo6ProjectRole> getProjects() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        return projects;
    }

    public void setProjects(List<Demo6ProjectRole> projects) {
        this.projects = projects;
    }

    @ToolUi.Hidden
    @Indexed(unique = true)
    public String getFullName() {
        return Stream.of(getFirstName(), getLastName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }

    @Override
    public String getLabel() {
        return getFullName();
    }
}
