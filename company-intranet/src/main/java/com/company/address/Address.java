package com.company.address;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;

@Recordable.Embedded
public class Address extends Content {

    @Required
    private String street;

    @Indexed
    @Required
    private String city;

    @Indexed
    @Required
    private StateProvince stateProvince;

    @Indexed
    @Required
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StateProvince getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(StateProvince stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getLabel() {
        String streetLabel = street != null ? "Unknown Street " : null;
        String cityLabel = city != null ? city : "Unknown City";
        String stateProvinceLabel = getStateProvince() != null ? getStateProvince().name() : "??";
        String zipCodeLabel = zipCode != null ? zipCode : "?????";
        return streetLabel + ", " + cityLabel + ", " + stateProvinceLabel + " " + zipCodeLabel;
    }
}
