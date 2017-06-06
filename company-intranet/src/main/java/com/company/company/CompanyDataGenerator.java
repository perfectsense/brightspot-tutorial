package com.company.company;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;

import com.psddev.dari.db.Query;
import com.psddev.dari.db.Record;

public class CompanyDataGenerator {

    private Consumer<Record> recordUpdater;
    private Random random;

    public CompanyDataGenerator(Consumer<Record> recordUpdater) {
        this(recordUpdater, new Random());
    }

    public CompanyDataGenerator(Consumer<Record> recordUpdater, Random random) {
        this.recordUpdater = recordUpdater;
        this.random = random;
    }

    public Company generateCompany() {

        Company company = Query.from(Company.class).first();

        if (company == null) {
            company = new Company();
        }

        if (company.getName() == null || company.getFoundedOn() == null) {

            if (company.getName() == null) {
                company.setName("Perfect Sense Inc");
            }

            if (company.getFoundedOn() == null) {
                company.setFoundedOn(Date.from(LocalDate.of(2014, 10, 23).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            if (recordUpdater != null) {
                recordUpdater.accept(company);
            }

            company.save();

            return company;
        }

        return null;
    }
}
