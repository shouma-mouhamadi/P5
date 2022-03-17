package com.example.demo.repository;

import com.example.demo.model.Person;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonDataAccessServiceTest {

    private static final PersonDataAccessService personDataAccessService =  new PersonDataAccessService();

    @Test
    void selectAllPeople() {
        personDataAccessService.selectAllPeople();
    }

    @Test
    void selectPersonByName() {
        String firstname = "Jean-Claude";
        String lastname = "Vandale";
        personDataAccessService.selectPersonByName(firstname, lastname);
    }

    @Test
    void selectPersonInfoByName() throws ParseException {
        String firstname = "Jean-Claude";
        String lastname = "Vandale";
        personDataAccessService.selectPersonInfoByName(firstname, lastname);
    }

    @Test
    void selectPersonsByOneFirestationAddress() throws ParseException {
        String address = "rue bidon";
        personDataAccessService.selectPersonsByOneFirestationAddress(address);
    }

    @Test
    void selectPersonsInfoByFirestationAddress() throws ParseException {
        ArrayList<String> persons = new ArrayList<>();
        persons.add("Jean Philippe");
        personDataAccessService.selectPersonsInfoByFirestationAddress(persons);
    }

    @Test
    void selectPersonsByFirestationAddress() throws ParseException {
        ArrayList<String> address = new ArrayList<>();
        address.add("rue inconnue");
        personDataAccessService.selectPersonsByFirestationAddress(address);
    }

    @Test
    void selectPhonesByFirestationAddress() {
        ArrayList<String> address = new ArrayList<>();
        address.add("rue inconnue");
        personDataAccessService.selectPhonesByFirestationAddress(address);
    }

    @Test
    void selectEmailByCity() {
        String city = "Culver";
        personDataAccessService.selectEmailByCity(city);
    }

    @Test
    void selectChildrenByAddress() throws ParseException {
        String address = "123 rue bidon";
        personDataAccessService.selectChildrenByAddress(address);
    }

    @Test
    void deletePersonByName() {
        String firstname = "Jean-Claude";
        String lastname = "Vandale";
        personDataAccessService.deletePersonByName(firstname, lastname);
    }

    @Test
    void insertPerson() {
        Person person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
        personDataAccessService.insertPerson(person);
    }

    @Test
    void updatePersonByName() {
        String firstname = "Shouma";
        String lastname = "Mouhamadi";
        Person person = new Person("Shouma", "Mouhamadi", "rue inconnue", "Meaux", "77100", "0749800192", "shouma77100@gmail.com");
        personDataAccessService.updatePersonByName(firstname, lastname, person);
    }


}