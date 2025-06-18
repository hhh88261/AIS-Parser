package com.example.aisParsing.Parser;

import com.example.aisParsing.Service.ParsingService;
import com.example.aisParsing.Service.Type1ProcessorService;
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

    public Parsing(Type1ProcessorService type1ProcessorService) {
        this.type1ProcessorService = type1ProcessorService;
    }

    @Override
    public void type1Decoder(String response) throws SentenceException, AisMessageException, SixbitException, IOException {

        Vdm vdm = new Vdm();

        // 메시지 파싱
        vdm.parse(response);

        AisMessage aisMessage = AisMessage.getInstance(vdm);

        if (aisMessage instanceof AisMessage5) {
            System.out.println("Not Type 1 Message");
            type5Decoder(response);
        }

        String StringType1Message = aisMessage.toString();

        type1ProcessorService.messageConvert(StringType1Message);
    }

    public void type5Decoder(String response) throws SentenceException, AisMessageException, SixbitException, IOException {

        Vdm vdm = new Vdm();
        String rawMessagePart1 = response;


        // TODO :: Type5MessageProcessor 구현
    }
}

