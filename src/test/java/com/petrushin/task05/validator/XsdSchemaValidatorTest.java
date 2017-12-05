package com.petrushin.task05.validator;

import org.junit.Test;

import javax.xml.bind.ValidationException;

public class XsdSchemaValidatorTest {

    private static final XsdSchemaValidator schemaValidator = new XsdSchemaValidator();
    private static final String XML_LOCATION = "test-data.xml";
    private static final String XSD_LOCATION = "devicesSchema.xsd";
    private static final String WRONG_SCHEMA_LOCATION = "wrongSchemaTest.xsd";
    private static final String WRONG_XML_LOCATION = "wrongDevicesTest.xml";

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenXMLIsWrong() throws ValidationException {
            schemaValidator.validate(XSD_LOCATION, WRONG_XML_LOCATION);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenSchemaIsWrong() throws ValidationException {
        schemaValidator.validate(XML_LOCATION, WRONG_SCHEMA_LOCATION);
    }

}
