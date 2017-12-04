package com.petrushin.task05.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.ValidationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XsdSchemaValidator {

    private static final Logger LOGGER = LogManager.getLogger(XsdSchemaValidator.class);

    public boolean validate(String xmlLocation, String xsdLocation) throws ValidationException {
        try {
            File xmlFile = new File(xmlLocation);
            File xsdFile = new File(xsdLocation);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException | IOException e) {
            LOGGER.error("Validation exception!");
            throw new ValidationException(e);
        }
        return true;

    }
}
