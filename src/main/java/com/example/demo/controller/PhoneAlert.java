package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class PhoneAlert {

    private final PersonService personService;

    public PhoneAlert(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "phoneAlert", params = "firestation")
    public ArrayList<String> getChildrenByFirestationNumber(@RequestParam String firestation) throws ParseException {
        return personService.getPhonesByAddress(firestation);
    }
}
