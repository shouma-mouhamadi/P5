package com.example.demo.dao;

import com.example.demo.model.MedicalRecord;

import java.text.ParseException;
import java.util.ArrayList;

public interface MedicalRecordDao {

    void insertMedicalRecord(MedicalRecord medicalRecord);

    String[] selectAllMedicalRecords();

    String selectMedicalRecord(String firstName, String lastName);

    ArrayList<String> selectMedicalBackground(String firstName, String lastName);

    String selectPersonBirthdate(String firstName, String lastName);

    long calculatePersonAge(String birthdate) throws ParseException;

    String determinePersonMajority(long age);

    boolean deleteMedicalRecord(String firstName, String lastName);

    void updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);
}
