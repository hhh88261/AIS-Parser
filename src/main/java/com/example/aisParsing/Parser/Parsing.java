package com.example.aisParsing.Parser;

import com.example.aisParsing.Service.ParsingService;
import com.example.aisParsing.Service.Type1ProcessorService;
import com.example.aisParsing.Service.Type5ProcessorService;
import dk.dma.ais.binary.SixbitException;
import dk.dma.ais.message.AisMessage;
import dk.dma.ais.message.AisMessage5;
import dk.dma.ais.message.AisMessageException;
import dk.dma.ais.sentence.SentenceException;
import dk.dma.ais.sentence.Vdm;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class Parsing implements ParsingService {

    private final Type1ProcessorService type1ProcessorService;
    private final Type5ProcessorService type5ProcessorService;
    public Parsing(Type1ProcessorService type1ProcessorService, Type5ProcessorService type5ProcessorService) {
        this.type1ProcessorService = type1ProcessorService;
        this.type5ProcessorService = type5ProcessorService;
    }

    @Override
    public void type1Decoder(String response) throws SentenceException, AisMessageException, SixbitException, IOException {

        Vdm vdm = new Vdm();

        // 메시지 파싱
        vdm.parse(response);

        AisMessage aisMessage = AisMessage.getInstance(vdm);

        String StringType1Message = aisMessage.toString();

        type1ProcessorService.messageConvert(StringType1Message);
    }

    @Override
    public void type5Decoder(String rawMessagePart1, String rawMessagePart2) throws Exception {

        Vdm vdm = new Vdm();

        // 두 개의 메시지를 순서대로 파싱
        vdm.parse(rawMessagePart1);
        vdm.parse(rawMessagePart2);

        // 두 메시지를 결합하여 aisMessage에 저장
        AisMessage aisMessage = AisMessage.getInstance(vdm);

        if (aisMessage instanceof AisMessage5) {
            AisMessage5 StringType5Message = (AisMessage5) aisMessage;
            type5ProcessorService.messageConvertor(StringType5Message);
        }
    }
}

