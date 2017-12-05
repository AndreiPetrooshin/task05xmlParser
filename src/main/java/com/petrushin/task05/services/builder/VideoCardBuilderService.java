package com.petrushin.task05.services.builder;

import com.petrushin.task05.domain.VideoCard;
import com.petrushin.task05.domain.enums.Port;

import java.util.Map;

import static com.petrushin.task05.util.DeviceConst.*;

/**
 * This class helps build and initiate {@link VideoCard} instance
 * which extends {@link TypeBuilderService}
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class VideoCardBuilderService extends TypeBuilderService<VideoCard> {

    @Override
    public VideoCard buildType(Map<String, String> attributes) {
        VideoCard videoCard = new VideoCard();
        if (attributes.size() > 1) {
            String cooler = attributes.get(COOLER);
            boolean isCooler = Boolean.parseBoolean(cooler);
            videoCard.setCooler(isCooler);
        }
        String peripheral = attributes.get(PERIPHERAL);
        boolean isPeripheral = Boolean.parseBoolean(peripheral);
        videoCard.setPeripheral(isPeripheral);
        return videoCard;
    }

    @Override
    public VideoCard buildFrequency(VideoCard videoCard, String str) {
        videoCard.setFrequency(str);
        return videoCard;
    }

    @Override
    public VideoCard buildPort(VideoCard videoCard, String str) {
        Port port = Port.valueOf(str);
        videoCard.addPort(port);
        return videoCard;
    }

    @Override
    public VideoCard buildMemorySize(VideoCard videoCard, String str) {
        videoCard.setMemorySize(str);
        return videoCard;
    }

    @Override
    public VideoCard buildSensitivity(VideoCard videoCard, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public VideoCard buildCores(VideoCard videoCard, String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public VideoCard buildSocket(VideoCard videoCard, String str) {
        throw new UnsupportedOperationException();
    }



}
