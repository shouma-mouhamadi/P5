package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class CommunityEmail {
    private final PersonService personService;

    public CommunityEmail(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "communityEmail", params = "city")
    public ArrayList<String> getEmailsByCity(@RequestParam String city){
        return personService.getEmailsByCity(city);
    }
}
