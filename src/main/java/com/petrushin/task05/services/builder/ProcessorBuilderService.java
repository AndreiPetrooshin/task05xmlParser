package com.petrushin.task05.services.builder;

import com.petrushin.task05.domain.Processor;
import com.petrushin.task05.domain.enums.Socket;

import java.util.Map;

import static com.petrushin.task05.util.DeviceConst.COOLER;
import static com.petrushin.task05.util.DeviceConst.PERIPHERAL;

/**
 * This class helps build and initiate {@link Processor} instance
 * which extends {@link TypeBuilderService}
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ProcessorBuilderService extends TypeBuilderService<Processor> {

    @Override
    public Processor buildType(Map<String, String> attributes) {
        Processor processor = new Processor();
        if (attributes.size() > 1) {
            String cooler = attributes.get(COOLER);
            boolean isCooler = Boolean.parseBoolean(cooler);
            processor.setCooler(isCooler);
        }
        String peripheral = attributes.get(PERIPHERAL);
        boolean isPeripheral = Boolean.parseBoolean(peripheral);
        processor.setPeripheral(isPeripheral);
        return processor;
    }

    @Override
    public Processor buildSocket(Processor processor, String str) {
        Socket socketValue = Socket.valueOf(str);
        processor.setSockets(socketValue);
        return processor;
    }

    @Override
    public Processor buildFrequency(Processor processor, String str) {
        processor.setFrequency(str);
        return processor;
    }

    @Override
    public Processor buildCores(Processor processor, String str) {
        int cores = Integer.parseInt(str);
        processor.setCores(cores);
        return processor;
    }

    @Override
    public Processor buildMemorySize(Processor processor, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Processor buildSensitivity(Processor processor, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Processor buildPort(Processor processor, String str) {
        throw new UnsupportedOperationException();
    }
}
