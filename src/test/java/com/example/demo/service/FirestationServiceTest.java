package com.example.demo.service;

import com.example.demo.dao.FirestationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Firestation;
import com.example.demo.repository.FirestationDataAccessService;
import com.example.demo.repository.PersonDataAccessService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class FirestationServiceTest {

    private final FirestationDao firestationDao = new FirestationDataAccessService();
    private final PersonDao personDao = new PersonDataAccessService();

    private final PersonService personService = new PersonService(personDao,firestationDao);

    private final FirestationService firestationService = new FirestationService(firestationDao, personService);

    @Test
    void addFirestation() {
        String address = "123 rue bidon";
        String station = "1";
        Firestation firestation = new Firestation(address, station);
        firestationService.addFirestation(firestation);
    }

    @Test
    void getAllFirestations() {
        firestationService.getAllFirestations();
    }

    @Test
    void getFirestation() {
        String address = "123 rue bidon";
        String station = "1";
        firestationService.getFirestation(address, station);
    }

    @Test
    void getFirestationAddress() {
        String station = "1";
        firestationService.getFirestationAddress(station);
    }

    @Test
    void getPersonsByStationNumber() throws ParseException {
        String station = "1";
        firestationService.getPersonsByStationNumber(station);
    }

    @Test
    void getStationNumberByAddress() {
        String address = "123 rue bidon";
        firestationService.getStationNumberByAddress(address);
    }

    @Test
    void deleteFirestation() {
        String station = "1";
        String address = "123 rue bidon";
        firestationService.deleteFirestation(station, address);
    }

    @Test
    void updateFirestation() {
        String station = "1";
        String address = "123 rue bidon";
        Firestation firestation = new Firestation(address, station);
        firestationService.updateFirestation(station, address, firestation);
    }
}