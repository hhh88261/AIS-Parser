package com.example.aisParsing.Processor.Type5;

import org.springframework.stereotype.Component;
import dk.dma.ais.message.AisMessage5;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageBuilder5 {

    public Map<String, String> messageBuilder5(AisMessage5 type5Message){

        Map<String, String> map = new HashMap<>();

        String mmsi5 = String.valueOf(type5Message.getUserId());
        String msgId = String.valueOf(type5Message.getMsgId());
        String shipName = type5Message.getName();
        String callSign = type5Message.getCallsign();

        // 불필요한 문자 제거
        shipName = shipName.replace("@", ""); // @ 기호 제거
        shipName = shipName.replaceAll("\\s+$", ""); // 마지막 공백 제거
        callSign = callSign.replaceAll("\\s+", ""); // 모든 공백 제거

        map.put("mmsi", mmsi5);
        map.put("msgId", msgId);
        map.put("shipName", shipName);
        map.put("callSign", callSign);

        return map;
    }
}