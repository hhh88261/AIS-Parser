package com.example.aisParsing.Processor.Type5;

import com.example.aisParsing.Reciver.ApiExplorer;
import com.example.aisParsing.Service.ApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ShipSpecificationInformation {

    private final ApiService apiService;

    public ShipSpecificationInformation(ApiService apiService) {
        this.apiService = apiService;
    }

    public String[] type5message(String callSign, String shipName) throws Exception {
        // 선박제원정보 가져오기
        String jsonData = apiService.fetchJsonData(callSign, shipName);

        // JSON 문자열을 JsonNode로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        // 필드 추출
        JsonNode itemNode = jsonNode.path("body").path("items").path("item");

        String clsgn = itemNode.path("clsgn").asText();      // 호출부호
        String vsslNo = itemNode.path("vsslNo").asText();    // 선박번호
        String vsslEngNm = itemNode.path("vsslEngNm").asText();  // 선박명
        String vsslNlty = itemNode.path("vsslNlty").asText();    // 선박국적
        String grtg = itemNode.path("grtg").asText();        // 총톤수
        String nvgShapNm = itemNode.path("nvgShapNm").asText();  // 운항형태명

        // 추출한 값을 배열에 저장
        String[] shipInfo = {clsgn, vsslNo, vsslEngNm, vsslNlty, grtg, nvgShapNm};

        return shipInfo;
    }
}
