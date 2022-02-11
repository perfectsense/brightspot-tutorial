package com.brightspot.tutorial.graphql.demo10;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.web.annotation.WebParameter;

@ViewInterface
public class Demo10ViewModel extends ViewModel<MyTenthGraphQLEndpoint> {

    @WebParameter
    private String message;

    @Demo10SearchFiltersParameter
    private Demo10SearchFilters searchFilters;

    public String getMessage() {
        return "The message is: " + message;
    }

    public String getNameSearchFilter() {
        return "The name is: " + searchFilters.getNameFilter();
    }

    public String getAddressSearchFilter() {
        String addressLabel = null;

        Demo10UsAddress address = searchFilters.getAddressFilter();
        if (address != null) {
            addressLabel = address.getLabel();
        }

        return "The address is: " + addressLabel;
    }
}
