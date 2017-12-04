package com.petrushin.task05.domain.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Group {


    @XmlEnumValue(value = "multimedia")
    MULTIMEDIA("multimedia"),

    @XmlEnumValue(value = "I/O devices")
    IO_DEVICE("I/O devices");

    private String value;

    Group(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
