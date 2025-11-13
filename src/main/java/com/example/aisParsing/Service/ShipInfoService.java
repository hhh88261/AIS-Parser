package com.example.aisParsing.Service;

import com.example.aisParsing.Entity.ShipInfoEntity;
import com.example.aisParsing.Repository.ShipInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class ShipInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ShipInfoService.class);

    @Autowired
    private ShipInfoRepository infoRepository;

    // 인메모리 캐시 Map 생성
    private final Map<String, Boolean> infoCache = new ConcurrentHashMap<>();

    // ship_info의 mmsi를 가져와 cache에 저장
    @PostConstruct
    public void initCache(){

        List<String> mmsiCache = infoRepository.findAllMmsi();
        for(String mmsi : mmsiCache){
            infoCache.put(mmsi, true);
        }
    }

    // upsert 클래스
    public void upsertShipInfo(String mmsi){

        if(infoCache.containsKey(mmsi)){
            long startTime = System.nanoTime();
            // mmsi가 존재할 때
            ShipInfoEntity entity = infoRepository.findById(mmsi)
                    .orElse(new ShipInfoEntity());

            infoRepository.save(entity);

            //long duration = (System.nanoTime() - startTime) / 1_000_000;
            //logger.info("캐시 히트: DB 연산에 {}ms 소요.", duration);


        } else {
            // 새로운 mmsi일 때
            ShipInfoEntity newEntity = new ShipInfoEntity();
            newEntity.setMmsi(mmsi);

            infoRepository.save(newEntity);

            infoCache.put(mmsi, true);
        }
    }
}