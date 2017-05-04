package com.company.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.employee.Employee;
import com.company.organization.Organization;
import com.company.position.Position;
import com.company.project.Project;
import com.company.team.Team;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUser;
import com.psddev.dari.db.Database;
import com.psddev.dari.db.ForwardingDatabase;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.DebugFilter;
import com.psddev.dari.util.DebugServlet;
import com.psddev.dari.util.StringUtils;

public class SampleDataLoad extends DebugServlet {

    @Override
    public String getName() {
        return "Company Intranet: Sample Data Loader";
    }

    @Override
    public List<String> getPaths() {
        return Collections.singletonList("company-intranet-sample-data-load");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new DebugFilter.PageWriter(getServletContext(), req, resp) { {
            startPage("Company Intranet", "Sample Data Loader"); {

                writeStart("h2").writeHtml("Company Intranet - Sample Data Loader").writeEnd();

                if (!"start".equals(req.getParameter("action"))) {

                    writeStart("form", "action", "", "class", "form-horizontal", "method", "post"); {

                        writeTag("input", "type", "hidden", "name", "action", "value", "start");

                        writeStart("div", "class", "form-actions"); {
                            writeTag("input", "type", "submit", "class", "btn btn-success", "value", "Load Data");
                        } writeEnd();

                    } writeEnd();

                } else {

                    ForwardingDatabase db = new ForwardingDatabase() {
                        @Override
                        protected <T> Query<T> filterQuery(Query<T> query) {
                            return query.clone()
                                    .master()
                                    .noCache()
                                    .resolveInvisible();
                        }
                    };
                    db.setDelegate(Database.Static.getDefault());
                    Database.Static.overrideDefault(db);

                    try {
                        Map<String, Function<String, List<Record>>> dataLoadFunctions = new LinkedHashMap<>();

                        dataLoadFunctions.put("/positions.csv", line -> createPositionsAndOrgs(line));
                        dataLoadFunctions.put("/employees.csv", line -> createEmployee(line));
                        dataLoadFunctions.put("/projects.csv", line -> createProject(line));
                        dataLoadFunctions.put("/teams.csv", line -> createTeam(line));

                        for (Map.Entry<String, Function<String, List<Record>>> entry : dataLoadFunctions.entrySet()) {

                            String resourcePath = entry.getKey();

                            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(getClass()
                                    .getResourceAsStream(resourcePath)))) {

                                for (String line : (Iterable<String>) () -> buffer.lines().iterator()) {

                                    try {
                                        List<Record> records = entry.getValue().apply(line);

                                        if (records == null) {
                                            continue;
                                        }

                                        for (Record record : records) {

                                            String typeLabel = record.getState().getType().getLabel();
                                            String objectLabel = record.getLabel();

                                            writeStart("li"); {
                                                writeHtml("Updated " + typeLabel + ": " + objectLabel);
                                            } writeEnd();
                                        }

                                    } catch (RuntimeException e) {
                                        writeStart("li"); {
                                            writeHtml("ERROR: Failed to create objects from: " + line + " Cause: " + e.getMessage());
                                        } writeEnd();
                                    }
                                }
                            }
                        }

                    } finally {
                        Database.Static.restoreDefault();
                    }
                }

            } endPage();
        } };
    }

    private List<Record> createEmployee(String line) {

        List<Record> records = new ArrayList<>();

        if (StringUtils.isBlank(line)) {
            return records;
        }

        String[] parts = line.split(",");

        if (parts.length == 2) {
            String firstName = parts[0];
            String lastName = parts[1];

            if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
                return null;
            }

            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(StringUtils.toNormalized(firstName) + "." + StringUtils.toNormalized(lastName) + "@company.com");
            // FIXME: hack to make saveUniquely work with indexed methods
            employee.getState().put("getFullName", employee.getFullName());
            updateRecordWithCmsData(employee);

            employee.saveUniquely();

            records.add(employee);
        }

        return records;
    }

    private List<Record> createPositionsAndOrgs(String line) {

        List<Record> list = new ArrayList<>();

        String[] parts = line.split(",");
        if (parts.length == 2) {

            String posName = parts[0];
            String orgName = parts[1];

            if (StringUtils.isBlank(posName) || StringUtils.isBlank(orgName)) {
                return list;
            }

            // organization

            Organization organization = new Organization();
            organization.setName(orgName);
            updateRecordWithCmsData(organization);

            organization.saveUniquely();

            list.add(organization);

            // position

            Position position = new Position();
            position.setName(posName);
            position.setOrganization(organization);
            updateRecordWithCmsData(position);

            position.saveUniquely();

            list.add(position);
        }

        return list;
    }

    private List<Record> createProject(String line) {

        List<Record> records = new ArrayList<>();

        if (StringUtils.isBlank(line)) {
            return records;
        }

        Project project = new Project();
        project.setName(line);
        updateRecordWithCmsData(project);

        project.saveUniquely();

        records.add(project);

        return records;
    }

    private List<Record> createTeam(String line) {

        List<Record> records = new ArrayList<>();

        if (StringUtils.isBlank(line)) {
            return records;
        }

        Team team = new Team();
        team.setName(line);
        updateRecordWithCmsData(team);

        team.saveUniquely();

        records.add(team);

        return records;
    }

    private void updateRecordWithCmsData(Record record) {

        ToolUser user = Query.from(ToolUser.class).first();

        record.as(Content.ObjectModification.class).setUpdateDate(new Date());
        record.as(Content.ObjectModification.class).setPublishDate(new Date());

        record.as(Content.ObjectModification.class).setUpdateUser(user);
        record.as(Content.ObjectModification.class).setPublishUser(user);
    }
}
