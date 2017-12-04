package com.petrushin.task05.domain;

import com.petrushin.task05.domain.enums.Group;
import com.petrushin.task05.domain.enums.Port;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "headPhones", propOrder = {
        "ports",
        "sensitivity"
})
public class HeadPhones extends Type {

    private List<Port> ports;
    private String sensitivity;

    public HeadPhones() {
        ports = new ArrayList<>();
    }

    public HeadPhones(boolean peripheral, int consumption,
                      Group group, List<Port> ports,
                      String sensitivity) {
        super(peripheral, consumption, group);
        this.ports = ports;
        this.sensitivity = sensitivity;
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

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
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

        HeadPhones that = (HeadPhones) o;

        if (ports != null ? !ports.equals(that.ports) : that.ports != null) {
            return false;
        }
        return sensitivity != null ? sensitivity.equals(that.sensitivity) : that.sensitivity == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ports != null ? ports.hashCode() : 0);
        result = 31 * result + (sensitivity != null ? sensitivity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HeadPhones{" + super.toString() +
                "ports=" + ports +
                ", sensitivity='" + sensitivity + '\'' +
                '}';
    }
}
