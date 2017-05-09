package com.company.position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import com.company.debug.ResourceFileReader;
import com.company.organization.Organization;
import com.company.organization.OrganizationDataGenerator;

import com.psddev.dari.db.Record;
import com.psddev.dari.util.StringUtils;

public class PositionDataGenerator {

    private Consumer<Record> recordUpdater;

    public PositionDataGenerator(Consumer<Record> recordUpdater) {
        this.recordUpdater = recordUpdater;
    }

    public List<Position> generatePositions() throws IOException {

        PositionsData positionsData = PositionsData.load();

        List<Position> positions = new ArrayList<>();

        Set<String> orgNames = new HashSet<>(positionsData.getPositions().values());
        Map<String, Organization> orgs = new OrganizationDataGenerator(recordUpdater).generateOrganizations(orgNames);

        for (Map.Entry<String, String> entry : positionsData.getPositions().entrySet()) {

            Position position = new Position();
            position.setName(entry.getKey());
            position.setOrganization(orgs.get(entry.getValue()));

            if (recordUpdater != null) {
                recordUpdater.accept(position);
            }

            position.saveUniquely();

            positions.add(position);
        }

        return positions;
    }

    private static class PositionsData {

        static final String RESOURCE_FILE_PATH = "/data/positions.csv";

        // positions and orgs
        Map<String, String> positions = new HashMap<>();

        PositionsData(Map<String, String> positions) {
            this.positions = positions;
        }

        public Map<String, String> getPositions() {
            return positions;
        }

        static PositionsData load() throws IOException {

            Map<String, String> positions = new HashMap<>();

            for (String line : ResourceFileReader.getFileLines(RESOURCE_FILE_PATH)) {

                String position = null;
                String org = null;

                String[] parts = StringUtils.fromCsv(line);

                if (parts.length >= 2) {
                    position = parts[0];
                    org = parts[1];
                }

                if (!StringUtils.isBlank(position) && !StringUtils.isBlank(org)) {
                    positions.put(position, org);
                }
            }

            return new PositionsData(positions);
        }
    }
}
