package com.company.project;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;

import com.company.company.Company;
import com.company.company.CompanyDataGenerator;
import com.company.debug.ResourceFileReader;
import com.company.employee.Employee;
import com.company.employee.EmployeeDataGenerator;

import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;

public class ProjectDataGenerator {

    private Consumer<Record> recordUpdater;
    private Random random;

    public ProjectDataGenerator(Consumer<Record> recordUpdater) {
        this(recordUpdater, new Random());
    }

    public ProjectDataGenerator(Consumer<Record> recordUpdater, Random random) {
        this.recordUpdater = recordUpdater;
        this.random = random;
    }

    public List<Project> generateProjects() throws IOException {

        List<Project> saved = new ArrayList<>();

        for (Project project : generateProjects(recordUpdater, random)) {

            try {
                if (recordUpdater != null) {
                    recordUpdater.accept(project);
                }

                project.save();

                saved.add(project);

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        return saved;
    }

    private static List<Project> generateProjects(Consumer<Record> recordUpdater, Random random) throws IOException {

        List<Project> projects = new ArrayList<>();

        ProjectsData projectsData = ProjectsData.load();
        Set<String> projectNames = new HashSet<>(projectsData.getProjects());

        for (Project project : Query.from(Project.class).iterable(0)) {
            projectNames.remove(project.getName());
        }

        if (projectNames.isEmpty()) {
            return projects;
        }

        List<Employee> employees = Query.from(Employee.class).selectAll();

        if (employees.isEmpty()) {
            employees = new EmployeeDataGenerator(recordUpdater, random).generateEmployees(200);
        }

        Company company = Query.from(Company.class).first();
        if (company.getName() == null || company.getFoundedOn() == null) {
            company = new CompanyDataGenerator(recordUpdater, random).generateCompany();
        }

        for (String projectName : projectNames) {

            Project project = new Project();

            int minProjectDurationMonths = 4;
            int maxProjectDurationMonths = 25;

            int projectDurationMonths = minProjectDurationMonths + random.nextInt(maxProjectDurationMonths - minProjectDurationMonths);
            long projectDurationMillis = Duration.ofDays(projectDurationMonths * 30).toMillis();

            Date begin = company.getFoundedOn();
            Date now = new Date();

            Date projectStart = new Date(begin.getTime() + (long) (random.nextDouble() * (now.getTime() - begin.getTime())));
            Date projectEnd = new Date(projectStart.getTime() + projectDurationMillis);

            if (projectEnd.after(now)) {
                projectEnd = null;
            }

            project.setName(projectName);
            project.setDescription("A description of the " + projectName + " project.");
            project.setStartDate(projectStart);
            project.setEndDate(projectEnd);

            List<Employee> possibleEmployees = new ArrayList<>();

            for (Employee employee : employees) {

                Date employeeStart = employee.getDateOfHire();
                Date employeeEnd = employee.getDateOfTermination();

                if ((employeeStart != null && employeeStart.before(projectStart))
                        && (employeeEnd == null || (projectEnd != null && employeeEnd.after(projectEnd)))) {

                    possibleEmployees.add(employee);
                }
            }

            int minProjectMembers = 3;
            int maxProjectMembers = 15;
            int projectMemberCount = minProjectMembers + random.nextInt(maxProjectMembers - minProjectMembers);

            Collections.shuffle(possibleEmployees, random);

            if (projectMemberCount < possibleEmployees.size()) {
                possibleEmployees = possibleEmployees.subList(0, projectMemberCount);
            }

            List<ProjectEmployee> projectEmployees = new ArrayList<>();

            for (Employee employee : possibleEmployees) {

                ProjectEmployee pe = new ProjectEmployee();
                pe.setProject(project);
                pe.setEmployee(employee);

                long peMin = projectStart.getTime();
                long peMax = projectEnd != null ? projectEnd.getTime() : now.getTime();

                pe.setStartDate(new Date(peMin + (long) (random.nextDouble() * (peMax - peMin))));

                projectEmployees.add(pe);
            }

            project.setProjectEmployees(projectEmployees);

            projects.add(project);
        }

        return projects;
    }

    private static class ProjectsData {

        static final String RESOURCE_FILE_PATH = "/data/projects.csv";

        Set<String> projects;

        ProjectsData(Set<String> projects) {
            this.projects = projects;
        }

        Set<String> getProjects() {
            return projects;
        }

        static ProjectsData load() throws IOException {
            Set<String> projects = new HashSet<>();

            for (String project : ResourceFileReader.getFileLines(RESOURCE_FILE_PATH)) {
                projects.add(project);
            }

            return new ProjectsData(projects);
        }
    }
}
