package com.petrushin.task05.domain.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Port {

    @XmlEnumValue(value = "USB")
    USB,

    @XmlEnumValue(value = "LPT")
    LPT,

    @XmlEnumValue(value = "HDMI")
    HDMI,

    @XmlEnumValue(value = "DVI")
    DVI,

    @XmlEnumValue(value = "AUX")
    AUX

}
