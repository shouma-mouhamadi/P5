package com.example.demo.dao;

import com.example.demo.model.Firestation;

import java.util.ArrayList;

public interface FirestationDao {

    void insertFirestation(Firestation firestation);

    String[] selectAllFirestations();

    String selectFirestation(String station, String address);

    ArrayList<String> selectFirestationAddressByStationNumber(String station);

    ArrayList<String> selectFirestationAddressByStationNumbers(ArrayList<String> stations);

    String selectStationNumberByAddress(String address);

    boolean deleteFirestation(String station, String address);

    void updateFirestation(String station, String address, Firestation firestation);
}
