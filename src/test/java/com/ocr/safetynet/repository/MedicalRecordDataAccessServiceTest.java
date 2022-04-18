package com.ocr.safetynet.repository;

import com.ocr.safetynet.model.MedicalRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MedicalRecordDataAccessServiceTest {

    private static MedicalRecordDataAccessService medical;
    private MedicalRecord medicalRecord;

    @BeforeAll
    private static void setUp() {
        medical = new MedicalRecordDataAccessService();
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
    void insertMedicalRecordTest() {
        medical.insertMedicalRecord(medicalRecord);
    }

    @Test
    void selectAllMedicalRecordsTest() {
        medical.selectAllMedicalRecords();
    }

    @Test
    void selectMedicalRecordTest() {
        medical.selectMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void deleteMedicalRecordTest() {
        medical.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void updateMedicalRecordTest() {
        medical.updateMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord);
    }

    @Test
    void selectMedicalBackgroundTest() {
        medical.selectMedicalBackground(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void selectPersonBirthdateTest() {
        medical.selectBirthdate(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    void calculatePersonAgeTest(){
        medical.calculateAge(medicalRecord.getBirthdate());
    }

}