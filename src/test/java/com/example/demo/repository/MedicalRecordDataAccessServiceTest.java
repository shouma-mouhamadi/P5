package com.example.demo.repository;

import com.example.demo.model.MedicalRecord;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordDataAccessServiceTest {
    static private final MedicalRecordDataAccessService medical = new MedicalRecordDataAccessService();

    @Test
    void insertMedicalRecord() {
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        medications.add("medicament");
        allergies.add("allergie");
        MedicalRecord medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
        medical.insertMedicalRecord(medicalRecord);
    }

    @Test
    void selectAllMedicalRecords() {
        medical.selectAllMedicalRecords();
    }

    @Test
    void selectMedicalRecord() {
        String firstname = "Shouma";
        String lastname= "Mouhamadi";
        medical.selectMedicalRecord(firstname, lastname);
    }

    @Test
    void deleteMedicalRecord() {
        String firstname = "Shouma";
        String lastname= "Mouhamadi";
        medical.deleteMedicalRecord(firstname, lastname);
    }

    @Test
    void updateMedicalRecord() {
        String firstname = "Shouma";
        String lastname= "Mouhamadi";
        ArrayList<String> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        medications.add("medicament");
        allergies.add("allergie");
        MedicalRecord medicalRecord = new MedicalRecord("Shouma", "Mouhamadi", "21/03/1990", medications, allergies);
        medical.updateMedicalRecord(firstname, lastname, medicalRecord);
    }

    @Test
    void selectMedicalBackground() {
        String firstname = "Shouma";
        String lastname= "Mouhamadi";
        medical.selectMedicalBackground(firstname, lastname);
    }

    @Test
    void selectPersonBirthdate() {
        String firstname = "Shouma";
        String lastname= "Mouhamadi";
        medical.selectPersonBirthdate(firstname, lastname);
    }

    @Test
    void calculatePersonAge() throws ParseException {
        String birthdate = "21/03/1990";
        medical.calculatePersonAge(birthdate);
    }

    @Test
    void determinePersonMajority() {
        long age = 18;
        medical.determinePersonMajority(age);
    }


}