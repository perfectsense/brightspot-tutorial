package com.company.debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.company.Company;
import com.company.company.CompanyDataGenerator;
import com.company.employee.EmployeeDataGenerator;
import com.company.position.PositionDataGenerator;
import com.company.project.ProjectDataGenerator;
import com.company.team.TeamDataGenerator;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.ToolUser;
import com.psddev.dari.db.Database;
import com.psddev.dari.db.ForwardingDatabase;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.DebugFilter;
import com.psddev.dari.util.DebugServlet;

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
                    writeStart("form",
                            "action", "",
                            "class", "form-horizontal",
                            "method", "post",
                            "onsubmit", "this.querySelectorAll('input[type=\"submit\"]')[0].setAttribute('disabled', 'disabled');"); {

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
                        try {
                            try {

                                Random random = new Random();
                                Consumer<Record> recordUpdater = SampleDataLoad::updateRecordWithCmsData;

                                CompanyDataGenerator companyGenerator = new CompanyDataGenerator(recordUpdater, random);
                                //OrganizationDataGenerator organizationGenerator = new OrganizationDataGenerator(recordUpdater);
                                PositionDataGenerator positionGenerator = new PositionDataGenerator(recordUpdater);
                                TeamDataGenerator teamGenerator = new TeamDataGenerator(recordUpdater);
                                EmployeeDataGenerator employeeGenerator = new EmployeeDataGenerator(recordUpdater, random);
                                ProjectDataGenerator projectGenerator = new ProjectDataGenerator(recordUpdater, random);

                                List<Record> generatedRecords = new ArrayList<>();

                                Company company = companyGenerator.generateCompany();
                                if (company != null) {
                                    generatedRecords.add(company);
                                }

                                //generatedRecords.addAll(organizationGenerator.generateOrganizations());
                                generatedRecords.addAll(positionGenerator.generatePositions());
                                generatedRecords.addAll(teamGenerator.generateTeams());
                                generatedRecords.addAll(employeeGenerator.generateEmployees(200));
                                generatedRecords.addAll(projectGenerator.generateProjects());

                                page.writeStart("ol");
                                {
                                    for (Record record : generatedRecords) {

                                        String typeLabel = record.getState().getType().getLabel();
                                        String objectLabel = record.getLabel();

                                        page.writeStart("li");
                                        {
                                            page.writeHtml("Updated " + typeLabel + ": " + objectLabel);
                                        }
                                        page.writeEnd();
                                    }
                                }
                                page.writeEnd();

                            } catch (RuntimeException e) {
                                page.writeStart("li"); {
                                    page.writeHtml("ERROR: Failed to finish generator objects! Cause: " + e.getMessage());
                                } page.writeEnd();
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } finally {
                        Database.Static.restoreDefault();
                    }
                }

            } endPage();
        } };
    }

    private static void updateRecordWithCmsData(Record record) {

        ToolUser user = Query.from(ToolUser.class).first();

        record.as(Content.ObjectModification.class).setUpdateDate(new Date());
        record.as(Content.ObjectModification.class).setPublishDate(new Date());

        record.as(Content.ObjectModification.class).setUpdateUser(user);
        record.as(Content.ObjectModification.class).setPublishUser(user);

        Directory.Data dirData = record.as(Directory.Data.class);
        dirData.clearPaths();
        for (Directory.Path path : record.as(Directory.ObjectModification.class).createPaths(null)) {
            dirData.addPath(path.getSite(), path.getPath(), path.getType());
        }
    }
}
