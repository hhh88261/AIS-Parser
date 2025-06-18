package com.example.aisParsing.Service;

import dk.dma.ais.binary.SixbitException;
import dk.dma.ais.message.AisMessageException;
import dk.dma.ais.sentence.SentenceException;

import java.io.BufferedReader;
import java.io.IOException;

public interface ParsingService {
    void type1Decoder(String response) throws AisMessageException, SixbitException, SentenceException, IOException;
}
