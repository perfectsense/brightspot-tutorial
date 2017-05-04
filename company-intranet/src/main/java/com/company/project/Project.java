package com.company.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.company.employee.Employee;
import com.company.event.CompanyEvent;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.StringUtils;

public class Project extends Content implements
        CompanyEvent,
        Directory.Item {

    private static final boolean USE_EMBEDDED_EMPLOYEE_DATA = true;

    @Required
    @Indexed(unique = true)
    private String name;

    @ToolUi.RichText
    private String description;

    private Date startDate;

    private Date endDate;

    // Unhide if USE_EMBEDDED_EMPLOYEE_DATA is set to true
    //@ToolUi.Hidden
    @Embedded
    private List<ProjectEmployee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ProjectEmployee> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }

    public void setEmployees(List<ProjectEmployee> employees) {
        this.employees = employees;
    }

    @Override
    public Date getCompanyEventDate() {
        return getStartDate();
    }

    public List<Employee> getAllEmployees() {

        // flag showing alternate data models to retrieve same data
        if (USE_EMBEDDED_EMPLOYEE_DATA) {
            return getEmployees()
                    .stream()
                    .map(ProjectEmployee::getEmployee)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } else {
            return Query.from(ProjectEmployee.class)
                    .where("project = ?", this)
                    .selectAll()
                    .stream()
                    .map(ProjectEmployee::getEmployee)
                    .collect(Collectors.toList());
        }
    }

    @DisplayName("Active Employees")
    @Ignored(false)
    public List<Employee> getActiveEmployees() {

        Date now = new Date();

        // flag showing alternate data models to retrieve same data
        if (USE_EMBEDDED_EMPLOYEE_DATA) {
            return getEmployees()
                    .stream()
                    .filter(pe -> now.after(pe.getStartDate())
                            && (pe.getEndDate() == null || pe.getEndDate().after(now)))
                    .map(ProjectEmployee::getEmployee)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } else {
            return Query.from(ProjectEmployee.class)
                    .where("project = ?", this)
                    .and("startDate <= ?0 && (endDate > ?0 || endDate = missing)", now)
                    .selectAll()
                    .stream()
                    .map(ProjectEmployee::getEmployee)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String createPermalink(Site site) {

        String normalizedName = StringUtils.toNormalized(getName());
        if (!StringUtils.isBlank(normalizedName)) {
            return "/projects/" + normalizedName;
        }

        return null;
    }
}
