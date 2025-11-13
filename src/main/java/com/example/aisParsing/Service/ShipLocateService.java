package com.example.aisParsing.Service;


import com.example.aisParsing.Entity.ShipLocateEntity;
import com.example.aisParsing.Repository.LocateRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipLocateService {
    private final LocateRepository locateRepository;


    public ShipLocateService(LocateRepository shipLocateRepository){
        this.locateRepository = shipLocateRepository;
    }


    public void insertShipLocate(String mmsi, String pos){
        ShipLocateEntity locateEntity = new ShipLocateEntity();


        String[] coordinates = pos.split("/");
        String lat = coordinates[0];
        String lon = coordinates[1];

        locateEntity.setMmsi(mmsi);
        locateEntity.setLat(lat);
        locateEntity.setLon(lon);
    }

}
