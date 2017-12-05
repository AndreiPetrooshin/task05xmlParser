package com.petrushin.task05.services.parsers.stax;


import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Type;
import com.petrushin.task05.services.builder.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.petrushin.task05.util.DeviceConst.*;

/**
 * DevicesSAXHandler class handling our xml document.
 * and creates List of our Objects.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
class DeviceStAXHandler {

    private static QName idQname = new QName(ID);
    private static QName coolerQname = new QName(COOLER);
    private static QName peripheralQname = new QName(PERIPHERAL);

    private Device currentDevice;
    private Type currentType;
    private List<Device> devices;
    private DeviceBuilderService deviceBuilder;
    private TypeBuilderService typeBuilder;
    private String tagName;

    private boolean isInsideDevice;
    private boolean isInsideType;

    /**
     * This method starts parsing our file and
     * transfers management by EventType.
     */
    List<Device> startParsing(XMLEventReader eventReader) throws XMLStreamException {
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            int eventType = event.getEventType();
            switch (eventType) {
                case XMLStreamConstants.START_DOCUMENT:
                    init();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    processStartElement(startElement);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    processCharacters(characters);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    processEndElement(endElement);
                    break;
            }
        }
        return devices;
    }


    private void processStartElement(StartElement startElement) {
        QName qName = startElement.getName();
        tagName = qName.getLocalPart();
        switch (tagName) {
            case DEVICE:
                initDevice(startElement);
                break;
            case PROCESSOR:
                initProcessor(startElement);
                break;
            case VIDEO_CARD:
                initVideoCard(startElement);
                break;
            case HEAD_PHONES:
                initHeadPhones(startElement);
                break;
            default:
                break;
        }

    }


    private void processCharacters(Characters characters) {
        if (tagName == null) {
            return;
        }
        if (isInsideDevice) {
            fillDevice(characters);
        }
        if (isInsideType) {
            fillType(characters);
        }
        tagName = null;
    }

    private void processEndElement(EndElement endElement) {
        QName qName = endElement.getName();
        String tagName = qName.getLocalPart();
        switch (tagName) {
            case DEVICE:
                isInsideDevice = false;
                devices.add(currentDevice);
                break;
            case PROCESSOR:
                isInsideType = false;
                break;
            case VIDEO_CARD:
                isInsideType = false;
                break;
            case HEAD_PHONES:
                isInsideType = false;
                break;
            default:
                break;
        }
    }

    /**
     * Method initializes {@code currentType} by {@link com.petrushin.task05.domain.HeadPhones} and
     * {@code typeBuilder} by {@link HeadPhonesBuilderService} instances.
     */
    private void initHeadPhones(StartElement startElement) {
        Map<String, String> attributeMap;
        Attribute peripheralAttr;
        attributeMap = new HashMap<>();

        peripheralAttr = startElement.getAttributeByName(peripheralQname);
        if (peripheralAttr != null) {
            String peripheralValue = peripheralAttr.getValue();
            attributeMap.put(PERIPHERAL, peripheralValue);
        }

        typeBuilder = new HeadPhonesBuilderService();
        currentType = typeBuilder.buildType(attributeMap);
        deviceBuilder.buildType(currentDevice, currentType);
        isInsideType = true;
    }

    /**
     * Method initializes {@code currentType} by {@link com.petrushin.task05.domain.VideoCard} and
     * {@code typeBuilder} by {@link VideoCardBuilderService} instances.
     */
    private void initVideoCard(StartElement startElement) {
        Map<String, String> attributeMap;
        Attribute peripheralAttr;
        Attribute coolerAttr;
        attributeMap = new HashMap<>();

        peripheralAttr = startElement.getAttributeByName(peripheralQname);
        if (peripheralAttr != null) {
            String peripheralValue = peripheralAttr.getValue();
            attributeMap.put(PERIPHERAL, peripheralValue);
        }

        coolerAttr = startElement.getAttributeByName(coolerQname);
        if (coolerAttr != null) {
            String coolerValue = coolerAttr.getValue();
            attributeMap.put(COOLER, coolerValue);
        }

        typeBuilder = new VideoCardBuilderService();
        currentType = typeBuilder.buildType(attributeMap);
        deviceBuilder.buildType(currentDevice, currentType);
        isInsideType = true;
    }

    /**
     * Method initializes {@code currentType} by {@link com.petrushin.task05.domain.Processor} and
     * {@code typeBuilder} by {@link ProcessorBuilderService} instances.
     */
    private void initProcessor(StartElement startElement) {
        Map<String, String> attributeMap;
        Attribute peripheralAttr;
        Attribute coolerAttr;
        attributeMap = new HashMap<>();

        peripheralAttr = startElement.getAttributeByName(peripheralQname);
        if (peripheralAttr != null) {
            String peripheralValue = peripheralAttr.getValue();
            attributeMap.put(PERIPHERAL, peripheralValue);
        }
        coolerAttr = startElement.getAttributeByName(coolerQname);
        if (coolerAttr != null) {
            String coolerValue = coolerAttr.getValue();
            attributeMap.put(COOLER, coolerValue);
        }

        typeBuilder = new ProcessorBuilderService();
        currentType = typeBuilder.buildType(attributeMap);
        deviceBuilder.buildType(currentDevice, currentType);
        isInsideType = true;
    }

    /**
     * Method initializes {@code currentDevice} by {@link com.petrushin.task05.domain.Device} and
     * {@code deviceBuilder} by {@link DeviceBuilderService} instances.
     */
    private void initDevice(StartElement startElement) {
        Map<String, String> attributeMap;
        deviceBuilder = new DeviceBuilderService();
        attributeMap = new HashMap<>();
        Attribute attributeByName = startElement.getAttributeByName(idQname);
        String idValue = attributeByName.getValue();
        attributeMap.put(ID, idValue);
        currentDevice = deviceBuilder.buildDevice(attributeMap);
        isInsideDevice = true;
    }

    private void fillType(Characters characters) {
        String name = characters.getData();
        switch (tagName) {
            case CONSUMPTION:
                typeBuilder.buildConsumption(currentType, name);
                break;
            case GROUP:
                typeBuilder.buildGroup(currentType, name);
                break;
            case CORES:
                typeBuilder.buildCores(currentType, name);
                break;
            case FREQUENCY:
                typeBuilder.buildFrequency(currentType, name);
                break;
            case MEMORY_SIZE:
                typeBuilder.buildMemorySize(currentType, name);
                break;
            case PORTS:
                typeBuilder.buildPort(currentType, name);
                break;
            case SENSITIVITY:
                typeBuilder.buildSensitivity(currentType, name);
                break;
            case SOCKET:
                typeBuilder.buildSocket(currentType, name);
                break;
            default:
                break;
        }
    }

    private void fillDevice(Characters characters) {
        String name = characters.getData();
        switch (tagName) {
            case NAME:
                deviceBuilder.buildName(currentDevice, name);
                break;
            case ORIGIN:
                deviceBuilder.buildOrigin(currentDevice, name);
                break;
            case PRICE:
                deviceBuilder.buildPrice(currentDevice, name);
                break;
            case CRITICAL:
                deviceBuilder.buildCritical(currentDevice, name);
                break;
            default:
                break;
        }
    }

    private void init() {
        devices = new ArrayList<>();
        deviceBuilder = new DeviceBuilderService();
    }

}
