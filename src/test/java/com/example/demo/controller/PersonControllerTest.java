package com.example.demo.controller;

import com.example.demo.dao.FirestationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import com.example.demo.repository.FirestationDataAccessService;
import com.example.demo.repository.PersonDataAccessService;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonControllerTest {

    private final PersonDao personDao = new PersonDataAccessService();
    private final FirestationDao firestationDao = new FirestationDataAccessService();

    private final PersonService personService = new PersonService(personDao,firestationDao);

    private final PersonController personController = new PersonController(personService);

    @Test
    void addPerson() {
        Person person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
        personController.addPerson(person);
    }

    @Test
    void getAllPeople() {
        personController.getAllPeople();
    }

    @Test
    void getPersonByName() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        personController.getPersonByName(firstName, lastName);
    }

    @Test
    void deletePersonByName() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        personController.deletePersonByName(firstName, lastName);
    }

    @Test
    void updatePerson() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        Person person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
        personController.updatePerson(firstName, lastName, person);
    }

    @Test
    void getChildrenByAddress() throws ParseException {
        String address = "123 rue bidon";
        personController.getChildrenByAddress(address);
    }

    @Test
    void getEmailsByCity() {
        String city = "Meaux";
        personController.getEmailsByCity(city);
    }

    @Test
    void getPersonsByAddress() throws ParseException {
        String address = "123 rue bidon";
        personController.getPersonsByAddress(address);
    }

    @Test
    void getPersonsByListStationNumber() throws ParseException {
        ArrayList<String> stations = new ArrayList<>();
        stations.add("1");
        stations.add("2");
        personController.getPersonsByListStationNumber(stations);

    }

    @Test
    void getChildrenByFirestationNumber() throws ParseException {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        personController.getChildrenByFirestationNumber(firstName, lastName);
    }

    @Test
    void testGetChildrenByFirestationNumber() throws ParseException {
        String firestation = "1";
        personController.getChildrenByFirestationNumber(firestation);
    }
}