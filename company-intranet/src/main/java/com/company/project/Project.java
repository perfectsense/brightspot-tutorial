package com.company.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.company.employee.Employee;
import com.company.event.CompanyEvent;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.StringUtils;

public class Project extends Content implements
        CompanyEvent,
        Directory.Item {

    @Required
    @Indexed(unique = true)
    private String name;

    @ToolUi.RichText
    private String description;

    private Date startDate;

    private Date endDate;

    @DisplayName("Employees")
    @Embedded
    private List<ProjectEmployee> projectEmployees;

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

    public List<ProjectEmployee> getProjectEmployees() {
        if (projectEmployees == null) {
            projectEmployees = new ArrayList<>();
        }

        updateProjectEmployees();

        return projectEmployees;
    }

    public void setProjectEmployees(List<ProjectEmployee> projectEmployees) {
        this.projectEmployees = projectEmployees;
    }

    @Override
    public Date getCompanyEventDate() {
        return getStartDate();
    }

    public Set<Employee> getAllEmployees() {
        return getProjectEmployees()
                .stream()
                .map(ProjectEmployee::getEmployee)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @DisplayName("Active Employees")
    @Ignored(false)
    public List<Employee> getActiveEmployees() {

        Date now = new Date();

        if (getEndDate() != null && getEndDate().before(now)) {
            return Collections.emptyList();
        }

        return getProjectEmployees()
                .stream()
                .filter(pe -> now.after(pe.getStartDate())
                        && (pe.getEndDate() == null || pe.getEndDate().after(now)))
                .map(ProjectEmployee::getEmployee)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public String createPermalink(Site site) {

        String normalizedName = StringUtils.toNormalized(getName());
        if (!StringUtils.isBlank(normalizedName)) {
            return "/projects/" + normalizedName;
        }

        return null;
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();
        updateProjectEmployees();
    }

    private void updateProjectEmployees() {
        if (projectEmployees != null) {

            for (ProjectEmployee pe : projectEmployees) {
                pe.setProject(this);
            }
        }
    }
}
