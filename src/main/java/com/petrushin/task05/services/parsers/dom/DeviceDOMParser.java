package com.petrushin.task05.services.parsers.dom;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.services.builder.DeviceDOMBuilderService;
import com.petrushin.task05.services.parsers.Parser;
import com.petrushin.task05.validator.XsdSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.ValidationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Class for parsing xml File by using
 * DOM model.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */

public class DeviceDOMParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(DeviceDOMParser.class);

    public List<Device> parse(String xmlLocation) {
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        List<Device> devices = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlLocation);
            DeviceDOMBuilderService builder = new DeviceDOMBuilderService();
            devices = builder.buildDevices(document);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Exception with element parse:{}", e);
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
