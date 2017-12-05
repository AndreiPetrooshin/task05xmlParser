package com.petrushin.task05.services.builder;

import com.petrushin.task05.domain.Device;
import com.petrushin.task05.domain.Type;

import java.util.Map;

import static com.petrushin.task05.util.DeviceConst.ID;

/**
 * Class for build and initiate {@link Device} instance
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 * */
public class DeviceBuilderService {


    public Device buildDevice(Map<String, String> attributes) {
        Device device = new Device();
        String id = attributes.get(ID);
        device.setId(id);
        return device;
    }

    public Device buildType(Device device, Type type){
        device.setType(type);
        return device;
    }

    public Device buildName(Device device, String str) {
        device.setName(str);
        return device;
    }

    public Device buildOrigin(Device device, String str) {
        device.setOrigin(str);
        return device;
    }

    public Device buildPrice(Device device, String str) {
        int price = Integer.parseInt(str);
        device.setPrice(price);
        return device;
    }

    public Device buildCritical(Device device, String str) {
        boolean critical = Boolean.parseBoolean(str);
        device.setCritical(critical);
        return device;
    }

}
