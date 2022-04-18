package com.ocr.safetynet.service;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.dao.PersonDao;
import com.ocr.safetynet.model.Person;
import com.ocr.safetynet.repository.FirestationDataAccessService;
import com.ocr.safetynet.repository.PersonDataAccessService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonServiceTest {

    private static PersonService personService;
    private Person person;

    @BeforeAll
    private static void setUp() {
        PersonDao personDao = new PersonDataAccessService();
        FirestationDao firestationDao = new FirestationDataAccessService();
        personService = new PersonService(personDao, firestationDao);
    }

    @BeforeEach
    private void setUpPerTest(){
        person = new Person(
                "Shouma",
                "Mouhamadi",
                "rue inconnue",
                "Meaux",
                "77100",
                "0749800192",
                "shouma77100@gmail.com");
    }

    @Test
    void addPersonTest() {
        personService.addPerson(person);
    }

    @Test
    void getAllPersonsTest() {
        personService.getAllPersons();
    }

    @Test
    void getPersonsByStationNumberTest(){
        personService.getPersonsByStationNumber("1");
    }

    @Test
    void getPersonsByAddressTest(){
        personService.getPersonsByAddress(person.getAddress());
    }

    @Test
    void getPhonesByAddressTest(){
        personService.getPhonesByAddress(person.getAddress());
    }

    @Test
    void getEmailsByCityTest() {
        personService.getEmailsByCity(person.getCity());
    }

    @Test
    void getChildrenByAddressTest(){
        personService.getChildrenByAddress(person.getAddress());
    }

    @Test
    void deletePersonTest() {
        personService.deletePerson(person.getFirstName(), person.getLastName());
    }

    @Test
    void updatePersonTest() {
        personService.updatePerson(person.getFirstName(), person.getLastName(), person);
    }

    /*@Test
    void getPersonInfoByNameTest(){
        personService.getPersonInfoByName(person.getFirstName(), person.getLastName());
    }*/

}