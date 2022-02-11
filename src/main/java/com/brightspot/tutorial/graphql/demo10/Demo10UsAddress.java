package com.brightspot.tutorial.graphql.demo10;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;
import org.apache.commons.lang3.StringUtils;

@Recordable.Embedded
public class Demo10UsAddress extends Content {

    @Indexed
    @ToolUi.CssClass("is-minimal")
    private String streetAddress;

    @Indexed
    @ToolUi.CssClass("is-minimal")
    private String city;

    @Indexed
    @DisplayName("State / Province")
    @ToolUi.CssClass("is-minimal")
    @ToolUi.ValueGeneratorClass(Demo10UsStateProvinceValueGenerator.class)
    //@ToolUi.Filterable
    private String stateProvince;

    @Indexed
    @ToolUi.CssClass("is-minimal")
    private String zipCode;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Indexed
    @ToolUi.Hidden
    @ToolUi.Filterable
    public Demo10UsStateProvince getStateProvince() {
        return Optional.ofNullable(stateProvince)
            .map(Demo10UsStateProvince::valueOfAbbreviation)
            .orElse(null);
    }

    public void setStateProvince(Demo10UsStateProvince stateProvince) {
        this.stateProvince = stateProvince != null ? stateProvince.getAbbreviation() : null;
    }
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getLabel() {
        return Stream.of(
                getStreetAddress(),
                getCity(),
                Stream.of(
                        Optional.ofNullable(getStateProvince())
                            .map(Demo10UsStateProvince::getAbbreviation)
                            .orElse(null),
                        getZipCode())
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(" ")))
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.joining(", "));
    }
}
