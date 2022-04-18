package com.ocr.safetynet.dao;

import com.ocr.safetynet.model.MedicalRecord;
import java.util.ArrayList;

public interface MedicalRecordDao {

    void addMedicalRecords(ArrayList<MedicalRecord> medicalRecordArrayList);

    void insertMedicalRecord(MedicalRecord medicalRecord);

    ArrayList<MedicalRecord> selectAllMedicalRecords();

    MedicalRecord selectMedicalRecord(String firstName, String lastName);

    ArrayList<String> selectMedicalBackground(String firstName, String lastName);

    String selectBirthdate(String firstName, String lastName);

    long calculateAge(String birthdate);

    boolean deleteMedicalRecord(String firstName, String lastName);

    void updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);
}
