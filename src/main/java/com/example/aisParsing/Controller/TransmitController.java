package com.example.aisParsing.Controller;

import com.example.aisParsing.Service.ITransmit;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.Map;

// 클라이언트 요청 처리
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TransmitController {
    private ITransmit iTransmit;

    public TransmitController(ITransmit iTransmit){
        this.iTransmit = iTransmit;
    }

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamSse() {
        System.out.println("사용자가 접속했습니다.");
        SseEmitter emitter = new SseEmitter(0L);

        iTransmit.addEmitter(emitter);
        return emitter;
    }

    @PostMapping("/data/send")
    public ResponseEntity<String> receiveData(@RequestBody Map<String, Object> payload) {
        System.out.println("클라이언트에서 받은 데이터: " + payload);
        return ResponseEntity.ok("데이터 수신 완료");
    }
}