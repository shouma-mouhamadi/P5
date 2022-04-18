package com.ocr.safetynet.service;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.dao.PersonDao;
import com.ocr.safetynet.model.Firestation;
import com.ocr.safetynet.repository.FirestationDataAccessService;
import com.ocr.safetynet.repository.PersonDataAccessService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FirestationServiceTest {

    private static FirestationService firestationService;
    private Firestation firestation;

    @BeforeAll
    private static void setUp() {
        FirestationDao firestationDao = new FirestationDataAccessService();
        PersonDao personDao = new PersonDataAccessService();
        PersonService personService = new PersonService(personDao, firestationDao);
        firestationService = new FirestationService(firestationDao, personService);
    }

    @BeforeEach
    private void setUpPerTest(){
        firestation = new Firestation("123 rue bidon", "1");
    }

    @Test
    void addFirestationTest() {
        firestationService.addFirestation(firestation);
    }

    @Test
    void getAllFirestationsTest() {
        firestationService.getAllFirestations();
    }

    @Test
    void getFirestationTest() {
        firestationService.getFirestation(firestation.getAddress(), firestation.getStation());
    }

    @Test
    void getFirestationAddressTest() {
        firestationService.getFirestationAddress(firestation.getStation());
    }

    @Test
    void getPersonsByStationNumberTest(){
        firestationService.getPersonsByStationNumber(firestation.getStation());
    }

    @Test
    void getStationNumberByAddressTest() {
        firestationService.getStationNumberByAddress(firestation.getAddress());
    }

    @Test
    void deleteFirestationTest() {
        firestationService.deleteFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Test
    void updateFirestationTest() {
        firestationService.updateFirestation(firestation.getStation(), firestation.getAddress(), firestation);
    }
}