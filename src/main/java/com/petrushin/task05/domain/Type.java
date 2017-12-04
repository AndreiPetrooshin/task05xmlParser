package com.petrushin.task05.domain;

import com.petrushin.task05.domain.enums.Group;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {
        "consumption",
        "group"
})
public class Type {

    private boolean peripheral = true;
    private int consumption;
    private Group group;


    public Type() {
    }

    public Type(boolean peripheral, int consumption, Group group) {
        this.peripheral = peripheral;
        this.consumption = consumption;
        this.group = group;
    }


    @XmlAttribute(required = true)
    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }


    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Type type = (Type) o;

        if (peripheral != type.peripheral) {
            return false;
        }
        if (consumption != type.consumption) {
            return false;
        }
        return group == type.group;
    }

    @Override
    public int hashCode() {
        int result = (peripheral ? 1 : 0);
        result = 31 * result + consumption;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "peripheral=" + peripheral +
                ", consumption=" + consumption +
                ", group=" + group + ", ";
    }
}
