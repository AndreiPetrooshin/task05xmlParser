package com.petrushin.task05.services.parsers;

import com.petrushin.task05.domain.Device;

import java.util.List;

public interface Parser {

    List<Device> parse(String xmlLocation);

    List<Device> parse(String xmlLocation, String schemaLocation);


}
