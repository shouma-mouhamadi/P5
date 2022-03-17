package com.example.demo.repository;

import com.example.demo.dao.MedicalRecordDao;
import com.example.demo.model.MedicalRecord;
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
import java.util.Objects;

@Repository("medicalRecordData")
public class MedicalRecordDataAccessService implements MedicalRecordDao {

    private static final JSONParser jsonParser = new JSONParser();
    private static JSONArray jsonArray = new JSONArray();

    static {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/java/com/example/demo/datasource/data.json"));
            jsonArray = (JSONArray) jsonObject.get("medicalrecords");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insertMedicalRecord(MedicalRecord medicalRecord) {
        boolean result=false;
        JSONObject jsonO = new JSONObject();
        jsonO.put("firstName", medicalRecord.getFirstName());
        jsonO.put("lastName", medicalRecord.getLastName());
        jsonO.put("birthdate", medicalRecord.getBirthdate());
        jsonO.put("medications", medicalRecord.getMedications());
        jsonO.put("allergies", medicalRecord.getAllergies());
        if(jsonArray.add(jsonO)){
            result=true;
        }
    }

    @Override
    public String[] selectAllMedicalRecords() {
        String[] bdd = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            bdd[i]= jsonArray.get(i).toString();
        }
        return bdd;
    }

    @Override
    public String selectMedicalRecord(String firstName, String lastName) {
        String result = null;
        for (Object o : jsonArray) {
            if (o.toString().contains(firstName) && o.toString().contains(lastName)) {
                result = o.toString();
                break;
            }
        }
        return result;
    }

    @Override
    public ArrayList<String> selectMedicalBackground(String firstName, String lastName) {
        JSONObject jsonObject;
        ArrayList<String> medicalBackground = new ArrayList<>();
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("firstName").equals(firstName) && jsonObject.get("lastName").equals(lastName)) {
                medicalBackground.add("Medications: "+jsonObject.get("medications").toString()+ " / Allergies: " +jsonObject.get("allergies").toString());
                break;
            }
        }

        return medicalBackground;
    }

    @Override
    public String selectPersonBirthdate(String firstName, String lastName){
        JSONObject jsonObject;
        String birthdate = null;
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("firstName").equals(firstName) && jsonObject.get("lastName").equals(lastName)) {
                birthdate = jsonObject.get("birthdate").toString();
                break;
            }
        }

        return birthdate;
    }

    @Override
    public long calculatePersonAge(String birthdate) throws java.text.ParseException {

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        //Date date2 = new Date();

        Calendar cal2 = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        //cal2.setTime(date2);


        return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
    }

    @Override
    public String determinePersonMajority(long age) {
        String majority;
        if(age>=18){
            majority="Adulte";
        }else {
            majority="Enfant";
        }

        return majority;
    }

    @Override
    public boolean deleteMedicalRecord(String firstName, String lastName) {
        String medicalRecord = selectMedicalRecord(firstName, lastName);
        boolean result=false;
        if(medicalRecord!=null){
            for (Object o : jsonArray) {
                if (Objects.equals(o.toString(), medicalRecord)) {
                    jsonArray.remove(o);
                    result=true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public void updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        boolean result=false;
        if(deleteMedicalRecord(firstName, lastName)){
            // Le nom et le prénom ne doivent pas changer
            MedicalRecord medicalRecord1 = new MedicalRecord(firstName,lastName,medicalRecord.getBirthdate(),medicalRecord.getMedications(),medicalRecord.getAllergies());
            insertMedicalRecord(medicalRecord1);
            result=true;
        }
    }
}
