package com.petrushin.task05.services;

import com.petrushin.task05.domain.*;
import com.petrushin.task05.domain.enums.Group;
import com.petrushin.task05.domain.enums.Port;
import com.petrushin.task05.domain.enums.Socket;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final List<Device> EXPECTED = Arrays.asList(
            new Device("_1","R7 1800x(BOX)","China",350,
                    new Processor(true,true,95,Group.IO_DEVICE, Socket.AM4,
                            "4.0gHz",8),true),
            new Device("_2","Sennheiser CX300","Germany",50,
                    new HeadPhones(false,15,Group.MULTIMEDIA,
                            Arrays.asList(Port.AUX),"90dB"),false),
            new Device("_3","Palit gtx 1060","China",220,
                    new VideoCard(true,true,140,Group.IO_DEVICE,
                            Arrays.asList(Port.DVI,Port.HDMI),"1400mHz",
                            "3gb"),false)
    );

}
