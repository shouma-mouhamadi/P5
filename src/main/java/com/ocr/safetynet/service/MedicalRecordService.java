package com.ocr.safetynet.service;

import com.ocr.safetynet.dao.MedicalRecordDao;
import com.ocr.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MedicalRecordService {

    private final MedicalRecordDao medicalRecordDao;

    @Autowired
    public MedicalRecordService(@Qualifier("medicalRecordData") MedicalRecordDao medicalRecordDao) {
        this.medicalRecordDao = medicalRecordDao;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord){
        medicalRecordDao.insertMedicalRecord(medicalRecord);
    }

    public ArrayList<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordDao.selectAllMedicalRecords();
    }

    public MedicalRecord getMedicalRecord(String firstName, String lastName){
        return medicalRecordDao.selectMedicalRecord(firstName, lastName);
    }

    public void deleteMedicalRecord(String firstName, String lastName){
        medicalRecordDao.deleteMedicalRecord(firstName, lastName);
    }

    public void updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord){
        medicalRecordDao.updateMedicalRecord(firstName, lastName, medicalRecord);
    }



}
