package com.petrushin.task05.services.parsers.stax;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.services.parsers.Parser;
import com.petrushin.task05.validator.XsdSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


/**
 * Class for parsing xml File by using
 * StAX model.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */

public class DeviceStAXParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(DeviceStAXParser.class);


    @Override
    public List<Device> parse(String xmlLocation) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        List<Device> devices = null;
        try {
            InputStream inputStream = new FileInputStream(xmlLocation);
            XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
            DeviceStAXHandler stAXHandlerNew = new DeviceStAXHandler();
            devices = stAXHandlerNew.startParsing(eventReader);
        } catch (XMLStreamException | FileNotFoundException e) {
            LOGGER.error("Error with parsing file:{}", xmlLocation);
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
