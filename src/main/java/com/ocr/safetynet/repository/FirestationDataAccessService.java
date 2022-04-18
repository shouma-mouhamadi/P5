package com.ocr.safetynet.repository;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.model.Firestation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Repository("firestationData")
public class FirestationDataAccessService implements FirestationDao {

    private static final JSONParser jsonParser = new JSONParser();
    private static final ArrayList<Firestation> firestations = new ArrayList<>();

    static {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/java/com/ocr/safetynet/datasource/data.json"));
            JSONArray jsonFirestations = (JSONArray) jsonObject.get("firestations");
            for (Object o : jsonFirestations) {
                jsonObject = (JSONObject) o;
                Firestation firestation = new Firestation(jsonObject.get("address").toString(),jsonObject.get("station").toString());
                firestations.add(firestation);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertFirestation(Firestation firestation)  {
        firestations.add(firestation);
    }

    @Override
    public ArrayList<Firestation> selectAllFirestations() {
        return firestations;
    }

    @Override
    public Firestation selectFirestation(String station, String address) {
        Firestation firestation = null;
        for (Firestation value : selectAllFirestations()) {
            if (value.getStation().equals(station) && value.getAddress().equals(address)) {
                firestation = value;
                break;
            }
        }
        return firestation;
    }

    @Override
    public ArrayList<String> selectFirestationAddressByStationNumber(String station) {
        ArrayList<String> address = new ArrayList<>();
        for (Firestation value : selectAllFirestations()) {
            if (value.getStation().equals(station)) {
                address.add(value.getAddress());
            }
        }
        return address;
    }

    @Override
    public ArrayList<String> selectFirestationAddressByStationNumbers(ArrayList<String> stations) {
        ArrayList<String> addressList = new ArrayList<>();
        for (String station : stations) {
            addressList.addAll(selectFirestationAddressByStationNumber(station));
        }
        return addressList;
    }
    
    @Override
    public String selectStationNumberByAddress(String address) {
        String station = null;
        for (Firestation value : selectAllFirestations()) {
            if (value.getAddress().equals(address)) {
                station = value.getStation();
                break;
            }
        }
        return station;
    }

    @Override
    public boolean deleteFirestation(String station, String address) {
        boolean result = false;
        Firestation firestation = selectFirestation(station, address);
        if (firestation != null){
            firestations.remove(firestation);
            result = true;
        }
        return result;
    }

    @Override
    public void updateFirestation(String station, String address, Firestation firestation) {
        if (deleteFirestation(station, address)){
            insertFirestation(firestation);
        }
    }
}
