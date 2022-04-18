package com.ocr.safetynet.controller;

import com.ocr.safetynet.model.Person;
import com.ocr.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    public ArrayList<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping(value = "person", params = {"firstName", "lastName"})
    public Person getPersonByFirstnameAndLastname(@RequestParam String firstName, @RequestParam String lastName){
        return personService.getPersonByFirstnameAndLastname(firstName, lastName);
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
    public ArrayList<String> getChildrenByAddress(@RequestParam String address){
        return personService.getChildrenByAddress(address);
    }

    @GetMapping(value = "communityEmail", params = "city")
    public ArrayList<String> getEmailsByCity(@RequestParam String city){
        return personService.getEmailsByCity(city);
    }

    @GetMapping(value = "fire", params = "address")
    public ArrayList<String> getPersonsByAddress(@RequestParam String address){
        return personService.getPersonsByAddress(address);
    }

    @GetMapping(value = "flood/stations", params = "stations")
    public ArrayList<String> getPersonsByListStationNumber(@RequestParam ArrayList<String> stations){
        return personService.getPersonsByStationNumbers(stations);
    }

    @GetMapping(value = "personInfo", params = {"firstName","lastName"})
    public ArrayList<String> getChildrenByFirestationNumber(@RequestParam String firstName, @RequestParam String lastName){
        return personService.getPersonInfoByName(firstName, lastName);
    }

    @GetMapping(value = "phoneAlert", params = "firestation")
    public ArrayList<String> getChildrenByFirestationNumber(@RequestParam String firestation){
        return personService.getPhonesByAddress(firestation);
    }

}
