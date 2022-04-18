package com.ocr.safetynet.repository;

import com.ocr.safetynet.model.Firestation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FirestationDataAccessServiceTest {

    private static FirestationDataAccessService fire;
    private Firestation firestation;

    @BeforeAll
    private static void setUp() {
        fire = new FirestationDataAccessService();
    }

    @BeforeEach
    private void setUpPerTest(){
        firestation = new Firestation("rue inconnue", "2");
    }

    @Test
    void insertFirestationTest() {
        fire.insertFirestation(firestation);
    }

    @Test
    void selectAllFirestationsTest() {
        fire.selectAllFirestations();
    }

    @Test
    void selectFirestationTest() {
        fire.selectFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Test
    void selectFirestationAddressByStationNumberTest() {
        fire.selectFirestationAddressByStationNumber(firestation.getStation());
    }

    @Test
    void selectFirestationAddressByStationNumbersTest() {
        ArrayList<String> stations = new ArrayList<>();
        stations.add(firestation.getStation());
        fire.selectFirestationAddressByStationNumbers(stations);
    }

    @Test
    void selectStationNumberByAddressTest() {
        fire.selectStationNumberByAddress(firestation.getAddress());
    }

    @Test
    void deleteFirestationTest() {
        fire.deleteFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Test
    void updateFirestationTest() {
        fire.updateFirestation(firestation.getStation(), firestation.getAddress(), firestation);
    }

}