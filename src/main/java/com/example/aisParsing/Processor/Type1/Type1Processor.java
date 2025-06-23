package com.example.aisParsing.Processor.Type1;

import com.example.aisParsing.Processor.JsonConverter;
import com.example.aisParsing.Service.ITransmit;
import com.example.aisParsing.Service.ShipLocateService;
import com.example.aisParsing.Service.Type1ProcessorService;
import com.example.aisParsing.Service.CoordinateCalculateService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Type1Processor implements Type1ProcessorService {
    private final MessageBuilder messageBuilder;
    private final JsonConverter jsonConverter;
    private final ShipLocateService shipLocateService;

    private final CoordinateCalculateService coordinateCalculateService;
    private final ITransmit iTransmit;

    public Type1Processor(MessageBuilder messageBuilder, CoordinateCalculateService coordinateCalculateService, JsonConverter jsonConverter, ITransmit iTransmit, ShipLocateService shipLocateService) {
        this.messageBuilder = messageBuilder;
        this.coordinateCalculateService = coordinateCalculateService;
        this.jsonConverter = jsonConverter;
        this.iTransmit = iTransmit;
        this.shipLocateService = shipLocateService;
    }

    // 요소 추출 및 가공
    public void messageConvert(String type1Message) {
        Map<String, String> convertedType1Message = messageBuilder.messageBuilder(type1Message);
        coordinateCalculate(convertedType1Message);
    }

    // 좌표 계산
    public void coordinateCalculate(Map<String, String> convertedType1Message) {
        Map<String, String> calculatedMessage = coordinateCalculateService.coordinateCalculate(convertedType1Message);
        jsonConvert(calculatedMessage);
        saveLocate(calculatedMessage);
    }

    // DB 저장
    public void saveLocate(Map<String, String> calculatedMessage){
        shipLocateService.locateRepository(calculatedMessage.get("mmsi"),calculatedMessage.get("pos"));
    }

    //Json 변환
    public void jsonConvert(Map<String, String> calculatedMessage) {
        String resultMessage = jsonConverter.jsonConverter(calculatedMessage);
        iTransmit.resultMessage(resultMessage);
    }
}
