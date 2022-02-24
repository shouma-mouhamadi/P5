package com.example.demo.service;

import com.example.demo.dao.MedicalRecordDao;
import com.example.demo.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public String[] getAllMedicalRecord(){
        return medicalRecordDao.selectAllMedicalRecords();
    }

    public String getMedicalRecord(String firstName, String lastName){
        return medicalRecordDao.selectMedicalRecord(firstName, lastName);
    }

    public void deleteMedicalRecord(String firstName, String lastName){
        medicalRecordDao.deleteMedicalRecord(firstName, lastName);
    }

    public void updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord){
        medicalRecordDao.updateMedicalRecord(firstName, lastName, medicalRecord);
    }



}
