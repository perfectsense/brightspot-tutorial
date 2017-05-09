package com.company.team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.company.debug.ResourceFileReader;

import com.psddev.dari.db.Record;

public class TeamDataGenerator {

    private Consumer<Record> recordUpdater;

    public TeamDataGenerator(Consumer<Record> recordUpdater) {
        this.recordUpdater = recordUpdater;
    }

    public List<Team> generateTeams() throws IOException {

        TeamsData teamsData = TeamsData.load();

        List<Team> teams = new ArrayList<>();

        for (String teamName : teamsData.getTeams()) {
            Team team = new Team();
            team.setName(teamName);

            if (recordUpdater != null) {
                recordUpdater.accept(team);
            }

            team.saveUniquely();
            teams.add(team);
        }

        return teams;
    }

    private static class TeamsData {

        static final String RESOURCE_FILE_PATH = "/data/teams.csv";

        Set<String> teams;

        TeamsData(Set<String> teams) {
            this.teams = teams;
        }

        Set<String> getTeams() {
            return teams;
        }

        static TeamsData load() throws IOException {
            Set<String> teams = new HashSet<>();

            for (String team : ResourceFileReader.getFileLines(RESOURCE_FILE_PATH)) {
                teams.add(team);
            }

            return new TeamsData(teams);
        }
    }
}
