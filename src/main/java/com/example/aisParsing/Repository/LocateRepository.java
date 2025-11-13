package com.example.aisParsing.Repository;

import com.example.aisParsing.Entity.ShipLocateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface LocateRepository extends JpaRepository<ShipLocateEntity, String> {

}
