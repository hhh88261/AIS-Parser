package com.example.aisParsing.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

// DB 설정파일 로드
@Entity
@Table(name = "ship_locate")
public class ShipLocateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Mmsi;

    private String lat;

    private String lon;

    private LocalDate shipDate;

    private LocalTime shipTime;

    public String getMmsi(){
        return Mmsi;
    }

    public void setMmsi(String Mmsi){
        this.Mmsi = Mmsi;
    }

    public String getLat(){
        return lat;
    }

    public void setLat(String lat){
        this.lat = lat;
    }

    public String getLon(){
        return lon;
    }

    public void setLon(String lon){
        this.lon = lon;
    }

    @PrePersist
    public void setDefaultDateTime() {
        this.shipDate = LocalDate.now();
        this.shipTime = LocalTime.now();
    }
}
