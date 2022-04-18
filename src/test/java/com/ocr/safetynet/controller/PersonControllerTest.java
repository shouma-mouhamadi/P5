package com.ocr.safetynet.controller;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.dao.PersonDao;
import com.ocr.safetynet.model.Person;
import com.ocr.safetynet.repository.FirestationDataAccessService;
import com.ocr.safetynet.repository.PersonDataAccessService;
import com.ocr.safetynet.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

class PersonControllerTest {

    private static PersonController personController;
    private Person person;

    @BeforeAll
    private static void setUp() {
        PersonDao personDao = new PersonDataAccessService();
        FirestationDao firestationDao = new FirestationDataAccessService();
        PersonService personService = new PersonService(personDao, firestationDao);
        personController = new PersonController(personService);
    }

    @BeforeEach
    private void setUpPerTest(){
        person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
    }

    @Test
    void addPersonTest() {
        personController.addPerson(person);
    }

    @Test
    void getAllPersonsTest() {
        personController.getAllPersons();
    }

    @Test
    void getPersonByFirstnameAndLastnameTest(){
        personController.getPersonByFirstnameAndLastname(person.getFirstName(), person.getLastName());
    }

    @Test
    void deletePersonByNameTest() {
        personController.deletePersonByName(person.getFirstName(), person.getLastName());
    }

    @Test
    void updatePersonTest() {
        personController.updatePerson(person.getFirstName(), person.getLastName(), person);
    }

    @Test
    void getEmailsByCityTest() {
        personController.getEmailsByCity(person.getCity());
    }

    @Test
    void getPersonsByListStationNumberTest(){
        ArrayList<String> stations = new ArrayList<>();
        stations.add("1");
        stations.add("2");
        personController.getPersonsByListStationNumber(stations);

    }

    @Test
    void getChildrenByFirestationNumberTest(){
        String firestation = "1";
        personController.getChildrenByFirestationNumber(firestation);
    }

    /*@Test
    void getChildrenByAddressTest(){
        personController.getChildrenByAddress(person.getAddress());
    }

    @Test
    void getPersonsByAddressTest(){
        personController.getPersonsByAddress(person.getAddress());
    }

    @Test
    void getPersonInfoByNameTest(){
        personController.getChildrenByFirestationNumber(person.getFirstName(), person.getLastName());
    }*/
}