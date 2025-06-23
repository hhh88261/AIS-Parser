package com.example.aisParsing.Repository;

import com.example.aisParsing.Entity.ShipLocateEntity;
import com.example.aisParsing.Service.ShipLocateService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component
public class LocateRepository implements ShipLocateService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void locateRepository(String Mmsi, String pos) {
        ShipLocateEntity locate = new ShipLocateEntity();

        String[] coordinates = pos.split("/");
        String lat = coordinates[0];
        String lon = coordinates[1];

        locate.setMmsi(Mmsi);
        locate.setLat(lat);
        locate.setLon(lon);

        em.persist(locate);
    }
}