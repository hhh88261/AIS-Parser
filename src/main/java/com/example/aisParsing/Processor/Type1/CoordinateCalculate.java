package com.example.aisParsing.Processor.Type1;

import com.example.aisParsing.Service.CoordinateCalculateService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CoordinateCalculate implements CoordinateCalculateService {
    public Map<String,String> coordinateCalculate(Map<String, String> convertedType1Message)  {

        // 계산 결과를 담을 새로운 Map 생성
        Map<String, String> resultMessage = new HashMap<>(convertedType1Message);

        // 좌표, 속도, 방향 계산
        String calculatedPos = CaculatePos(convertedType1Message.get("pos"));
        String TrueHead = convertedType1Message.get("trueHeading");
        String calculatedSog = CaculateSog(convertedType1Message.get("sog"));
        String calculatedCog = convertedType1Message.get("cog");

        // 계산된 값 새로운 Map에 저장
        resultMessage.put("pos", calculatedPos);
        resultMessage.put("sog", calculatedSog);
        resultMessage.put("cog", calculatedCog);

        // 미래 위치 계산
        if (TrueHead != null && !TrueHead.equals("511")) {
            String predictedValue = CalculateLocation(calculatedPos, calculatedCog, calculatedSog);
            resultMessage.put("predictedValue", predictedValue);
        }

        return resultMessage;
    }

    // 좌표 계산
    public String CaculatePos(String Pos) {
        String[] coordinates = Pos.split("/");
        double latitude = Double.parseDouble(coordinates[0]) / 600000.0;
        double longitude = Double.parseDouble(coordinates[1]) / 600000.0;
        String caculatedPos = String.format("%.6f", latitude) + "/" + String.format("%.6f", longitude);

        return caculatedPos;
    }

    // 속도 계산
    public String CaculateSog(String Sog) {
        double sogValue = Double.parseDouble(Sog); // 문자열을 double로 변환
        String caculatedSog = String.format(String.valueOf(sogValue / 10.0)); // 10으로 나누기

        return caculatedSog;
    }

    // 미래 위치 계산
    public  String CalculateLocation(String Pos, String cog, String Sog) {
        // lon과 lat에 저장, 형변환
        String[] coordinates = Pos.split("/");

        double lat = Double.parseDouble(coordinates[0].trim());
        double lon = Double.parseDouble(coordinates[1].trim());

        int cogCalucalate = Integer.parseInt(cog);
        double SogCalucalate = Double.parseDouble(Sog) / 10.0;

        // 10분 후 이동 거리 계산
        double d = (SogCalucalate * 10.0 / 60.0); // 10분 이동 거리

        // 이동 거리 계산
        double deltaNorth = d * Math.cos(Math.toRadians(cogCalucalate)); // 북쪽 이동 거리
        double deltaEast = d * Math.sin(Math.toRadians(cogCalucalate));  // 동쪽 이동 거리

        // 위도 변화
        double deltaLat = deltaNorth / 110.574; // 위도 1km당 변화량

        // 경도 변화
        double deltaLon = deltaEast / (111.320 * Math.cos(Math.toRadians(lat))); // 경도 1km당 변화량

        // 새로운 위치 계산
        String predictedValue = String.format("%.6f", lat + deltaLat) + "/" + String.format("%.6f", lon + deltaLon);

        return predictedValue;
    }

}
