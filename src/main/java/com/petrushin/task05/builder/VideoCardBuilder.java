package com.petrushin.task05.builder;

import com.petrushin.task05.domain.VideoCard;
import com.petrushin.task05.domain.enums.Port;

import java.util.Map;

import static com.petrushin.task05.domain.DeviceConst.*;

public class VideoCardBuilder {

    private VideoCard videoCard;

    public VideoCard buildVideoCard(Map<String, String> attributes) {
        VideoCard videoCard = new VideoCard();
        if (attributes.size() > 1) {
            String cooler = attributes.get(COOLER);
            boolean isCooler = Boolean.parseBoolean(cooler);
            videoCard.setCooler(isCooler);
        }
        String peripheral = attributes.get(PERIPHERAL);
        boolean isPeripheral = Boolean.parseBoolean(peripheral);
        videoCard.setPeripheral(isPeripheral);
        this.videoCard = videoCard;
        return videoCard;

    }

    public void buildFrequency(String str) {
        videoCard.setFrequency(str);
    }

    public void buildMemorySize(String str) {
        videoCard.setMemorySize(str);
    }


    public void buildPort(String str) {
        Port port = Port.valueOf(str);
        videoCard.addPort(port);
    }
}
