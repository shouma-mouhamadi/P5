package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public String[] getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(value = "", params = {"firstName", "lastName"})
    public String getPersonByName(@RequestParam String firstName, @RequestParam String lastName){
        return personService.getPersonByName(firstName, lastName);
    }

    @DeleteMapping(value = "", params = {"firstName", "lastName"})
    public void deletePersonByName(@RequestParam String firstName, @RequestParam String lastName){
        personService.deletePerson(firstName, lastName);
    }

    @PutMapping(value = "", params = {"firstName", "lastName"})
    public void updatePerson(@RequestParam String firstName, @RequestParam String lastName, @Valid @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(firstName, lastName, personToUpdate);
    }

}
