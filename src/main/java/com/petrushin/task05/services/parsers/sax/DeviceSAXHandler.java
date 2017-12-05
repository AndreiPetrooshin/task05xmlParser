package com.petrushin.task05.services.parsers.sax;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Type;
import com.petrushin.task05.domain.enums.DeviceEnum;
import com.petrushin.task05.services.builder.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static com.petrushin.task05.util.DeviceConst.*;

/**
 * DevicesSAXHandler class extends {@link DefaultHandler}
 * for handling our xml document.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */

public class DeviceSAXHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(DeviceSAXHandler.class);

    private List<Device> devices;
    private Device currentDevice;
    private DeviceEnum currentEnum;
    private Type currentType;
    private DeviceBuilderService deviceBuilder;
    private TypeBuilderService typeBuilder;
    private EnumSet<DeviceEnum> withText;
    private EnumSet<DeviceEnum> complexElements;


    DeviceSAXHandler(DeviceBuilderService deviceBuilder) {

        this.deviceBuilder = deviceBuilder;

    }


    @Override
    public void startDocument() throws SAXException {
        devices = new ArrayList<>();
        withText = EnumSet.range(DeviceEnum.NAME, DeviceEnum.SENSITIVITY);
        complexElements = EnumSet.range(DeviceEnum.DEVICE, DeviceEnum.PROCESSOR);
        LOGGER.info("SAX Parse starts");
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        Map<String, String> attributeMap;
        if (DEVICE.equalsIgnoreCase(qName)) {
            attributeMap = convertAttributesToMap(attributes, qName);
            currentDevice = deviceBuilder.buildDevice(attributeMap);
        } else {
            String name = qName.toUpperCase();
            DeviceEnum temp = DeviceEnum.valueOf(name);
            if (withText.contains(temp)) {
                currentEnum = temp;
            } else if (complexElements.contains(temp)) {
                attributeMap = convertAttributesToMap(attributes, qName);
                initTypeBuilder(qName, attributeMap);
                currentDevice.setType(currentType);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    deviceBuilder.buildName(currentDevice, str);
                    break;
                case ORIGIN:
                    deviceBuilder.buildOrigin(currentDevice, str);
                    break;
                case PRICE:
                    deviceBuilder.buildPrice(currentDevice, str);
                    break;
                case CONSUMPTION:
                    typeBuilder.buildConsumption(currentType, str);
                    break;
                case GROUP:
                    typeBuilder.buildGroup(currentType, str);
                    break;
                case SOCKET:
                    typeBuilder.buildSocket(currentType, str);
                    break;
                case FREQUENCY:
                    typeBuilder.buildFrequency(currentType, str);
                    break;
                case CORES:
                    typeBuilder.buildCores(currentType, str);
                    break;
                case MEMORYSIZE:
                    typeBuilder.buildMemorySize(currentType, str);
                    break;
                case CRITICAL:
                    deviceBuilder.buildCritical(currentDevice, str);
                    break;
                case SENSITIVITY:
                    typeBuilder.buildSensitivity(currentType, str);
                    break;
                case PORTS:
                    typeBuilder.buildPort(currentType, str);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(),
                            currentEnum.name());
            }
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (DEVICE.equalsIgnoreCase(qName)) {
            devices.add(currentDevice);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.info("SAX Parse ends");
    }

    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Method creates needed TypeBuilder by {@link Map} attributeMap
     * and {@link String} type  and assigns it to typeBuilder field.
    * */
    private void initTypeBuilder(String type, Map<String, String> attributeMap) {
        switch (type) {
            case PROCESSOR:
                typeBuilder = new ProcessorBuilderService();
                break;
            case HEAD_PHONES:
                typeBuilder = new HeadPhonesBuilderService();
                break;
            case VIDEO_CARD:
                typeBuilder = new VideoCardBuilderService();
                break;
            default:
                break;
        }
        currentType = typeBuilder.buildType(attributeMap);
    }

    /**
     * Method for converting {@link Attributes} attributes instance
     * into the Map representation by Device Type.
     */
    private Map<String, String> convertAttributesToMap(Attributes attributes, String type) {
        Map<String, String> attributeMap = new HashMap<>();
        String peripheralValue = attributes.getValue(PERIPHERAL);
        attributeMap.put(PERIPHERAL, peripheralValue);
        if (DEVICE.equalsIgnoreCase(type)) {
            String idValue = attributes.getValue(ID);
            attributeMap.put(ID, idValue);
            return attributeMap;
        }
        if (PROCESSOR.equalsIgnoreCase(type)
                || VIDEO_CARD.equalsIgnoreCase(type)) {
            if (attributes.getLength() > 1) {
                String coolerValue = attributes.getValue(COOLER);
                attributeMap.put(COOLER, coolerValue);
            }
        }
        return attributeMap;
    }
}
