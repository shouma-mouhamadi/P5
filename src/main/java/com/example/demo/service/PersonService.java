package com.example.demo.service;

import com.example.demo.dao.FirestationDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

     public String[] getAllPeople(){
        return personDao.selectAllPeople();
     }

     public String getPersonByName(String firstName, String lastName){
        return personDao.selectPersonByName(firstName, lastName);
     }
     public ArrayList<String> getPersonInfoByName(String firstName, String lastName) throws ParseException {
        return personDao.selectPersonInfoByName(firstName, lastName);
     }

     public ArrayList<String> getPersonsByStationNumbers(ArrayList<String> stations) throws ParseException {
        return personDao.selectPersonsInfoByFirestationAddress(firestationDao.selectFirestationAddressByStationNumbers(stations));
     }

     public ArrayList<String> getPersonsByStationNumber(String station) throws ParseException {
        return personDao.selectPersonsByFirestationAddress(firestationDao.selectFirestationAddressByStationNumber(station));
     }

     public ArrayList<String> getPersonsByAddress(String address) throws ParseException {
        ArrayList<String> personList;
        ArrayList<String> finalList = new ArrayList<>();
        String stationNumber;
        personList=personDao.selectPersonsByFirestationAddress(address);
        stationNumber=firestationDao.selectStationNumberByAddress(address);
        finalList.add("stationNumber: "+stationNumber);
        finalList.addAll(personList);
        return finalList;
     }

     public ArrayList<String> getPhonesByAddress(String station) throws ParseException {
        return personDao.selectPhonesByFirestationAddress(firestationDao.selectFirestationAddressByStationNumber(station));
     }

     public ArrayList<String> getEmailsByCity(String city){
        return personDao.selectEmailByCity(city);
     }

     public  ArrayList<String> getChildrenByAddress(String address) throws ParseException {
        return personDao.selectChildrenByAddress(address);
     }

     public void deletePerson(String firstName, String lastName){
         personDao.deletePersonByName(firstName, lastName);
     }

     public void updatePerson(String firstName, String lastName, Person person){
         personDao.updatePersonByName(firstName, lastName, person);
     }

}
