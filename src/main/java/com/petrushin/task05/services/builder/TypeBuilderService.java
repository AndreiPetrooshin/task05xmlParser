package com.petrushin.task05.services.builder;


import com.petrushin.task05.domain.Type;
import com.petrushin.task05.domain.enums.Group;

import java.util.Map;

public abstract class TypeBuilderService<T extends Type> {


    public T buildConsumption(T t,String str) {
        int consumption = Integer.parseInt(str);
        t.setConsumption(consumption);
        return t;
    }

    public T buildGroup(T t,String str) {
        String multimediaValue = Group.MULTIMEDIA.getValue();
        if (str.equalsIgnoreCase(multimediaValue)) {
            t.setGroup(Group.MULTIMEDIA);
        } else {
            String ioValue = Group.IO_DEVICE.getValue();
            if (str.equalsIgnoreCase(ioValue)) {
                t.setGroup(Group.IO_DEVICE);
            }
        }
        return t;
    }

    public abstract T buildType(Map<String, String> attributes);


    public abstract T buildSocket(T t,String str);

    public abstract T buildFrequency(T t,String str);

    public abstract T buildCores(T t,String str);

    public abstract T buildMemorySize(T t,String str);

    public abstract T buildSensitivity(T t,String str);

    public abstract T buildPort(T t,String str);



}
