package com.example.demo.dao;

import com.example.demo.model.Firestation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Repository("firestationData")
public class FirestationDataAccessService implements FirestationDao{

    private static final JSONParser jsonParser = new JSONParser();
    private static JSONArray jsonArray = new JSONArray();

    static {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:/Users/Shouma/Desktop/SafetyNet-Alerts/src/main/java/com/example/demo/datasource/data.json"));
            jsonArray = (JSONArray) jsonObject.get("firestations");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insertFirestation(Firestation firestation) {
        boolean result=false;
        JSONObject jsonO = new JSONObject();
        jsonO.put("address", firestation.getAddress());
        jsonO.put("station", firestation.getStation());
        if(jsonArray.add(jsonO)){
            result=true;
        }
    }

    @Override
    public String[] selectAllFirestations() {
        String[] bdd = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            bdd[i]= jsonArray.get(i).toString();
        }
        return bdd;
    }

    @Override
    public String selectFirestation(String station, String address) {
        String result = null;
        for (Object o : jsonArray) {
            if (o.toString().contains(station) && o.toString().contains(address)) {
                result = o.toString();
                break;
            }
        }
        return result;
    }

    @Override
    public ArrayList<String> selectFirestationAddressByStationNumber(String station) {
        JSONObject jsonObject;
        ArrayList<String> address = new ArrayList<>();
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("station").equals(station)) {
                address.add(jsonObject.get("address").toString());
            }
        }
        return address;
    }

    @Override
    public ArrayList<String> selectFirestationAddressByStationNumbers(ArrayList<String> stations) {
        JSONObject jsonObject;
        ArrayList<String> address = new ArrayList<>();

        for (String station : stations) {
            for (Object o : jsonArray) {
                jsonObject = (JSONObject) o;
                if (jsonObject.get("station").equals(station)) {
                    address.add(jsonObject.get("address").toString());
                }
            }
        }

        return address;
    }

    @Override
    public String selectStationNumberByAddress(String address) {
        JSONObject jsonObject;
        String stationNumber = null;

        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("address").equals(address)) {
                stationNumber = jsonObject.get("station").toString();
            }
        }

        return stationNumber;
    }

    @Override
    public boolean deleteFirestation(String station, String address) {
        String firestation = selectFirestation(station, address);
        boolean result=false;
        if(firestation!=null){
            for (Object o : jsonArray) {
                if (Objects.equals(o.toString(), firestation)) {
                    jsonArray.remove(o);
                    result=true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public void updateFirestation(String station, String address, Firestation firestation) {
        boolean result=false;
        if(deleteFirestation(station, address)){
            insertFirestation(firestation);
            result=true;
        }
    }
}
