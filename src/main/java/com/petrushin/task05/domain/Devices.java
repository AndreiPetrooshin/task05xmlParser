package com.petrushin.task05.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {
        "device"
})
public class Devices {

    public Devices() {
    }

    @XmlElement
    private List<Device> device;

    public List<Device> getDevice() {
        if (device == null) {
            device = new ArrayList<>();
        }
        return device;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "device=" + device +
                '}';
    }
}
