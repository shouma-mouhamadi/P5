package com.example.demo.dao;

import com.example.demo.model.Person;

import java.text.ParseException;
import java.util.ArrayList;

public interface PersonDao {

    void insertPerson(Person person);

    String[] selectAllPeople();

    String selectPersonByName(String firstName, String lastName);

    ArrayList<String> selectPersonInfoByName(String firstName, String lastName) throws ParseException;

    ArrayList<String> selectPersonsByFirestationAddress(ArrayList<String> address) throws ParseException;

    ArrayList<String> selectPersonsInfoByFirestationAddress(ArrayList<String> address) throws ParseException;

    ArrayList<String> selectPersonsByOneFirestationAddress(String address) throws ParseException;

    ArrayList<String> selectPhonesByFirestationAddress(ArrayList<String> address) throws ParseException;

    ArrayList<String> selectEmailByCity(String city);

    ArrayList<String> selectChildrenByAddress(String address) throws ParseException;

    boolean deletePersonByName(String firstName, String lastName);

    void updatePersonByName(String firstName, String lastName, Person person);

}
