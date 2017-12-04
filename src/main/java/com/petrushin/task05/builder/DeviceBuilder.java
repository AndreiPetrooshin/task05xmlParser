package com.petrushin.task05.builder;

import com.petrushin.task05.domain.Device;

import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.ID;

public class DeviceBuilder {

    private Device device;
    private TypeBuilder typeBuilder;

    public DeviceBuilder(TypeBuilder typeBuilder) {
        this.typeBuilder = typeBuilder;
    }


    public Device buildDevice(Map<String, String> attributes) {
        device = new Device();
        String id = attributes.get(ID);
        device.setId(id);
        return device;
    }

    public void buildName(String str) {
        device.setName(str);
    }

    public void buildOrigin(String str) {
        device.setOrigin(str);
    }

    public void buildPrice(String str) {
        int price = Integer.parseInt(str);
        device.setPrice(price);
    }

    public void buildCritical(String str) {
        boolean critical = Boolean.parseBoolean(str);
        device.setCritical(critical);
    }

    public TypeBuilder getTypeBuilder() {
        return typeBuilder;
    }

    public void setTypeBuilder(TypeBuilder typeBuilder) {
        this.typeBuilder = typeBuilder;
    }
}
