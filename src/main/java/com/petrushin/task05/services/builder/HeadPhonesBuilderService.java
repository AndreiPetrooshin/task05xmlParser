package com.petrushin.task05.services.builder;

import com.petrushin.task05.domain.HeadPhones;
import com.petrushin.task05.domain.enums.Port;

import java.util.Map;

import static com.petrushin.task05.util.DeviceConst.PERIPHERAL;

/**
 * This class helps build and initiate {@link HeadPhones} instance
 * which extends {@link TypeBuilderService}
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class HeadPhonesBuilderService extends TypeBuilderService<HeadPhones> {

    @Override
    public HeadPhones buildType(Map<String, String> attributes) {
        String peripheral = attributes.get(PERIPHERAL);
        boolean isPeripheral = Boolean.parseBoolean(peripheral);
        HeadPhones headPhones = new HeadPhones();
        headPhones.setPeripheral(isPeripheral);
        return headPhones;
    }

    @Override
    public HeadPhones buildSensitivity(HeadPhones headPhones, String str) {
        headPhones.setSensitivity(str);
        return headPhones;
    }

    @Override
    public HeadPhones buildPort(HeadPhones headPhones, String str) {
        Port port = Port.valueOf(str);
        headPhones.addPort(port);
        return headPhones;
    }

    @Override
    public HeadPhones buildSocket(HeadPhones headPhones, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HeadPhones buildFrequency(HeadPhones headPhones, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HeadPhones buildCores(HeadPhones headPhones, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HeadPhones buildMemorySize(HeadPhones headPhones, String str) {
        throw new UnsupportedOperationException();
    }

}
