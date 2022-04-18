package com.ocr.safetynet.controller;

import com.ocr.safetynet.model.Firestation;
import com.ocr.safetynet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("firestation")
@RestController
public class FirestationController {

    private final FirestationService firestationService;

    @Autowired
    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @PostMapping
    public void addFirestation(@Valid @NonNull @RequestBody Firestation firestation){
        firestationService.addFirestation(firestation);
    }

    @GetMapping
    public ArrayList<Firestation> getAllFirestations(){
        return firestationService.getAllFirestations();
    }

    @GetMapping(value = "", params = {"station", "address"})
    public Firestation getFirestation(@RequestParam String station, @RequestParam String address){
        return firestationService.getFirestation(station, address);
    }

    @GetMapping(value = "", params = "stationNumber")
    public ArrayList<String> getPersonsByStationNumber(@RequestParam String stationNumber){
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
