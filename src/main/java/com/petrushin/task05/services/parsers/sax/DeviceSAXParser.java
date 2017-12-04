package com.petrushin.task05.services.parsers.sax;

import com.petrushin.task05.builder.DeviceBuilder;
import com.petrushin.task05.builder.TypeBuilder;
import com.petrushin.task05.domain.Device;
import com.petrushin.task05.services.parsers.Parser;
import com.petrushin.task05.validator.XsdSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.ValidationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeviceSAXParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger(DeviceSAXParser.class);

    @Override
    public List<Device> parse(String xmlLocation) {
        List<Device> devices = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            TypeBuilder typeBuilder = new TypeBuilder();
            DeviceBuilder deviceBuilder = new DeviceBuilder(typeBuilder);
            DeviceHandler handler = new DeviceHandler(deviceBuilder);
            File file = new File(xmlLocation);
            parser.parse(file, handler);
            devices = handler.getDevices();

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return devices;

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
