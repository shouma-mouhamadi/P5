package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class Fire {
    private final PersonService personService;

    public Fire(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "fire", params = "address")
    public ArrayList<String> getChildrenByAddress(@RequestParam String address) throws ParseException {
        return personService.getPersonsByAddress(address);
    }
}
