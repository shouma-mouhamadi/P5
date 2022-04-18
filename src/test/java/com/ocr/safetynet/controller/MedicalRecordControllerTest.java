package com.ocr.safetynet.controller;

import com.ocr.safetynet.dao.MedicalRecordDao;
import com.ocr.safetynet.model.MedicalRecord;
import com.ocr.safetynet.repository.MedicalRecordDataAccessService;
import com.ocr.safetynet.service.MedicalRecordService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MedicalRecordControllerTest {

    private static MedicalRecordController medicalRecordController;
    private MedicalRecord medicalRecord;

    @BeforeAll
    private static void setUp() {
        MedicalRecordDao medicalRecordDao = new MedicalRecordDataAccessService();
        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordDao);
        medicalRecordController = new MedicalRecordController(medicalRecordService);
    }

    @BeforeEach
    private void setUpPerTest(){
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        medications.add("doliprane");
        allergies.add("pollen");
        medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
    }

    @Test
    void addMedicalRecordTest() {
        medicalRecordController.addMedicalRecord(medicalRecord);
    }

    @Test
    void getAllMedicalRecordsTest() {
        medicalRecordController.getAllMedicalRecords();
    }

    @Test
    void getMedicalRecordTest() {
        medicalRecordController.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void deleteMedicalRecordTest() {
        medicalRecordController.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void updateMedicalRecordTest() {
        medicalRecordController.updateMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord);
    }
}