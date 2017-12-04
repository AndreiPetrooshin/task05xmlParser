package com.petrushin.task05.builder;

import com.petrushin.task05.domain.HeadPhones;
import com.petrushin.task05.domain.enums.Port;

import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.PERIPHERAL;

public class HeadPhonesBuilder {

    private HeadPhones headPhones;

    public HeadPhones buildHeadPhones(Map<String, String> attributes) {
        String peripheral = attributes.get(PERIPHERAL);
        boolean isPeripheral = Boolean.parseBoolean(peripheral);
        HeadPhones headPhones = new HeadPhones();
        headPhones.setPeripheral(isPeripheral);
        this.headPhones = headPhones;
        return headPhones;
    }

    public void buildSensitivity(String str) {
        headPhones.setSensitivity(str);
    }

    public void buildPort(String str) {
        Port port = Port.valueOf(str);
        headPhones.addPort(port);
    }
}
