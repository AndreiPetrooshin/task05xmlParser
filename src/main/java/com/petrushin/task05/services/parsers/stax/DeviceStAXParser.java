package com.petrushin.task05.services.parsers.stax;

import com.petrushin.task05.builder.DeviceBuilder;
import com.petrushin.task05.builder.TypeBuilder;
import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Type;
import com.petrushin.task05.services.parsers.Parser;
import com.petrushin.task05.services.reader.XMLStreamReaderStAX;
import com.petrushin.task05.validator.XsdSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationException;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.*;

public class DeviceStAXParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(DeviceStAXParser.class);

    @Override
    public List<Device> parse(String xmlLocation) {
        XMLStreamReaderStAX xmlStreamReaderStAX = new XMLStreamReaderStAX();
        XMLStreamReader xmlReader = xmlStreamReaderStAX.getXmlStreamReader(xmlLocation);
        List<Device> devices = new ArrayList<>();
        try {
            fillDeviceList(xmlReader, devices);
        } catch (XMLStreamException e) {
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

    private void fillDeviceList(XMLStreamReader xmlReader, List<Device> devices) throws XMLStreamException {
        String name;
        while (xmlReader.hasNext()) {
            int type = xmlReader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = xmlReader.getLocalName();
                if (name.equalsIgnoreCase(DEVICE)) {
                    Device device = fillDevice(xmlReader);
                    devices.add(device);
                }
            }

        }
    }

    private Device fillDevice(XMLStreamReader xmlReader) {
        TypeBuilder typeBuilder = new TypeBuilder();
        DeviceBuilder deviceBuilder = new DeviceBuilder(typeBuilder);
        Map<String, String> attributeMap = getAttributeMap(xmlReader, DEVICE);
        Device device = deviceBuilder.buildDevice(attributeMap);

        String localName;
        try {
            while (xmlReader.hasNext()) {
                int type = xmlReader.next();
                if (XMLStreamConstants.START_ELEMENT == type) {
                    localName = xmlReader.getLocalName();
                    String xmlText;
                    Type deviceType;
                    switch (localName) {
                        case NAME:
                            xmlText = getXMLText(xmlReader);
                            deviceBuilder.buildName(xmlText);
                            break;
                        case ORIGIN:
                            xmlText = getXMLText(xmlReader);
                            deviceBuilder.buildOrigin(xmlText);
                            break;
                        case PRICE:
                            xmlText = getXMLText(xmlReader);
                            deviceBuilder.buildPrice(xmlText);
                            break;
                        case CRITICAL:
                            xmlText = getXMLText(xmlReader);
                            deviceBuilder.buildCritical(xmlText);
                            break;
                        case HEAD_PHONES:
                            attributeMap = getAttributeMap(xmlReader, HEAD_PHONES);
                            deviceType = typeBuilder.buildType(HEAD_PHONES, attributeMap);
                            fillHedPhones(xmlReader, typeBuilder);
                            device.setType(deviceType);
                            break;
                        case PROCESSOR:
                            attributeMap = getAttributeMap(xmlReader, PROCESSOR);
                            deviceType = typeBuilder.buildType(PROCESSOR, attributeMap);
                            fillProcessor(xmlReader, typeBuilder);
                            device.setType(deviceType);
                            break;
                        case VIDEO_CARD:
                            attributeMap = getAttributeMap(xmlReader, VIDEO_CARD);
                            deviceType = typeBuilder.buildType(VIDEO_CARD, attributeMap);
                            fillVideoCard(xmlReader, typeBuilder);
                            device.setType(deviceType);
                            break;
                        default:
                            break;
                    }
                } else if (XMLStreamConstants.END_ELEMENT == type) {
                    if (xmlReader.getLocalName().equalsIgnoreCase(DEVICE)) {
                        return device;
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return device;
    }

    private void fillHedPhones(XMLStreamReader xmlReader, TypeBuilder typeBuilder) {
        try {
            while (xmlReader.hasNext()) {
                int type = xmlReader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String localName;
                        localName = xmlReader.getLocalName();
                        String xmlText;
                        switch (localName) {
                            case CONSUMPTION:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildConsumption(xmlText);
                                break;
                            case GROUP:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildGroup(xmlText);
                                break;
                            case PORTS:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildPort(xmlText);
                                break;
                            case SENSITIVITY:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildSensitivity(xmlText);
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        if (xmlReader.getLocalName().equalsIgnoreCase(HEAD_PHONES)) {
                            return;
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void fillProcessor(XMLStreamReader xmlReader, TypeBuilder typeBuilder) {
        try {
            while (xmlReader.hasNext()) {
                int type = xmlReader.next();
                String localName;
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        localName = xmlReader.getLocalName();
                        String xmlText;
                        switch (localName) {
                            case CONSUMPTION:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildConsumption(xmlText);
                                break;
                            case GROUP:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildGroup(xmlText);
                                break;
                            case SOCKET:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildSocket(xmlText);
                                break;
                            case FREQUENCY:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildFrequency(xmlText);
                                break;
                            case CORES:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildCores(xmlText);
                                break;
                            default:
                                break;

                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        if (xmlReader.getLocalName().equalsIgnoreCase(PROCESSOR)) {
                            return;
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void fillVideoCard(XMLStreamReader xmlReader, TypeBuilder typeBuilder) {
        try {
            while (xmlReader.hasNext()) {
                int type = xmlReader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String localName;
                        localName = xmlReader.getLocalName();
                        String xmlText;
                        switch (localName) {
                            case CONSUMPTION:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildConsumption(xmlText);
                                break;
                            case GROUP:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildGroup(xmlText);
                                break;
                            case PORTS:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildPort(xmlText);
                                break;
                            case FREQUENCY:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildFrequency(xmlText);
                                break;
                            case MEMORY_SIZE:
                                xmlText = getXMLText(xmlReader);
                                typeBuilder.buildMemorySize(xmlText);
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        if (xmlReader.getLocalName().equalsIgnoreCase(VIDEO_CARD)) {
                            return;
                        }
                    }
                    default:
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getAttributeMap(XMLStreamReader reader, String deviceType) {
        Map<String, String> attributeMap = new HashMap<>();

        if (DEVICE.equalsIgnoreCase(deviceType)) {
            String idValue = reader.getAttributeValue(null, ID);
            attributeMap.put(ID, idValue);
            return attributeMap;
        }

        String peripheralValue = reader.getAttributeValue(null, PERIPHERAL);
        attributeMap.put(PERIPHERAL, peripheralValue);

        if (PROCESSOR.equalsIgnoreCase(deviceType)
                || VIDEO_CARD.equalsIgnoreCase(deviceType)) {
            if (reader.getAttributeCount() > 1) {
                String coolerValue = reader.getAttributeValue(null, COOLER);
                attributeMap.put(COOLER, coolerValue);
            }
        }
        return attributeMap;

    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }


}
