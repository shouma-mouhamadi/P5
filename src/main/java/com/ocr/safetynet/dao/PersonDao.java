package com.ocr.safetynet.dao;

import com.ocr.safetynet.model.Person;
import com.ocr.safetynet.repository.MedicalRecordDataAccessService;

import java.util.ArrayList;

public interface PersonDao {

    void addPersons(ArrayList<Person> personArrayList);

    void addMedicalRecordDAS(MedicalRecordDataAccessService medicalRecordDataAccessService);

    void insertPerson(Person person);

    ArrayList<Person> selectAllPersons();

    Person selectPerson(String firstName, String lastName);

    ArrayList<String> selectPersonInfo(String firstName, String lastName);

    ArrayList<String> selectPersonsByFirestationAddress(ArrayList<String> address);

    ArrayList<String> selectPersonsInfoByFirestationAddress(ArrayList<String> address);

    ArrayList<String> selectPersonsByOneFirestationAddress(String address);

    ArrayList<String> selectPhonesPersonsByFirestationAddress(ArrayList<String> address);

    ArrayList<String> selectEmailsPersonsByCity(String city);

    ArrayList<String> selectChildrenByAddress(String address);

    boolean deletePerson(String firstName, String lastName);

    void updatePerson(String firstName, String lastName, Person person);

}
