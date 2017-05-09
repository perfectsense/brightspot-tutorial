package com.company.position;

import com.company.organization.Organization;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;

@ToolUi.Publishable(false)
public class Position extends Content {

    @Required
    @Indexed(unique = true)
    private String name;

    @Indexed
    @Required
    @ToolUi.DropDown
    private Organization organization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String getLabel() {
        return getName();
    }
}
