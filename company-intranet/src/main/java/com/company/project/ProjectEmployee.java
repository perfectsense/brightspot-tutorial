package com.company.project;

import java.util.Date;

import com.company.employee.Employee;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public class ProjectEmployee extends Content {

    private transient Project project;

    @Indexed
    private Employee employee;

    @Indexed
    @Required
    private Date startDate;

    @Indexed
    private Date endDate;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    @Override
    public String getLabel() {
        return getEmployee() != null ? getEmployee().getLabel() : null;
    }
}
