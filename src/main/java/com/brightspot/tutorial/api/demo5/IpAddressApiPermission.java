package com.brightspot.tutorial.api.demo5;

import java.util.LinkedHashSet;
import java.util.Set;

import com.psddev.cms.api.ApiPermission;
import com.psddev.cms.api.ApiRequest;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("IP Allow List")
public class IpAddressApiPermission extends ApiPermission {

    @DisplayName("Allowed IP Addresses")
    private Set<String> ipAddresses;

    public Set<String> getIpAddresses() {
        if (ipAddresses == null) {
            ipAddresses = new LinkedHashSet<>();
        }
        return ipAddresses;
    }

    public void setIpAddresses(Set<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @Override
    public String getLabel() {
        return String.join(", ", getIpAddresses());
    }

    @Override
    public boolean hasPermission(ApiRequest request) {
        return getIpAddresses().contains(request.getOriginal().as(IpAddressApiRequest.class).getIpAddress());
    }
}
