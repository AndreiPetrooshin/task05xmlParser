package com.petrushin.task05.domain;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlType(propOrder = {
        "name",
        "origin",
        "price",
        "type",
        "critical"
})
public class Device {


    private String name;
    private String origin;
    private int price;
    private Type type;
    private boolean critical;
    private String id;

    public Device() {
    }

    public Device(String id, String name, String origin, int price, Type type, boolean critical) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.type = type;
        this.critical = critical;
        this.id = id;
    }

    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @XmlElements({
            @XmlElement(name = "processor", type = Processor.class),
            @XmlElement(name = "videoCard", type = VideoCard.class),
            @XmlElement(name = "headPhones", type = HeadPhones.class)
    })
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", critical=" + critical +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Device device = (Device) o;

        if (price != device.price) {
            return false;
        }
        if (critical != device.critical) {
            return false;
        }
        if (name != null ? !name.equals(device.name) : device.name != null) {
            return false;
        }
        if (origin != null ? !origin.equals(device.origin) : device.origin != null) {
            return false;
        }
        if (type != null ? !type.equals(device.type) : device.type != null) {
            return false;
        }
        return id != null ? id.equals(device.id) : device.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (critical ? 1 : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
