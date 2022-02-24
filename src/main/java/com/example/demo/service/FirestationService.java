package com.example.demo.service;

import com.example.demo.dao.FirestationDao;
import com.example.demo.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;

@Service
public class FirestationService {

    private final FirestationDao firestationDao;
    private final PersonService personService;

    @Autowired
    public FirestationService(@Qualifier("firestationData") FirestationDao firestationDao, PersonService personService) {
        this.firestationDao = firestationDao;
        this.personService = personService;
    }

    public void addFirestation(Firestation firestation){
        firestationDao.insertFirestation(firestation);
    }

    public String[] getAllFirestations(){
        return firestationDao.selectAllFirestations();
    }

    public String getFirestation(String station, String address){
        return firestationDao.selectFirestation(station, address);
    }
    public ArrayList<String> getFirestationAddress(String station){
        return firestationDao.selectFirestationAddressByStationNumber(station);
    }

    public ArrayList<String> getPersonsByStationNumber(String station) throws ParseException {
        return personService.getPersonsByStationNumbers(getFirestationAddress(station));
    }
    public String getStationNumberByAddress(String address){
        return firestationDao.selectStationNumberByAddress(address);
    }

    public void deleteFirestation(String station, String address){
        firestationDao.deleteFirestation(station, address);
    }

    public void updateFirestation(String station, String address, Firestation firestation){
        firestationDao.updateFirestation(station, address, firestation);
    }

}
