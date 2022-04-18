package com.ocr.safetynet.service;

import com.ocr.safetynet.dao.MedicalRecordDao;
import com.ocr.safetynet.model.MedicalRecord;
import com.ocr.safetynet.repository.MedicalRecordDataAccessService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MedicalRecordServiceTest {

    private static MedicalRecordService medicalRecordService;
    private MedicalRecord medicalRecord;

    @BeforeAll
    private static void setUp() {
        MedicalRecordDao medicalRecordDao = new MedicalRecordDataAccessService();
        medicalRecordService = new MedicalRecordService(medicalRecordDao);
    }

    @BeforeEach
    private void setUpPerTest(){
        medicalRecord = new MedicalRecord(
                "Shouma",
                "Mouhamadi",
                "21/03/1990",
                new ArrayList<>() {{add("Paxlovid");add("Xevudy");}},
                new ArrayList<>() {{add("pollens");}});
    }

    @Test
    void addMedicalRecordTest() {

        medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @Test
    void getAllMedicalRecordTest() {
        medicalRecordService.getAllMedicalRecord();
    }

    @Test
    void getMedicalRecordTest() {
        medicalRecordService.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void deleteMedicalRecordTest() {
        medicalRecordService.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void updateMedicalRecordTest() {
        medicalRecordService.updateMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord);
    }
}