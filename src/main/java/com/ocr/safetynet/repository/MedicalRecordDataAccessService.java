package com.ocr.safetynet.repository;

import com.ocr.safetynet.dao.MedicalRecordDao;
import com.ocr.safetynet.model.MedicalRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Repository("medicalRecordData")
public class MedicalRecordDataAccessService implements MedicalRecordDao {

    private static final JSONParser jsonParser = new JSONParser();
    private static ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();

    static {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/java/com/ocr/safetynet/datasource/data.json"));
            JSONArray jsonMedicalRecords = (JSONArray) jsonObject.get("medicalrecords");
            for (Object o : jsonMedicalRecords) {
                jsonObject = (JSONObject) o;
                JSONArray jsonArrayMedication = (JSONArray) jsonObject.get("medications");
                JSONArray jsonArrayAllergies = (JSONArray) jsonObject.get("allergies");
                ArrayList<String> medications = new ArrayList<>();
                ArrayList<String> allergies = new ArrayList<>();
                for (Object value : jsonArrayMedication) {
                    medications.add(value.toString());
                }
                for (Object value : jsonArrayAllergies) {
                    allergies.add(value.toString());
                }
                MedicalRecord medicalRecord = new MedicalRecord(jsonObject.get("firstName").toString(),jsonObject.get("lastName").toString(),jsonObject.get("birthdate").toString(),medications,allergies);
                medicalRecords.add(medicalRecord);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMedicalRecords(ArrayList<MedicalRecord> medicalRecordArrayList){
        medicalRecords = medicalRecordArrayList;
    }

    @Override
    public void insertMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    @Override
    public ArrayList<MedicalRecord> selectAllMedicalRecords() {
        return medicalRecords;
    }

    @Override
    public MedicalRecord selectMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = null;
        for (MedicalRecord value : selectAllMedicalRecords()) {
            if (value.getFirstName().equals(firstName) && value.getLastName().equals(lastName)) {
                medicalRecord = value;
                break;
            }
        }
        return medicalRecord;

    }

    @Override
    public ArrayList<String> selectMedicalBackground(String firstName, String lastName) {
        ArrayList<String> medicalBackground = new ArrayList<>();
        MedicalRecord medicalRecord = selectMedicalRecord(firstName, lastName);
        if(medicalRecord != null){
            medicalBackground.add(medicalRecord.getMedications().toString());
            medicalBackground.add(medicalRecord.getAllergies().toString());
        }
        return medicalBackground;
    }

    @Override
    public String selectBirthdate(String firstName, String lastName){
        MedicalRecord medicalRecord = selectMedicalRecord(firstName, lastName);
        return medicalRecord.getBirthdate();
    }

    @Override
    public long calculateAge(String birthdate){
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        Calendar cal2 = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        assert date1 != null;
        cal1.setTime(date1);
        return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
    }

    @Override
    public boolean deleteMedicalRecord(String firstName, String lastName) {
        boolean result = false;
        MedicalRecord medicalRecord = selectMedicalRecord(firstName, lastName);
        if (medicalRecord != null){
            medicalRecords.remove(medicalRecord);
            result = true;
        }
        return result;
    }

    @Override
    public void updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        if(deleteMedicalRecord(firstName, lastName)){
            insertMedicalRecord(medicalRecord);
        }
    }
}
