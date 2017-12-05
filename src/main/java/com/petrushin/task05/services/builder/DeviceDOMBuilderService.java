package com.petrushin.task05.services.builder;

import com.petrushin.task05.domain.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.petrushin.task05.util.DeviceConst.*;

/**
 * This class helps build Device objects by using
 * {@link DeviceBuilderService} and {@link TypeBuilderService} services.
 *
 * @author Andrei Petrushin;
 * @version 1.0.0
 */
public class DeviceDOMBuilderService {

    private static final int FIRST_ELEMENT = 0;

    private List<Device> devices;

    public DeviceDOMBuilderService() {
        this.devices = new ArrayList<>();

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
        DeviceBuilderService deviceBuilder = new DeviceBuilderService();
        Map<String, String> attributeMap = new HashMap<>();
        String id = element.getAttribute(ID);
        attributeMap.put(ID, id);
        Device device = deviceBuilder.buildDevice(attributeMap);

        String name = getElementTextContent(element, NAME);
        deviceBuilder.buildName(device, name);

        String price = getElementTextContent(element, PRICE);
        deviceBuilder.buildPrice(device, price);

        String origin = getElementTextContent(element, ORIGIN);
        deviceBuilder.buildOrigin(device, origin);

        String critical = getElementTextContent(element, CRITICAL);
        deviceBuilder.buildCritical(device, critical);

        Type type = buildType(element);
        deviceBuilder.buildType(device, type);

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

        TypeBuilderService<VideoCard> videoCardBuilder = new VideoCardBuilderService();
        VideoCard videoCard = videoCardBuilder.buildType(attributeMap);

        String consumption = getElementTextContent(element, CONSUMPTION);
        videoCardBuilder.buildConsumption(videoCard, consumption);

        String group = getElementTextContent(element, GROUP);
        videoCardBuilder.buildGroup(videoCard, group);

        String frequency = getElementTextContent(element, FREQUENCY);
        videoCardBuilder.buildFrequency(videoCard, frequency);

        String memorySize = getElementTextContent(element, MEMORY_SIZE);
        videoCardBuilder.buildMemorySize(videoCard, memorySize);

        NodeList ports = element.getElementsByTagName(PORTS);
        for (int i = 0; i < ports.getLength(); i++) {
            Element portElement = (Element) ports.item(i);
            videoCardBuilder.buildPort(videoCard, portElement.getTextContent());
        }
        return videoCard;
    }

    private Type buildHeadPhones(Element element) {
        String peripheral = element.getAttribute(PERIPHERAL);

        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put(PERIPHERAL, peripheral);

        TypeBuilderService<HeadPhones> headPhonesBuilder = new HeadPhonesBuilderService();
        HeadPhones headPhones = headPhonesBuilder.buildType(attributeMap);

        String consumption = getElementTextContent(element, CONSUMPTION);
        headPhonesBuilder.buildConsumption(headPhones, consumption);

        String group = getElementTextContent(element, GROUP);
        headPhonesBuilder.buildGroup(headPhones, group);

        String sensitivity = getElementTextContent(element, SENSITIVITY);
        headPhonesBuilder.buildSensitivity(headPhones, sensitivity);

        NodeList ports = element.getElementsByTagName(PORTS);
        for (int i = 0; i < ports.getLength(); i++) {
            Element portElement = (Element) ports.item(i);
            String port = portElement.getTextContent();
            headPhonesBuilder.buildPort(headPhones, port);
        }
        return headPhones;
    }

    private Type buildProcessor(Element element) {
        Map<String, String> attributeMap = new HashMap<>();

        if (element.hasAttribute(PERIPHERAL)) {
            String peripheral = element.getAttribute(PERIPHERAL);
            attributeMap.put(PERIPHERAL, peripheral);
        }

        String coolerValue = element.getAttribute(COOLER);
        attributeMap.put(COOLER, coolerValue);

        TypeBuilderService<Processor> processorBuilder = new ProcessorBuilderService();
        Processor processor = processorBuilder.buildType(attributeMap);

        String consumption = getElementTextContent(element, CONSUMPTION);
        processorBuilder.buildConsumption(processor, consumption);

        String group = getElementTextContent(element, GROUP);
        processorBuilder.buildGroup(processor, group);

        String cores = getElementTextContent(element, CORES);
        processorBuilder.buildCores(processor, cores);

        String frequency = getElementTextContent(element, FREQUENCY);
        processorBuilder.buildFrequency(processor, frequency);

        String socket = getElementTextContent(element, SOCKET);
        processorBuilder.buildSocket(processor, socket);

        return processor;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(FIRST_ELEMENT);
        return node.getTextContent();
    }

}
