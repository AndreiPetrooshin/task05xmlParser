package com.petrushin.task05.domain;

import com.petrushin.task05.domain.enums.Group;
import com.petrushin.task05.domain.enums.Socket;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "processor", propOrder = {
        "sockets",
        "frequency",
        "cores"
})
public class Processor extends Type {

    private Socket sockets;
    private String frequency;
    private int cores;
    private boolean cooler;

    public Processor() {
    }

    public Processor(boolean peripheral, boolean cooler, int consumption,
                     Group group, Socket sockets,
                     String frequency, int cores) {
        super(peripheral, consumption, group);
        this.cooler = cooler;
        this.sockets = sockets;
        this.frequency = frequency;
        this.cores = cores;
    }

    @XmlElement(name = "socket")
    public Socket getSockets() {
        return sockets;
    }

    public void setSockets(Socket sockets) {
        this.sockets = sockets;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
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

        Processor processor = (Processor) o;

        if (cores != processor.cores) {
            return false;
        }
        if (sockets != processor.sockets) {
            return false;
        }
        if (cooler != processor.cooler) {
            return false;
        }
        return frequency != null ? frequency.equals(processor.frequency) : processor.frequency == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sockets != null ? sockets.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        result = 31 * result + cores;
        return result;
    }

    @Override
    public String toString() {
        return "Processor{" + super.toString() +
                "sockets=" + sockets +
                ", frequency='" + frequency + '\'' +
                ", cores=" + cores +
                ", cooler=" + cooler +
                '}';
    }
}
