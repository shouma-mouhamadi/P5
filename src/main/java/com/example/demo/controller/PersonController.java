package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "person")
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping(value = "person")
    public String[] getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(value = "person", params = {"firstName", "lastName"})
    public String getPersonByName(@RequestParam String firstName, @RequestParam String lastName){
        return personService.getPersonByName(firstName, lastName);
    }

    @DeleteMapping(value = "person", params = {"firstName", "lastName"})
    public void deletePersonByName(@RequestParam String firstName, @RequestParam String lastName){
        personService.deletePerson(firstName, lastName);
    }

    @PutMapping(value = "person", params = {"firstName", "lastName"})
    public void updatePerson(@RequestParam String firstName, @RequestParam String lastName, @Valid @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(firstName, lastName, personToUpdate);
    }

    @GetMapping(value = "childAlert", params = "address")
    public ArrayList<String> getChildrenByAddress(@RequestParam String address) throws ParseException {
        return personService.getChildrenByAddress(address);
    }

    @GetMapping(value = "communityEmail", params = "city")
    public ArrayList<String> getEmailsByCity(@RequestParam String city){
        return personService.getEmailsByCity(city);
    }

    @GetMapping(value = "fire", params = "address")
    public ArrayList<String> getPersonsByAddress(@RequestParam String address) throws ParseException {
        return personService.getPersonsByAddress(address);
    }

    @GetMapping(value = "flood/stations", params = "stations")
    public ArrayList<String> getPersonsByListStationNumber(@RequestParam ArrayList<String> stations) throws ParseException {
        return personService.getPersonsByStationNumbers(stations);
    }

    @GetMapping(value = "personInfo", params = {"firstName","lastName"})
    public ArrayList<String> getChildrenByFirestationNumber(@RequestParam String firstName, @RequestParam String lastName) throws ParseException {
        return personService.getPersonInfoByName(firstName, lastName);
    }

    @GetMapping(value = "phoneAlert", params = "firestation")
    public ArrayList<String> getChildrenByFirestationNumber(@RequestParam String firestation) throws ParseException {
        return personService.getPhonesByAddress(firestation);
    }

}
