package com.ocr.safetynet.repository;


import com.ocr.safetynet.dao.PersonDao;
import com.ocr.safetynet.model.MedicalRecord;
import com.ocr.safetynet.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Repository("personData")
public class PersonDataAccessService implements PersonDao {

    private static final JSONParser jsonParser = new JSONParser();
    private static MedicalRecordDataAccessService medicalRecordDAS = new MedicalRecordDataAccessService();
    private static final FirestationDataAccessService firestationDAS = new FirestationDataAccessService();
    private static ArrayList<Person> persons = new ArrayList<>();
    
    private static long age = 0;

    static {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/java/com/ocr/safetynet/datasource/data.json"));
            JSONArray jsonPersons = (JSONArray) jsonObject.get("persons");
            for (Object o : jsonPersons) {
                jsonObject = (JSONObject) o;
                Person person = new Person(jsonObject.get("firstName").toString(),jsonObject.get("lastName").toString(),jsonObject.get("address").toString(),jsonObject.get("city").toString(),jsonObject.get("zip").toString(),jsonObject.get("phone").toString(),jsonObject.get("email").toString());
                persons.add(person);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPersons(ArrayList<Person> personArrayList){
        persons = personArrayList;
    }

    @Override
    public void addMedicalRecordDAS(MedicalRecordDataAccessService medicalRecordDataAccessService){
        medicalRecordDAS = medicalRecordDataAccessService;
    }

    @Override
    public ArrayList<Person> selectAllPersons(){
        return persons;
    }

    @Override
    public Person selectPerson(String firstName, String lastName) {
        Person person = null;
        for (Person value : selectAllPersons()) {
            if (value.getFirstName().equals(firstName) && value.getLastName().equals(lastName)) {
                person = value;
                break;
            }
        }
        return person;
    }

    @Override
    public ArrayList<String> selectPersonInfo(String firstName, String lastName){
        ArrayList<String> personList = new ArrayList<>();
        Person person = selectPerson(firstName, lastName);
        age = medicalRecordDAS.calculateAge(medicalRecordDAS.selectBirthdate(person.getFirstName(), person.getLastName()));
        personList.add("firstName: " + person.getFirstName());
        personList.add("lastName: " + person.getLastName());
        personList.add("address: " + person.getAddress());
        personList.add("age: " + age);
        personList.add("email: " + person.getEmail());
        personList.add("medications: " + medicalRecordDAS.selectMedicalRecord(firstName, lastName).getMedications().toString());
        personList.add("allergies: " + medicalRecordDAS.selectMedicalRecord(firstName, lastName).getAllergies().toString());
        personList.add("");
        //we display people with the same lastname
        for (Person value : selectAllPersons()) {
            if (value.getLastName().equals(lastName) && !value.getFirstName().equals(firstName)) {
                age = medicalRecordDAS.calculateAge(medicalRecordDAS.selectBirthdate(value.getFirstName(), value.getLastName()));
                personList.add("firstName: " + value.getFirstName());
                personList.add("lastName: " + value.getLastName());
                personList.add("address: " + value.getAddress());
                personList.add("age: " + age);
                personList.add("email: " + value.getEmail());
                personList.add("medications: " + medicalRecordDAS.selectMedicalRecord(value.getFirstName(), value.getLastName()).getMedications().toString());
                personList.add("allergies: " + medicalRecordDAS.selectMedicalRecord(value.getFirstName(), value.getLastName()).getAllergies().toString());
                personList.add("");
            }
        }
        return personList;
    }

    @Override
    public ArrayList<String> selectPersonsByFirestationAddress(ArrayList<String> address){
        ArrayList<String> personList = new ArrayList<>();
        int numberOfAdults = 0;
        int numberOfChildren = 0;
        for (String s : address) {
            for (Person value : selectAllPersons()) {
                if (value.getAddress().equals(s)) {
                    age = medicalRecordDAS.calculateAge(medicalRecordDAS.selectBirthdate(value.getFirstName(), value.getLastName()));
                    // count of the number of adults and children
                    if(age>=18){
                        numberOfAdults++;
                    }else {
                        numberOfChildren++;
                    }
                    personList.add("firstName: "+value.getFirstName());
                    personList.add("lastName: "+value.getLastName());
                    personList.add("address: "+value.getAddress());
                    personList.add("phone: "+value.getPhone());
                    personList.add("age: "+ age + " years old");
                    personList.add("");
                }
            }
        }
        personList.add("Total (" + (numberOfAdults+numberOfChildren) + " persons): " + numberOfAdults + " adults and " + numberOfChildren + " children");
        return personList;
    }

    @Override
    public ArrayList<String> selectPersonsInfoByFirestationAddress(ArrayList<String> address){
        ArrayList<String> personList = new ArrayList<>();
        ArrayList<String> medicalBackground;
        for (String s : address) {
            personList.add("stationNumber: " + firestationDAS.selectStationNumberByAddress(s));
            personList.add("");
            for (Person value : selectAllPersons()) {
                age = medicalRecordDAS.calculateAge(medicalRecordDAS.selectBirthdate(value.getFirstName(), value.getLastName()));
                medicalBackground = medicalRecordDAS.selectMedicalBackground(value.getFirstName(), value.getLastName());
                if (value.getAddress().equals(s)) {
                    personList.add("firstName: " + value.getFirstName());
                    personList.add("lastName: " + value.getLastName());
                    personList.add("phone: " + value.getPhone());
                    personList.add("age: "+ age + " years old");
                    personList.add("medications: " + medicalBackground.get(0));
                    personList.add("allergies: " + medicalBackground.get(1));
                    personList.add("");
                }
            }
        }
        return personList;
    }

    @Override
    public ArrayList<String> selectPersonsByOneFirestationAddress(String address){
        ArrayList<String> personList = new ArrayList<>();
        for (Person value : selectAllPersons()) {
            age = medicalRecordDAS.calculateAge(medicalRecordDAS.selectBirthdate(value.getFirstName(), value.getLastName()));
            if (value.getAddress().equals(address)) {
                personList.add("firstName: "+value.getFirstName());
                personList.add("lastName: "+value.getLastName());
                personList.add("phone: "+value.getPhone());
                personList.add("age: "+ age + " years old");
                personList.add("");
            }
        }
        return personList;
    }

    @Override
    public ArrayList<String> selectPhonesPersonsByFirestationAddress(ArrayList<String> address){
        ArrayList<String> personList = new ArrayList<>();
        for (String s : address) {
            for (Person value : selectAllPersons()) {
                if (value.getAddress().equals(s)) {
                    personList.add("firstName: "+value.getFirstName());
                    personList.add("lastName: "+value.getLastName());
                    personList.add("phoneNumber: "+value.getPhone());
                    personList.add("");
                }
            }
        }
        return personList;
    }

    @Override
    public ArrayList<String> selectEmailsPersonsByCity(String city) {
        ArrayList<String> personList = new ArrayList<>();
        for (Person value : selectAllPersons()) {
            if (value.getCity().equals(city)) {
                personList.add("firstName: "+value.getFirstName());
                personList.add("lastName: "+value.getLastName());
                personList.add("email: "+value.getEmail());
                personList.add("");
            }
        }
        return personList;
    }

    @Override
    public ArrayList<String> selectChildrenByAddress(String address){
        ArrayList<String> children = new ArrayList<>();
        children.add("Children:");
        children.add("");
        ArrayList<String> adults = new ArrayList<>();
        for (Person value : selectAllPersons()) {
            if (value.getAddress().equals(address)) {
                age = medicalRecordDAS.calculateAge(medicalRecordDAS.selectBirthdate(value.getFirstName(), value.getLastName()));
                if(age<18){
                    children.add("firstName: "+value.getFirstName());
                    children.add("lastName: "+value.getLastName());
                    children.add("age: "+ age + " years old");
                    children.add("");
                }else {
                    adults.add("firstName: "+value.getFirstName());
                    adults.add("lastName: "+value.getLastName());
                    adults.add("age: "+ age + " years old");
                    adults.add("");
                }
            }
        }
        // merging the children and adult lists
        children.add("Adults:");
        children.add("");
        children.addAll(adults);
        return children;
    }

    @Override
    public boolean deletePerson(String firstName, String lastName) {
        boolean result = false;
        Person person = selectPerson(firstName, lastName);
        if (person != null){
            persons.remove(person);
            result = true;
        }
        return result;
    }

    @Override
    public void insertPerson(Person person) {
        persons.add(person);
    }

    @Override
    public void updatePerson(String firstName, String lastName, Person person) {
        if(deletePerson(firstName, lastName)){
            // firstname and lastname do not change
            Person personModified = new Person(firstName,lastName,person.getAddress(),person.getCity(),person.getZip(),person.getPhone(),person.getEmail());
            insertPerson(personModified);
        }
    }

}
