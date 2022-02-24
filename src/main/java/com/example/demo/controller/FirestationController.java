package com.example.demo.controller;

import com.example.demo.model.Firestation;
import com.example.demo.service.FirestationService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;

@RequestMapping("firestation")
@RestController
public class FirestationController {

    private final FirestationService firestationService;

    @Autowired
    public FirestationController(FirestationService firestationService, PersonService personService) {
        this.firestationService = firestationService;
    }

    @PostMapping
    public void addFirestation(@Valid @NonNull @RequestBody Firestation firestation){
        firestationService.addFirestation(firestation);
    }

    @GetMapping
    public String[] getAllFirestations(){
        return firestationService.getAllFirestations();
    }

    @GetMapping(value = "", params = {"station", "address"})
    public String getFirestation(@RequestParam String station, @RequestParam String address){
        return firestationService.getFirestation(station, address);
    }

    @GetMapping(value = "", params = "stationNumber")
    public ArrayList<String> getPersonsByStationNumber(@RequestParam String stationNumber) throws ParseException {
        return firestationService.getPersonsByStationNumber(stationNumber);
    }

    @DeleteMapping(value = "", params = {"station", "address"})
    public void deleteFirestation(@RequestParam String station, @RequestParam String address){
        firestationService.deleteFirestation(station, address);
    }

    @PutMapping(value = "", params = {"station", "address"})
    public void updateFirestation(@RequestParam String station, @RequestParam String address, @Valid @NonNull @RequestBody Firestation firestation){
        firestationService.updateFirestation(station, address, firestation);
    }

}
