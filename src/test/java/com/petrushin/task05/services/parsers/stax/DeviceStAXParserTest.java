package com.petrushin.task05.services.parsers.stax;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.services.TestData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeviceStAXParserTest {

    private static final DeviceStAXParser PARSER = new DeviceStAXParser();
    private static final String XML_LOCATION = "test-data.xml";
    private static final String SCHEMA_LOCATION = "devicesSchema.xsd";

    @Test
    public void shouldParseXMLFileAndReturnDeviceListWhenXmlLocation() {
        List<Device> result = PARSER.parse(XML_LOCATION);
        Assert.assertArrayEquals(TestData.EXPECTED.toArray(), result.toArray());
    }

    @Test
    public void shouldParserXMLFileAndReturnDeviceListWhenXmlLocationAndSchemaLocation() {
        List<Device> result = PARSER.parse(XML_LOCATION, SCHEMA_LOCATION);
        Assert.assertArrayEquals(TestData.EXPECTED.toArray(), result.toArray());
    }

}
