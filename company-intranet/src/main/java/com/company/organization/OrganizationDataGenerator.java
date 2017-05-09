package com.company.organization;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import com.psddev.dari.db.Record;

public class OrganizationDataGenerator {

    private Consumer<Record> recordUpdater;

    public OrganizationDataGenerator(Consumer<Record> recordUpdater) {
        this.recordUpdater = recordUpdater;
    }

    public Map<String, Organization> generateOrganizations(Set<String> names) {

        Map<String, Organization> organizations = new HashMap<>();

        for (String name : names) {
            Organization organization = new Organization();
            organization.setName(name);

            if (recordUpdater != null) {
                recordUpdater.accept(organization);
            }

            organization.saveUniquely();

            organizations.put(name, organization);
        }

        return organizations;
    }
}
