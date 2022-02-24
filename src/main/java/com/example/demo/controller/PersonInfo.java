package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class PersonInfo {
    private final PersonService personService;

    public PersonInfo(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "personInfo", params = {"firstName","lastName"})
    public ArrayList<String> getChildrenByFirestationNumber(@RequestParam String firstName, @RequestParam String lastName) throws ParseException {
        return personService.getPersonInfoByName(firstName, lastName);
    }
}
