package com.ocr.safetynet.service;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public ArrayList<Firestation> getAllFirestations(){
        return firestationDao.selectAllFirestations();
    }

    public Firestation getFirestation(String station, String address){
        return firestationDao.selectFirestation(station, address);
    }
    public void getFirestationAddress(String station){
        firestationDao.selectFirestationAddressByStationNumber(station);
    }

    public ArrayList<String> getPersonsByStationNumber(String station){
        return personService.getPersonsByFirestationAddress(firestationDao.selectFirestationAddressByStationNumber(station));
    }


    public void getStationNumberByAddress(String address){
        firestationDao.selectStationNumberByAddress(address);
    }

    public void deleteFirestation(String station, String address){
        firestationDao.deleteFirestation(station, address);
    }

    public void updateFirestation(String station, String address, Firestation firestation){
        firestationDao.updateFirestation(station, address, firestation);
    }

}
