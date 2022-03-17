package com.example.demo.controller;

import com.example.demo.dao.FirestationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Firestation;
import com.example.demo.repository.FirestationDataAccessService;
import com.example.demo.repository.PersonDataAccessService;
import com.example.demo.service.FirestationService;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class FirestationControllerTest {
    private final PersonDao personDao = new PersonDataAccessService();
    private final FirestationDao firestationDao = new FirestationDataAccessService();
    private final PersonService personService = new PersonService(personDao, firestationDao);
    private final FirestationService firestationService = new FirestationService(firestationDao, personService);
    private final FirestationController firestationController = new FirestationController(firestationService, personService);

    @Test
    void addFirestation() {
        String address = "123 rue bidon";
        String station = "1";
        Firestation firestation = new Firestation(address, station);
        firestationController.addFirestation(firestation);
    }

    @Test
    void getAllFirestations() {
        firestationController.getAllFirestations();
    }

    @Test
    void getFirestation() {
        String station = "1";
        String address = "123 rue bidon";
        firestationController.getFirestation(station, address);
    }

    @Test
    void getPersonsByStationNumber() throws ParseException {
        String stationNumber = "1";
        firestationController.getPersonsByStationNumber(stationNumber);
    }

    @Test
    void deleteFirestation() {
        String station = "1";
        String address = "123 rue bidon";
        firestationController.deleteFirestation(station, address);
    }

    @Test
    void updateFirestation() {
        String address = "123 rue bidon";
        String station = "1";
        Firestation firestation = new Firestation(address, station);
        firestationController.updateFirestation(station, address, firestation);
    }
}