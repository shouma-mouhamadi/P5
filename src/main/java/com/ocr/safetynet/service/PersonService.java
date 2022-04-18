package com.ocr.safetynet.service;

import com.ocr.safetynet.dao.FirestationDao;
import com.ocr.safetynet.dao.PersonDao;
import com.ocr.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonService {

    private final PersonDao personDao;
    private final FirestationDao firestationDao;

    @Autowired
    public PersonService(@Qualifier("personData") PersonDao personDao, FirestationDao firestationDao) {
        this.personDao = personDao;
        this.firestationDao = firestationDao;
    }

    public void addPerson(Person person){
        personDao.insertPerson(person);
    }

     public ArrayList<Person> getAllPersons(){
        return personDao.selectAllPersons();
     }

     public Person getPersonByFirstnameAndLastname(String firstName, String lastName){
        return personDao.selectPerson(firstName, lastName);
     }

     public ArrayList<String> getPersonInfoByName(String firstName, String lastName){
        return personDao.selectPersonInfo(firstName, lastName);
     }

    public ArrayList<String> getPersonsByFirestationAddress(ArrayList<String> address){
        return personDao.selectPersonsByFirestationAddress(address);
    }

     public ArrayList<String> getPersonsByStationNumbers(ArrayList<String> stations){
        return personDao.selectPersonsInfoByFirestationAddress(firestationDao.selectFirestationAddressByStationNumbers(stations));
     }

     public void getPersonsByStationNumber(String station){
         personDao.selectPersonsByOneFirestationAddress(firestationDao.selectFirestationAddressByStationNumber(station).get(0));
     }

     public ArrayList<String> getPersonsByAddress(String address){
        ArrayList<String> personList;
        ArrayList<String> finalList = new ArrayList<>();
        String stationNumber;
        personList=personDao.selectPersonsByOneFirestationAddress(address);
        stationNumber=firestationDao.selectStationNumberByAddress(address);
        finalList.add("stationNumber "+stationNumber+":");
        finalList.add("");
        finalList.addAll(personList);
        return finalList;
     }

     public ArrayList<String> getPhonesByAddress(String station){
        return personDao.selectPhonesPersonsByFirestationAddress(firestationDao.selectFirestationAddressByStationNumber(station));
     }

     public ArrayList<String> getEmailsByCity(String city){
        return personDao.selectEmailsPersonsByCity(city);
     }

     public  ArrayList<String> getChildrenByAddress(String address){
        return personDao.selectChildrenByAddress(address);
     }

     public void deletePerson(String firstName, String lastName){
         personDao.deletePerson(firstName, lastName);
     }

     public void updatePerson(String firstName, String lastName, Person person){
         personDao.updatePerson(firstName, lastName, person);
     }

}
