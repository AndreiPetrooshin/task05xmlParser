package com.petrushin.task05.domain.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Socket {
    @XmlEnumValue("AM3")
    AM3,
    @XmlEnumValue("AM4")
    AM4,

    @XmlEnumValue("LGA1151")
    LGA1151,

    @XmlEnumValue("LGA1156")
    LGA1156,

    @XmlEnumValue("LGA1150")
    LGA1150,

}
