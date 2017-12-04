package com.petrushin.task05.builder;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.*;

public class DeviceDOMBuilder {

    private static final int FIRST_ELEMENT = 0;

    private List<Device> devices;
    private TypeBuilder typeBuilder;
    private DeviceBuilder deviceBuilder;

    public DeviceDOMBuilder() {
        this.devices = new ArrayList<>();
        this.typeBuilder = new TypeBuilder();
        this.deviceBuilder = new DeviceBuilder(typeBuilder);
    }


    public List<Device> buildDevices(Document document) {
        Element root = document.getDocumentElement();
        NodeList deviceList = root.getElementsByTagName(DEVICE);
        for (int i = 0; i < deviceList.getLength(); i++) {
            Element deviceElement = (Element) deviceList.item(i);
            Device device = buildDevice(deviceElement);
            devices.add(device);
        }
        return devices;
    }


    private Device buildDevice(Element element) {
        typeBuilder = new TypeBuilder();
        deviceBuilder = new DeviceBuilder(typeBuilder);

        Map<String, String> attributeMap = new HashMap<>();
        String id = element.getAttribute(ID);
        attributeMap.put(ID, id);
        Device device = deviceBuilder.buildDevice(attributeMap);
        initDeviceBuilder(element, deviceBuilder);

        Type type = buildType(element);

        typeBuilder.buildConsumption(element.getElementsByTagName(CONSUMPTION).item(0).getTextContent());
        typeBuilder.buildGroup(element.getElementsByTagName(GROUP).item(0).getTextContent());
        device.setType(type);
        return device;
    }

    private Type buildType(Element element) {
        NodeList typeList;
        typeList = element.getElementsByTagName(PROCESSOR);
        int processorsLength = typeList.getLength();
        if (processorsLength == 1) {
            Element processorElement = (Element) typeList.item(FIRST_ELEMENT);
            return buildProcessor(processorElement);
        }

        typeList = element.getElementsByTagName(HEAD_PHONES);
        int headPhonesLength = typeList.getLength();
        if (headPhonesLength == 1) {
            Element headPhonesElement = (Element) typeList.item(FIRST_ELEMENT);
            return buildHeadPhones(headPhonesElement);
        }

        typeList = element.getElementsByTagName(VIDEO_CARD);
        int videoCardLength = typeList.getLength();
        if (videoCardLength == 1) {
            Element videoCardElement = (Element) typeList.item(FIRST_ELEMENT);
            return buildVideoCard(videoCardElement);
        }
        return null;
    }

    private Type buildVideoCard(Element element) {
        Map<String, String> attributeMap = new HashMap<>();

        if (element.hasAttribute(PERIPHERAL)) {
            String peripheral = element.getAttribute(PERIPHERAL);
            attributeMap.put(PERIPHERAL, peripheral);
        }
        String coolerValue = element.getAttribute(COOLER);
        attributeMap.put(COOLER, coolerValue);

        Type type = typeBuilder.buildType(VIDEO_CARD, attributeMap);

        String consumption = getElementTextContent(element, CONSUMPTION);
        typeBuilder.buildConsumption(consumption);

        String group = getElementTextContent(element, GROUP);
        typeBuilder.buildGroup(group);

        String frequency = getElementTextContent(element, FREQUENCY);
        typeBuilder.buildFrequency(frequency);

        String memorySize = getElementTextContent(element, MEMORY_SIZE);
        typeBuilder.buildMemorySize(memorySize);

        NodeList ports = element.getElementsByTagName(PORTS);
        for (int i = 0; i < ports.getLength(); i++) {
            Element portElement = (Element) ports.item(i);
            typeBuilder.buildPort(portElement.getTextContent());
        }
        return type;
    }

    private Type buildHeadPhones(Element element) {
        Map<String, String> attributeMap = new HashMap<>();
        String peripheral = element.getAttribute(PERIPHERAL);
        attributeMap.put(PERIPHERAL, peripheral);

        Type type = typeBuilder.buildType(HEAD_PHONES, attributeMap);

        String consumption = getElementTextContent(element, CONSUMPTION);
        typeBuilder.buildConsumption(consumption);

        String group = getElementTextContent(element, GROUP);
        typeBuilder.buildGroup(group);

        String sensitivity = getElementTextContent(element, SENSITIVITY);
        typeBuilder.buildSensitivity(sensitivity);

        NodeList ports = element.getElementsByTagName(PORTS);
        for (int i = 0; i < ports.getLength(); i++) {
            Element portElement = (Element) ports.item(i);
            String port = portElement.getTextContent();
            typeBuilder.buildPort(port);
        }
        return type;
    }

    private Type buildProcessor(Element element) {
        Type type;
        Map<String, String> attributeMap = new HashMap<>();

        if (element.hasAttribute(PERIPHERAL)) {
            String peripheral = element.getAttribute(PERIPHERAL);
            attributeMap.put(PERIPHERAL, peripheral);
        }

        String coolerValue = element.getAttribute(COOLER);
        attributeMap.put(COOLER, coolerValue);

        type = typeBuilder.buildType(PROCESSOR, attributeMap);

        String consumption = getElementTextContent(element, CONSUMPTION);
        typeBuilder.buildConsumption(consumption);

        String group = getElementTextContent(element, GROUP);
        typeBuilder.buildGroup(group);

        String cores = getElementTextContent(element, CORES);
        typeBuilder.buildCores(cores);

        String frequency = getElementTextContent(element, FREQUENCY);
        typeBuilder.buildFrequency(frequency);

        String socket = getElementTextContent(element, SOCKET);
        typeBuilder.buildSocket(socket);

        return type;
    }

    private void initDeviceBuilder(Element element, DeviceBuilder deviceBuilder) {
        String name = getElementTextContent(element, NAME);
        deviceBuilder.buildName(name);

        String price = getElementTextContent(element, PRICE);
        deviceBuilder.buildPrice(price);

        String origin = getElementTextContent(element, ORIGIN);
        deviceBuilder.buildOrigin(origin);

        String critical = getElementTextContent(element, CRITICAL);
        deviceBuilder.buildCritical(critical);
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(FIRST_ELEMENT);
        return node.getTextContent();
    }

}
