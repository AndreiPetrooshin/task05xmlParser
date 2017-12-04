package com.petrushin.task05.services.parsers.jaxb;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Devices;
import com.petrushin.task05.services.parsers.Parser;
import com.petrushin.task05.validator.XsdSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.util.List;

public class DeviceJAXBParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(DeviceJAXBParser.class);

    public DeviceJAXBParser() {
    }

    @Override
    public List<Device> parse(String xmlLocation) {
        List<Device> deviceList = null;
        Unmarshaller unmarshaller;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Devices.class);
            unmarshaller = context.createUnmarshaller();
            File xml = new File(xmlLocation);
            Devices devices = (Devices) unmarshaller.unmarshal(xml);
            deviceList = devices.getDevice();
        } catch (JAXBException e) {
            LOGGER.error("Error with parsing xml file");
        }
        return deviceList;
    }

    @Override
    public List<Device> parse(String xmlLocation, String schemaLocation) {
        try {
            XsdSchemaValidator xsdSchemaValidator = new XsdSchemaValidator();
            xsdSchemaValidator.validate(xmlLocation, schemaLocation);
        } catch (ValidationException e) {
            LOGGER.error("Schema is not valid or path is incorrect!");
        }
        return parse(xmlLocation);
    }
}
