package com.example.demo.service;
import com.example.demo.model.Person;
import com.example.demo.repository.FirestationDataAccessService;
import com.example.demo.repository.PersonDataAccessService;
import com.example.demo.service.PersonService;
import com.example.demo.dao.FirestationDao;
import com.example.demo.dao.PersonDao;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private final PersonDao personDao = new PersonDataAccessService();
    private final FirestationDao firestationDao = new FirestationDataAccessService();

    private final PersonService personService = new PersonService(personDao,firestationDao);

    PersonServiceTest() {
    }

    @Test
    void addPerson() {
        Person person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
        personService.addPerson(person);
    }

    @Test
    void getAllPeople() {
        personService.getAllPeople();
    }

    @Test
    void getPersonByName() {
        personService.getPersonByName("Shouma","Mouhamadi");
    }

    @Test
    void getPersonInfoByName() throws ParseException {
        String firstName =  "John";
        String lastName = "Boyd";
        personService.getPersonInfoByName(firstName, lastName);
    }

    @Test
    void getPersonsByStationNumbers() throws ParseException {
        ArrayList<String> stations = new ArrayList<>();
        stations.add("1");
        personService.getPersonsByStationNumbers(stations);
    }

    @Test
    void getPersonsByStationNumber() throws ParseException {
        String station = "1";
        personService.getPersonsByStationNumber(station);
    }

    @Test
    void getPersonsByAddress() throws ParseException {
        String address = "123 rue bidon";
        personService.getPersonsByAddress(address);
    }

    @Test
    void getPhonesByAddress() throws ParseException {
        String address = "123 rue bidon";
        personService.getPhonesByAddress(address);
    }

    @Test
    void getEmailsByCity() {
        String city = "Culver";
        personService.getEmailsByCity(city);
    }

    @Test
    void getChildrenByAddress() throws ParseException {
        String address = "123 rue bidon";
        personService.getChildrenByAddress(address);
    }

    @Test
    void deletePerson() {
        String firstName =  "Shouma";
        String lastName = "Mouhamadi";
        personService.deletePerson(firstName, lastName);
    }

    @Test
    void updatePerson() {
        String firstName =  "Shouma";
        String lastName = "Mouhamadi";
        Person person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
        personService.updatePerson(firstName, lastName, person);
    }
}