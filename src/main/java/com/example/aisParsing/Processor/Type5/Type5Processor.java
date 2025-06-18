package com.example.aisParsing.Processor.Type5;

import com.example.aisParsing.Processor.JsonConverter;
import com.example.aisParsing.Processor.Type1.MessageBuilder;
import com.example.aisParsing.Service.ITransmit;
import com.example.aisParsing.Service.Type5ProcessorService;
import dk.dma.ais.message.AisMessage5;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Type5Processor implements Type5ProcessorService {
    private final MessageBuilder5 messageBuilder5;
    private final ShipSpecificationInformation shipSpecificationInformation;
    private final JsonConverter jsonConverter;
    private final ITransmit iTransmit;

    public Type5Processor(MessageBuilder5 messageBuilder5, ShipSpecificationInformation shipSpecificationInformation, JsonConverter jsonConverter, ITransmit iTransmit){
        this.messageBuilder5 = messageBuilder5;
        this.shipSpecificationInformation = shipSpecificationInformation;
        this.jsonConverter = jsonConverter;
        this.iTransmit = iTransmit;
    }

    // 메시지 추출 및 가공
    public void messageConvertor(AisMessage5 type5Message) throws Exception {
        Map<String, String> map = messageBuilder5.messageBuilder5(type5Message);
        shipImformation(map);
    }

    // 제원정보 조회(해양수산부 API)
    public void shipImformation(Map<String, String> map) throws Exception {
        String[] shipInfo = shipSpecificationInformation.type5message(map.get("callSign"), map.get("shipName"));
    }

    // 결과 값 전송(클라이언트 송신)
    public void jsonConvert(Map<String, String> calculatedMessage) {
        String resultMessage = jsonConverter.jsonConverter(calculatedMessage);
        iTransmit.resultMessage(resultMessage);
    }
}
