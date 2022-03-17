package com.example.demo.service;

import com.example.demo.dao.MedicalRecordDao;
import com.example.demo.model.MedicalRecord;
import com.example.demo.repository.MedicalRecordDataAccessService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordServiceTest {
    private static final MedicalRecordDao medicalRecordDao = new MedicalRecordDataAccessService();
    private static final MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordDao);

    @Test
    void addMedicalRecord() {
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
        medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @Test
    void getAllMedicalRecord() {
        medicalRecordService.getAllMedicalRecord();
    }

    @Test
    void getMedicalRecord() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        medicalRecordService.getMedicalRecord(firstName, lastName);
    }

    @Test
    void deleteMedicalRecord() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }

    @Test
    void updateMedicalRecord() {
        String firstName = "Shouma";
        String lastName = "Mouhamadi";
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
        medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
    }
}