package com.ocr.safetynet.controller;

import com.ocr.safetynet.model.MedicalRecord;
import com.ocr.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("medicalRecord")
@RestController
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public void addMedicalRecord(@Valid @NonNull @RequestBody MedicalRecord medicalRecord){
        medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @GetMapping
    public ArrayList<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordService.getAllMedicalRecord();
    }

    @GetMapping(value = "", params = {"firstName", "lastName"})
    public MedicalRecord getMedicalRecord(@RequestParam String firstName, @RequestParam String lastName){
        return medicalRecordService.getMedicalRecord(firstName, lastName);
    }

    @DeleteMapping(value = "", params = {"firstName", "lastName"})
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName){
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }

    @PutMapping(value = "", params = {"firstName", "lastName"})
    public void updateMedicalRecord(@RequestParam String firstName, @RequestParam String lastName, @Valid @NonNull @RequestBody MedicalRecord medicalRecord){
        medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
    }

}
