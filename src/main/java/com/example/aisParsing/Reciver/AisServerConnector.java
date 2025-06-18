package com.example.aisParsing.Reciver;

import com.example.aisParsing.Service.ParsingService;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.DeploymentException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

// AIS 서버와 연결
@Component
public class AisServerConnector implements CommandLineRunner {

    // 메시지 타입 분류기 의존성 주입
    private final ParsingService parsingService;

    public AisServerConnector(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    // Bean 초기화 되자마자 백그라운드에서 실행
    @Override
    public void run(String... args) throws Exception {
        // webSocketOpen.startWebSocket();
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 9999));
            System.out.println("TCP소켓 접속 됨");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String response;
                // reader의 데이터를 읽어옴
                while ((response = reader.readLine()) != null) {
                    parsingService.type1Decoder(response);
                    System.out.println(response);
                }
            }
        } catch (Exception e) {
            System.out.println("연결 실패");
        }
    }

}
