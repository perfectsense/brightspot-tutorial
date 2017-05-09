package com.company.address;

import java.util.Arrays;

public enum StateProvince {

    AL("Alabama", "AL"),
    AK("Alaska", "AK"),
    AZ("Arizona", "AZ"),
    AR("Arkansas", "AR"),
    CA("California", "CA"),
    CO("Colorado", "CO"),
    CT("Connecticut", "CT"),
    DC("Washington, DC", "DC"),
    DE("Delaware", "DE"),
    FL("Florida", "FL"),
    GA("Georgia", "GA"),
    HI("Hawaii", "HI"),
    ID("Idaho", "ID"),
    IL("Illinois", "IL"),
    IN("Indiana", "IN"),
    IA("Iowa", "IA"),
    KS("Kansas", "KS"),
    KY("Kentucky", "KY"),
    LA("Louisiana", "LA"),
    ME("Maine", "ME"),
    MD("Maryland", "MD"),
    MA("Massachusetts", "MA"),
    MI("Michigan", "MI"),
    MN("Minnesota", "MN"),
    MS("Mississippi", "MS"),
    MO("Missouri", "MO"),
    MT("Montana", "MT"),
    NE("Nebraska", "NE"),
    NV("Nevada", "NV"),
    NH("New Hampshire", "NH"),
    NJ("New Jersey", "NJ"),
    NM("New Mexico", "NM"),
    NY("New York", "NY"),
    NC("North Carolina", "NC"),
    ND("North Dakota", "ND"),
    OH("Ohio", "OH"),
    OK("Oklahoma", "OK"),
    OR("Oregon", "OR"),
    PA("Pennsylvania", "PA"),
    RI("Rhode Island", "RI"),
    SC("South Carolina", "SC"),
    SD("South Dakota", "SD"),
    TN("Tennessee", "TN"),
    TX("Texas", "TX"),
    UT("Utah", "UT"),
    VT("Vermont", "VT"),
    VA("Virginia", "VA"),
    WA("Washington", "WA"),
    WV("West Virginia", "WV"),
    WI("Wisconsin", "WI"),
    WY("Wyoming", "WY");

    private String name;
    private String abbreviation;

    StateProvince(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return "(" + getAbbreviation() + ") " + getName();
    }

    public static StateProvince fromAbbreviation(String fromAbbreviation) {
        return Arrays.stream(StateProvince.values()).filter(sp -> sp.getAbbreviation().equalsIgnoreCase(fromAbbreviation)).findFirst().orElse(null);
    }

    public static StateProvince fromName(String fromName) {
        return Arrays.stream(StateProvince.values()).filter(sp -> sp.getName().equalsIgnoreCase(fromName)).findFirst().orElse(null);
    }
}
