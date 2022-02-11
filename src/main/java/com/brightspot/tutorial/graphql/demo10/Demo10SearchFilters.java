package com.brightspot.tutorial.graphql.demo10;

public class Demo10SearchFilters {

    private String nameFilter;

    private Demo10UsAddress addressFilter;

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public Demo10UsAddress getAddressFilter() {
        return addressFilter;
    }

    public void setAddressFilter(Demo10UsAddress addressFilter) {
        this.addressFilter = addressFilter;
    }
}
