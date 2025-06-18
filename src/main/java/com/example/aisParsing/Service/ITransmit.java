package com.example.aisParsing.Service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ITransmit {
    SseEmitter addEmitter(SseEmitter emitter);
    void resultMessage(String aisMessage);
}
