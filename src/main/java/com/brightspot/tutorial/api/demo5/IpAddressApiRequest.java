package com.brightspot.tutorial.api.demo5;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.Optional;

import com.google.common.net.InetAddresses;
import com.psddev.dari.web.WebRequest;
import com.psddev.dari.web.WebRequestExtension;

public class IpAddressApiRequest extends WebRequestExtension {

    private String ipAddress;

    public String getIpAddress() {
        if (ipAddress == null) {
            ipAddress = getRemoteAddress(getOriginal());
        }
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public static String getRemoteAddress(WebRequest request) {
        return Optional.ofNullable(request.getHeader("X-Forwarded-For"))
            .flatMap(ipList -> Arrays.stream(ipList.split(",")).findFirst())
            .map(String::trim)
            .map(InetAddresses::forString)
            .map(InetAddresses::getCoercedIPv4Address)
            .map(Inet4Address::getHostAddress)
            .orElse(null);
    }
}
