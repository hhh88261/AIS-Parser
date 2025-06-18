package com.example.aisParsing.Service;

import dk.dma.ais.message.AisMessage5;

public interface Type5ProcessorService {
    void messageConvertor(AisMessage5 type5Message) throws Exception;
}
