package com.petrushin.task05.builder;

import com.petrushin.task05.domain.Processor;
import com.petrushin.task05.domain.enums.Socket;

import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.COOLER;
import static com.petrushin.task05.domain.DeviceConst.PERIPHERAL;

public class ProcessorBuilder {

    private Processor processor;

    public Processor buildProcessor(Map<String, String> attributes) {
        Processor processor = new Processor();
        if (attributes.size() > 1) {
            String cooler = attributes.get(COOLER);
            boolean isCooler = Boolean.parseBoolean(cooler);
            processor.setCooler(isCooler);
        }
        String peripheral = attributes.get(PERIPHERAL);
        boolean isPeripheral = Boolean.parseBoolean(peripheral);
        processor.setPeripheral(isPeripheral);
        this.processor = processor;
        return processor;
    }

    public void buildSocket(String str) {
        processor.setSockets(Socket.valueOf(str));
    }

    public void buildFrequency(String str) {
        processor.setFrequency(str);
    }

    public void buildCores(String str) {
        int cores = Integer.parseInt(str);
        processor.setCores(cores);
    }


}
