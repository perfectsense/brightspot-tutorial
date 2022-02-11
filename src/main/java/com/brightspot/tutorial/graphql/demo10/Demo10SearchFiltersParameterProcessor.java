package com.brightspot.tutorial.graphql.demo10;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import com.psddev.cms.api.ApiRequest;
import com.psddev.dari.util.CollectionUtils;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.graphql.SchemaInputType;
import com.psddev.graphql.SchemaInputTypes;
import com.psddev.graphql.cda.annotation.ContentDeliveryApiWebAnnotationProcessor;

class Demo10SearchFiltersParameterProcessor implements ContentDeliveryApiWebAnnotationProcessor<Demo10SearchFiltersParameter> {

    private static final String ATM_SEARCH_FILTERS_TYPE = "AtmSearchFilters";

    private static final String NAME_FILTER_FIELD = "name";
    private static final String ADDRESS_FILTER_FIELD = "address";

    private static final String ADDRESS_FILTER_TYPE = "AddressFilter";

    private static final String CITY_FIELD = "city";
    private static final String STATE_FIELD = "state";
    private static final String ZIP_CODE_FIELD = "zipCode";

    @Override
    public Object getValue(ApiRequest request, Object input, Field field, Demo10SearchFiltersParameter annotation) {

        String name = ObjectUtils.to(String.class, CollectionUtils.getByPath(input, NAME_FILTER_FIELD ));
        Demo10UsAddress address = getUsAddressValue(request, CollectionUtils.getByPath(input, ADDRESS_FILTER_FIELD), field, annotation);

        Demo10SearchFilters filters = new Demo10SearchFilters();

        filters.setNameFilter(name);
        filters.setAddressFilter(address);

        return filters;
    }

    private Demo10UsAddress getUsAddressValue(ApiRequest request, Object input, Field field, Demo10SearchFiltersParameter annotation) {

        String city = ObjectUtils.to(String.class, CollectionUtils.getByPath(input, CITY_FIELD));

        Demo10UsStateProvince state = Optional.ofNullable(ObjectUtils.to(String.class, CollectionUtils.getByPath(input, STATE_FIELD)))
            .map(Demo10UsStateProvince::valueOfAbbreviation)
            .orElse(null);

        String zipCode = ObjectUtils.to(String.class, CollectionUtils.getByPath(input, ZIP_CODE_FIELD));

        if (city == null && state == null && zipCode == null) {
            return null;
        }

        Demo10UsAddress address = new Demo10UsAddress();
        address.setCity(city);
        address.setStateProvince(state);
        address.setZipCode(zipCode);
        return address;
    }

    @Override
    public SchemaInputType getInputType(Field field, Demo10SearchFiltersParameter annotation) {

        SchemaInputType nameFilter = SchemaInputTypes.STRING;

        SchemaInputType addressFilter = SchemaInputTypes.newInputObject(ADDRESS_FILTER_TYPE)
            .field(CITY_FIELD, SchemaInputTypes.STRING)
            .field(STATE_FIELD, SchemaInputTypes.enumOf("UsState",
                Arrays.stream(Demo10UsStateProvince.values())
                    .map(Demo10UsStateProvince::getAbbreviation)
                    .toArray(String[]::new)))
            .field(ZIP_CODE_FIELD, SchemaInputTypes.STRING)
            .build();

        SchemaInputType openNowFilter = SchemaInputTypes.BOOLEAN;

        return SchemaInputTypes.newInputObject(ATM_SEARCH_FILTERS_TYPE)
            .field(NAME_FILTER_FIELD, nameFilter)
            .field(ADDRESS_FILTER_FIELD, addressFilter)
            .build();
    }
}
