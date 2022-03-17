package com.example.demo.repository;

import com.example.demo.model.Firestation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FirestationDataAccessServiceTest {
    private static final FirestationDataAccessService fire = new FirestationDataAccessService();

    @Test
    void insertFirestation() {
        Firestation firestation = new Firestation("rue inconnue", "2");
        fire.insertFirestation(firestation);
    }

    @Test
    void selectAllFirestations() {
        fire.selectAllFirestations();
    }

    @Test
    void selectFirestation() {
        String station = "1";
        String address = "rue bidon";
        fire.selectFirestation(station, address);
    }

    @Test
    void selectFirestationAddressByStationNumber() {
        String station = "1";
        fire.selectFirestationAddressByStationNumber(station);
    }

    @Test
    void selectFirestationAddressByStationNumbers() {
        ArrayList<String> stations = new ArrayList<>();
        stations.add("1");
        stations.add("2");
        fire.selectFirestationAddressByStationNumbers(stations);
    }

    @Test
    void selectStationNumberByAddress() {
        String address = "rue bidon";
        fire.selectStationNumberByAddress(address);
    }

    @Test
    void deleteFirestation() {
        String station = "1";
        String address = "rue bidon";
        fire.deleteFirestation(station, address);
    }

    @Test
    void updateFirestation() {
        Firestation firestation = new Firestation("rue inconnue", "2");
        String station = "1";
        String address = "rue bidon";
        fire.updateFirestation(station, address, firestation);
    }


}