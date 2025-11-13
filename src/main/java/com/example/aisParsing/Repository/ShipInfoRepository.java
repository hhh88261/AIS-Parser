package com.example.aisParsing.Repository;

import com.example.aisParsing.Entity.ShipInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface ShipInfoRepository extends JpaRepository<ShipInfoEntity, String> {

    //ship_info 의 mmsi 값 모두 조회
    @Query("SELECT s.Mmsi FROM ShipInfoEntity s")
    List<String> findAllMmsi();
}
