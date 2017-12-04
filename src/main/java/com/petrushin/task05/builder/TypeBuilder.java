package com.petrushin.task05.builder;

import com.petrushin.task05.domain.HeadPhones;
import com.petrushin.task05.domain.Processor;
import com.petrushin.task05.domain.Type;
import com.petrushin.task05.domain.VideoCard;
import com.petrushin.task05.domain.enums.Group;

import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.*;

public abstract class TypeBuilder {

    private ProcessorBuilder processorBuilder;
    private HeadPhonesBuilder headPhonesBuilder;
    private VideoCardBuilder videoCardBuilder;
    private Type type;

    public TypeBuilder() {
        processorBuilder = new ProcessorBuilder();
        headPhonesBuilder = new HeadPhonesBuilder();
        videoCardBuilder = new VideoCardBuilder();
    }

    public void buildConsumption(String str) {
        int consumption = Integer.parseInt(str);
        type.setConsumption(consumption);
    }

    public void buildGroup(String str) {
        String multimediaValue = Group.MULTIMEDIA.getValue();
        if (str.equalsIgnoreCase(multimediaValue)) {
            type.setGroup(Group.MULTIMEDIA);
        } else {
            String ioValue = Group.IO_DEVICE.getValue();
            if (str.equalsIgnoreCase(ioValue)) {
                type.setGroup(Group.IO_DEVICE);
            }
        }
    }

    public void buildSocket(String str) {
        if (type instanceof Processor) {
            processorBuilder.buildSocket(str);
        }
    }

    public void buildFrequency(String str) {
        if (type instanceof Processor) {
            processorBuilder.buildFrequency(str);
        } else if (type instanceof VideoCard) {
            videoCardBuilder.buildFrequency(str);
        }
    }

    public void buildCores(String str) {
        if (type instanceof Processor) {
            processorBuilder.buildCores(str);
        }
    }

    public void buildMemorySize(String str) {
        if (type instanceof VideoCard) {
            videoCardBuilder.buildMemorySize(str);
        }
    }

    public void buildSensitivity(String str) {
        if (type instanceof HeadPhones) {
            headPhonesBuilder.buildSensitivity(str);
        }
    }

    public void buildPort(String str) {
        if (type instanceof VideoCard) {
            videoCardBuilder.buildPort(str);
            return;
        }
        if (type instanceof HeadPhones) {
            headPhonesBuilder.buildPort(str);
            return;
        }
    }

    public Type buildType(String type, Map<String, String> attributes) {
        if (PROCESSOR.equalsIgnoreCase(type)) {
            this.type = processorBuilder.buildProcessor(attributes);
        } else if (VIDEO_CARD.equalsIgnoreCase(type)) {
            this.type = videoCardBuilder.buildVideoCard(attributes);
        } else if (HEAD_PHONES.equalsIgnoreCase(type)) {
            this.type = headPhonesBuilder.buildHeadPhones(attributes);
        }
        return this.type;
    }

}
