package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RequestMapping("flood")
@RestController
public class Flood {
    private final PersonService personService;

    public Flood(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "stations", params = "stations")
    public ArrayList<String> getPersonsByListStationNumber(@RequestParam ArrayList<String> stations) throws ParseException {
        return personService.getPersonsByStationNumbers(stations);
    }

}
