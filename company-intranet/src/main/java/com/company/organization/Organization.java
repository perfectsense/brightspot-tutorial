package com.company.organization;

import com.company.company.Company;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@ToolUi.Publishable(false)
@Recordable.BootstrapPackages("temp")
public class Organization extends Content {

    @Required
    @Indexed(unique = true)
    private String name;

    // DELETE ME
    @Indexed
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return getName();
    }
}
