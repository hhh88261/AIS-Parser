package com.example.aisParsing.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ship_info")
public class ShipInfoEntity {
    @Id
    private String Mmsi;

    private String vss_name;

    private String vss_type;

    private String vss_area;

    private String vss_cargo;

    private String vss_nation;

    private String vss_gt;

    public String getMmsi(){return Mmsi;}

    public void setMmsi(String Mmsi){
        this.Mmsi = Mmsi;
    }
}
