package com.petrushin.task05.domain;

import com.petrushin.task05.domain.enums.Group;
import com.petrushin.task05.domain.enums.Port;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "videoCard", propOrder = {
        "ports",
        "frequency",
        "memorySize"
})
public class VideoCard extends Type {

    private List<Port> ports;
    private String frequency;
    private String memorySize;
    private boolean cooler;

    public VideoCard() {
        ports = new ArrayList<>();
    }

    public VideoCard(boolean peripheral, boolean cooler, int consumption,
                     Group group, List<Port> ports,
                     String frequency, String memorySize) {
        super(peripheral, consumption, group);
        this.cooler = cooler;
        this.ports = ports;
        this.frequency = frequency;
        this.memorySize = memorySize;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public void addPort(Port port) {
        ports.add(port);
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    @XmlAttribute
    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        VideoCard videoCard = (VideoCard) o;

        if (ports != null ? !ports.equals(videoCard.ports) : videoCard.ports != null) {
            return false;
        }
        if (frequency != null ? !frequency.equals(videoCard.frequency) : videoCard.frequency != null) {
            return false;
        }
        if (cooler != videoCard.cooler) {
            return false;
        }
        return memorySize != null ? memorySize.equals(videoCard.memorySize) : videoCard.memorySize == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ports != null ? ports.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        result = 31 * result + (memorySize != null ? memorySize.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoCard{" + super.toString() +
                "ports=" + ports +
                ", cooler='" + cooler + '\'' +
                ", frequency='" + frequency + '\'' +
                ", memorySize='" + memorySize + '\'' +
                '}';
    }
}
