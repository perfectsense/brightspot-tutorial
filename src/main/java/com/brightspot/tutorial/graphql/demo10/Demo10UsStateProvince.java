package com.brightspot.tutorial.graphql.demo10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Demo10UsStateProvince {

    ALABAMA("Alabama", "AL"),
    ALASKA("Alaska", "AK"),
    //AMERICAN_SAMOA("American Samoa", "AS"),
    ARIZONA("Arizona", "AZ"),
    ARKANSAS("Arkansas", "AR"),
    CALIFORNIA("California", "CA"),
    COLORADO("Colorado", "CO"),
    CONNECTICUT("Connecticut", "CT"),
    DELAWARE("Delaware", "DE"),
    DISTRICT_OF_COLUMBIA("District of Columbia", "DC"),
    FLORIDA("Florida", "FL"),
    GEORGIA("Georgia", "GA"),
    HAWAII("Hawaii", "HI"),
    IDAHO("Idaho", "ID"),
    ILLINOIS("Illinois", "IL"),
    INDIANA("Indiana", "IN"),
    IOWA("Iowa", "IA"),
    KANSAS("Kansas", "KS"),
    KENTUCKY("Kentucky", "KY"),
    LOUISIANA("Louisiana", "LA"),
    MAINE("Maine", "ME"),
    MARYLAND("Maryland", "MD"),
    MASSACHUSETTS("Massachusetts", "MA"),
    MICHIGAN("Michigan", "MI"),
    MINNESOTA("Minnesota", "MN"),
    MISSISSIPPI("Mississippi", "MS"),
    MISSOURI("Missouri", "MO"),
    MONTANA("Montana", "MT"),
    NEBRASKA("Nebraska", "NE"),
    NEVADA("Nevada", "NV"),
    NEW_HAMPSHIRE("New Hampshire", "NH"),
    NEW_JERSEY("New Jersey", "NJ"),
    NEW_MEXICO("New Mexico", "NM"),
    NEW_YORK("New York", "NY"),
    NORTH_CAROLINA("North Carolina", "NC"),
    NORTH_DAKOTA("North Dakota", "ND"),
    OHIO("Ohio", "OH"),
    OKLAHOMA("Oklahoma", "OK"),
    OREGON("Oregon", "OR"),
    PENNSYLVANIA("Pennsylvania", "PA"),
    RHODE_ISLAND("Rhode Island", "RI"),
    SOUTH_CAROLINA("South Carolina", "SC"),
    SOUTH_DAKOTA("South Dakota", "SD"),
    TENNESSEE("Tennessee", "TN"),
    TEXAS("Texas", "TX"),
    UTAH("Utah", "UT"),
    VERMONT("Vermont", "VT"),
    VIRGINIA("Virginia", "VA"),
    WASHINGTON("Washington", "WA"),
    WEST_VIRGINIA("West Virginia", "WV"),
    WISCONSIN("Wisconsin", "WI"),
    WYOMING("Wyoming", "WY");

    /**
     * The state's name.
     */
    private String name;

    /**
     * The state's abbreviation.
     */
    private String abbreviation;

    /**
     * The set of states addressed by abbreviations.
     */
    private static final Map<String, Demo10UsStateProvince> STATES_BY_ABBR = new HashMap<>(); static {
        for (Demo10UsStateProvince state : values()) {
            STATES_BY_ABBR.put(state.getAbbreviation(), state);
        }
    }

    /**
     * The set of states addressed by their abbreviation's first letter.
     */
    private static final Map<String, List<Demo10UsStateProvince>> STATES_BY_ABBR_FIRST_LETTER = new HashMap<>(); static {
        for (Demo10UsStateProvince state : values()) {
            STATES_BY_ABBR_FIRST_LETTER.computeIfAbsent(
                state.getAbbreviation().substring(0, 1),
                key -> new ArrayList<>()).add(state);
        }
    }

    /**
     * Constructs a new state.
     *
     * @param name the state's name.
     * @param abbreviation the state's abbreviation.
     */
    Demo10UsStateProvince(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the state's abbreviation.
     *
     * @return the state's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr the state's abbreviation.
     * @return the enum constant with the specified abbreviation.
     */
    public static Demo10UsStateProvince valueOfAbbreviation(final String abbr) {
        return STATES_BY_ABBR.get(abbr.toUpperCase());
    }

    public static Demo10UsStateProvince valueOfName(final String name) {
        final String enumName = name.toUpperCase().replace(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Gets the enum constants with the abbreviation starting with the specified first letter.
     *
     * @param abbrFirstLetter the state abbreviation's first letter.
     * @return the enum constant with the specified abbreviation.
     */
    public static List<Demo10UsStateProvince> valuesOfAbbreviationFirstLetter(final String abbrFirstLetter) {
        return STATES_BY_ABBR_FIRST_LETTER.getOrDefault(abbrFirstLetter.toUpperCase(), Collections.emptyList());
    }

    @Override
    public String toString() {
        return name;
    }
}
