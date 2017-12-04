package com.petrushin.task05.services.parsers.sax;

import com.petrushin.task05.builder.DeviceBuilder;
import com.petrushin.task05.builder.TypeBuilder;
import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Type;
import com.petrushin.task05.domain.enums.DeviceEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static com.petrushin.task05.domain.DeviceConst.*;

public class DeviceHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(DeviceHandler.class);

    private final DeviceBuilder deviceBuilder;

    private List<Device> devices;
    private Device currentDevice;
    private DeviceEnum currentEnum;
    private EnumSet<DeviceEnum> withText;
    private EnumSet<DeviceEnum> complexElements;


    public DeviceHandler(DeviceBuilder deviceBuilder) {
        devices = new ArrayList<>();
        withText = EnumSet.range(DeviceEnum.NAME, DeviceEnum.SENSITIVITY);
        complexElements = EnumSet.range(DeviceEnum.DEVICE, DeviceEnum.PROCESSOR);
        this.deviceBuilder = deviceBuilder;

    }

    @Override
    public void startDocument() throws SAXException {
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
                TypeBuilder typeBuilder = deviceBuilder.getTypeBuilder();
                attributeMap = convertAttributesToMap(attributes, qName);
                Type type = typeBuilder.buildType(qName, attributeMap);
                currentDevice.setType(type);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        TypeBuilder typeBuilder = deviceBuilder.getTypeBuilder();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    deviceBuilder.buildName(str);
                    break;
                case ORIGIN:
                    deviceBuilder.buildOrigin(str);
                    break;
                case PRICE:
                    deviceBuilder.buildPrice(str);
                    break;
                case CONSUMPTION:
                    typeBuilder.buildConsumption(str);
                    break;
                case GROUP:
                    typeBuilder.buildGroup(str);
                    break;
                case SOCKET:
                    typeBuilder.buildSocket(str);
                    break;
                case FREQUENCY:
                    typeBuilder.buildFrequency(str);
                    break;
                case CORES:
                    typeBuilder.buildCores(str);
                    break;
                case MEMORYSIZE:
                    typeBuilder.buildMemorySize(str);
                    break;
                case CRITICAL:
                    deviceBuilder.buildCritical(str);
                    break;
                case SENSITIVITY:
                    typeBuilder.buildSensitivity(str);
                    break;
                case PORTS:
                    typeBuilder.buildPort(str);
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
