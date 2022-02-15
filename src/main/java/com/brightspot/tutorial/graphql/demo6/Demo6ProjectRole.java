package com.brightspot.tutorial.graphql.demo6;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
@Recordable.DisplayName("Demo 6 - Project Role")
public class Demo6ProjectRole extends Content {

    private String projectName;

    private String employeeRole;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
