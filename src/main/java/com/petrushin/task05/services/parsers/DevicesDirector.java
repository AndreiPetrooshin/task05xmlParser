package com.petrushin.task05.services.parsers;

import com.petrushin.task05.domain.Device;

import java.util.List;

public class DevicesDirector {

    private Parser parser;

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public List<Device> buildDevices(String xmlLocation, String schemaLocation) {
        if (schemaLocation == null) {
            return parser.parse(xmlLocation);
        } else {
            return parser.parse(xmlLocation, schemaLocation);
        }
    }

}
