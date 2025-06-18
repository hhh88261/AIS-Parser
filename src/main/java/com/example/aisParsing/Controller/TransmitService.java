package com.example.aisParsing.Controller;


import com.example.aisParsing.Service.ITransmit;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransmitService implements ITransmit {

    // 접속된 클라이언트 emitters 저장
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList();

    // emitter 처리
    public SseEmitter addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
        return emitter;
    }

    // AIS 메시지 받아 브로드캐스트
    public void resultMessage(String aisMessage) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("aisMessage")
                        .data(aisMessage));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(emitter);
            }
        }
    }
}