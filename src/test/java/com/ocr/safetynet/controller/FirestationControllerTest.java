package com.ocr.safetynet.controller;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.dao.PersonDao;
import com.ocr.safetynet.model.Firestation;
import com.ocr.safetynet.repository.FirestationDataAccessService;
import com.ocr.safetynet.repository.PersonDataAccessService;
import com.ocr.safetynet.service.FirestationService;
import com.ocr.safetynet.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FirestationControllerTest {

    private static FirestationController firestationController;
    private Firestation firestation;

    @BeforeAll
    private static void setUp() {
        PersonDao personDao = new PersonDataAccessService();
        FirestationDao firestationDao = new FirestationDataAccessService();
        PersonService personService = new PersonService(personDao, firestationDao);
        FirestationService firestationService = new FirestationService(firestationDao, personService);
        firestationController = new FirestationController(firestationService);
    }

    @BeforeEach
    private void setUpPerTest(){
        firestation = new Firestation("123 rue bidon", "1");
    }

    @Test
    void addFirestationTest() {
        firestationController.addFirestation(firestation);
    }

    @Test
    void getAllFirestationsTest() {
        firestationController.getAllFirestations();
    }

    @Test
    void getFirestationTest() {
        firestationController.getFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Test
    void getPersonsByStationNumberTest(){
        firestationController.getPersonsByStationNumber(firestation.getStation());
    }

    @Test
    void deleteFirestationTest() {
        firestationController.deleteFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Test
    void updateFirestationTest() {
        firestationController.updateFirestation(firestation.getStation(), firestation.getAddress(), firestation);
    }
}