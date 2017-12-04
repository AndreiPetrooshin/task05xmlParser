package com.petrushin.task05.domain.enums;

public enum DeviceEnum {

    DEVICES("deivces"),
    DEVICE("device"),
    HEADPHONES("headPhones"),
    VIDEOCARD("videoCard"),
    PROCESSOR("processor"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    CONSUMPTION("consumption"),
    GROUP("group"),
    SOCKET("socket"),
    FREQUENCY("frequency"),
    CORES("cores"),
    PORTS("ports"),
    CRITICAL("critical"),
    MEMORYSIZE("memorySize"),
    SENSITIVITY("sensitivity");

    private String value;

    DeviceEnum(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
