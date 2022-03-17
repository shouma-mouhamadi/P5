package com.example.demo.controller;

import com.example.demo.dao.MedicalRecordDao;
import com.example.demo.model.MedicalRecord;
import com.example.demo.repository.MedicalRecordDataAccessService;
import com.example.demo.service.MedicalRecordService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordControllerTest {

    private final MedicalRecordDao medicalRecordDao = new MedicalRecordDataAccessService();

    private final MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordDao);

    private final MedicalRecordController medicalRecordController = new MedicalRecordController(medicalRecordService);

    @Test
    void addMedicalRecord() {
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        medications.add("doliprane");
        allergies.add("pollen");
        MedicalRecord medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
        medicalRecordController.addMedicalRecord(medicalRecord);
    }

    @Test
    void getAllMedicalRecords() {
        medicalRecordController.getAllMedicalRecords();
    }

    @Test
    void getMedicalRecord() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        medicalRecordController.getMedicalRecord(firstName, lastName);
    }

    @Test
    void deleteMedicalRecord() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        medicalRecordController.deleteMedicalRecord(firstName, lastName);
    }

    @Test
    void updateMedicalRecord() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        medications.add("doliprane");
        allergies.add("pollen");
        MedicalRecord medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
        medicalRecordController.updateMedicalRecord(firstName, lastName, medicalRecord);
    }
}