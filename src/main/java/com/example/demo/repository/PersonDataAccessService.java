package com.example.demo.repository;


import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

@Repository("personData")
public class PersonDataAccessService implements PersonDao {

    private static final JSONParser jsonParser = new JSONParser();
    private static JSONArray jsonArray = new JSONArray();
    private static final MedicalRecordDataAccessService medicalRecordDataAccessService = new MedicalRecordDataAccessService();
    private static final FirestationDataAccessService firestationDataAccessService = new FirestationDataAccessService();

    static {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/java/com/example/demo/datasource/data.json"));
            jsonArray = (JSONArray) jsonObject.get("persons");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] selectAllPeople() {
        String[] bdd = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            bdd[i]= jsonArray.get(i).toString();
        }
        return bdd;
    }

    @Override
    public String selectPersonByName(String firstName, String lastName) {
        String result = null;
        for (Object o : jsonArray) {
            if (o.toString().contains(firstName) && o.toString().contains(lastName)) {
                result = o.toString();
                break;
            }
        }
        return result;
    }

    @Override
    public ArrayList<String> selectPersonInfoByName(String firstName, String lastName) throws java.text.ParseException {
        ArrayList<String> person = new ArrayList<>();
        ArrayList<String> personsWithSameName = new ArrayList<>();
        JSONObject jsonObject;
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("firstName").equals(firstName) && jsonObject.get("lastName").equals(lastName)) {
                person.add(jsonObject.get("firstName").toString() + " "+ jsonObject.get("lastName").toString().toUpperCase(Locale.ROOT) + " / " + jsonObject.get("address").toString() + " / " + medicalRecordDataAccessService.calculatePersonAge(medicalRecordDataAccessService.selectPersonBirthdate(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString())) + " years old / " + jsonObject.get("email").toString() + " / " + medicalRecordDataAccessService.selectMedicalBackground(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString()));
            }else if (jsonObject.get("lastName").equals(lastName) && !jsonObject.get("firstName").equals(firstName)){
                personsWithSameName.add(jsonObject.get("firstName").toString() + " "+ jsonObject.get("lastName").toString().toUpperCase(Locale.ROOT) + " / " + jsonObject.get("address").toString() + " / " + medicalRecordDataAccessService.calculatePersonAge(medicalRecordDataAccessService.selectPersonBirthdate(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString())) + " years old / " + jsonObject.get("email").toString() + " / " + medicalRecordDataAccessService.selectMedicalBackground(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString()));
            }
        }
        person.addAll(personsWithSameName);
        return person;
    }

    @Override
    public ArrayList<String> selectPersonsByFirestationAddress(ArrayList<String> address) throws java.text.ParseException {
        JSONObject jsonObject;
        ArrayList<String> persons = new ArrayList<>();
        String majority;
        int nbAdults=0;
        int nbChilds=0;
        for (String s : address) {
            for (Object o : jsonArray) {
                jsonObject = (JSONObject) o;
                if (jsonObject.get("address").equals(s)) {
                    majority = medicalRecordDataAccessService.determinePersonMajority(medicalRecordDataAccessService.calculatePersonAge(medicalRecordDataAccessService.selectPersonBirthdate(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString())));
                    persons.add(jsonObject.get("firstName").toString() + " " + jsonObject.get("lastName").toString().toUpperCase(Locale.ROOT) + " / " + jsonObject.get("address").toString() + " / " + jsonObject.get("phone").toString() + " / " + majority);
                    // Décompte du nombre d'adultes et d'enfants
                    if (majority.equals("Adulte")){
                        nbAdults++;
                    }else {
                        nbChilds++;
                    }
                }
            }
        }
        String count = "Total: "+nbAdults+" adult(s) & "+nbChilds+" kid(s).";
        persons.add(count);
        return persons;
    }

    @Override
    public ArrayList<String> selectPersonsInfoByFirestationAddress(ArrayList<String> address) throws java.text.ParseException {
        JSONObject jsonObject;
        ArrayList<String> persons = new ArrayList<>();
        for (String s : address) {
            persons.add("stationNumber: " + firestationDataAccessService.selectStationNumberByAddress(s));
            for (Object o : jsonArray) {
                jsonObject = (JSONObject) o;
                if (jsonObject.get("address").equals(s)) {
                    persons.add(jsonObject.get("firstName").toString() + " " + jsonObject.get("lastName").toString().toUpperCase(Locale.ROOT) + " / " + jsonObject.get("phone").toString() + " / " + medicalRecordDataAccessService.calculatePersonAge(medicalRecordDataAccessService.selectPersonBirthdate(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString())) + " years old / " + jsonObject.get("address").toString() + " / " + medicalRecordDataAccessService.selectMedicalBackground(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString()));

                }
            }
        }

        return persons;
    }

    @Override
    public ArrayList<String> selectPersonsByOneFirestationAddress(String address) throws java.text.ParseException {
        JSONObject jsonObject;
        ArrayList<String> persons = new ArrayList<>();
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("address").equals(address)) {
                persons.add(jsonObject.get("firstName").toString() + " " + jsonObject.get("lastName").toString().toUpperCase(Locale.ROOT) + " / " + jsonObject.get("phone").toString() + " / " + medicalRecordDataAccessService.calculatePersonAge(medicalRecordDataAccessService.selectPersonBirthdate(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString()))+" ans" + " / " + medicalRecordDataAccessService.selectMedicalBackground(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString()));
            }
        }
        return persons;
    }

    @Override
    public ArrayList<String> selectPhonesByFirestationAddress(ArrayList<String> address){
        JSONObject jsonObject;
        ArrayList<String> phones = new ArrayList<>();
        for (String s : address) {
            for (Object o : jsonArray) {
                jsonObject = (JSONObject) o;
                if (jsonObject.get("address").equals(s)) {
                    phones.add(jsonObject.get("firstName").toString()+" "+jsonObject.get("lastName").toString()+" / "+jsonObject.get("phone").toString());
                }
            }
        }
        return phones;
    }

    @Override
    public ArrayList<String> selectEmailByCity(String city) {
        JSONObject jsonObject;
        ArrayList<String> emails = new ArrayList<>();
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("city").equals(city)) {
                emails.add(jsonObject.get("firstName").toString()+" "+jsonObject.get("lastName").toString()+" / "+jsonObject.get("email").toString());
            }
        }
        return emails;
    }

    @Override
    public ArrayList<String> selectChildrenByAddress(String address) throws java.text.ParseException {
        JSONObject jsonObject;
        ArrayList<String> children = new ArrayList<>();
        ArrayList<String> otherMembers = new ArrayList<>();
        long age;
        String member;
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (jsonObject.get("address").equals(address)) {
                age = medicalRecordDataAccessService.calculatePersonAge(medicalRecordDataAccessService.selectPersonBirthdate(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString()));
                member = jsonObject.get("firstName").toString()+" "+jsonObject.get("lastName").toString().toUpperCase(Locale.ROOT)+" / "+age+" ans";
                if (age<=18){
                    children.add(member);
                }else{
                    otherMembers.add(member);
                }
            }
        }
        //fusion des deux tableaux
        children.add("");
        children.addAll(otherMembers);
        return children;
    }


    @Override
    public boolean deletePersonByName(String firstName, String lastName) {
        String person = selectPersonByName(firstName, lastName);
        boolean result=false;
        if(person!=null){
            for (Object o : jsonArray) {
                if (Objects.equals(o.toString(), person)) {
                    jsonArray.remove(o);
                    result=true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insertPerson(Person person) {
        JSONObject jsonO = new JSONObject();
        jsonO.put("zip", person.getZip());
        jsonO.put("firstName", person.getFirstName());
        jsonO.put("lastName", person.getLastName());
        jsonO.put("address", person.getAddress());
        jsonO.put("city", person.getCity());
        jsonO.put("phone", person.getPhone());
        jsonO.put("email", person.getEmail());
        jsonArray.add(jsonO);
    }

    @Override
    public void updatePersonByName(String firstName, String lastName, Person person) {
        if(deletePersonByName(firstName, lastName)){
            // Le nom et le prénom ne doivent pas changer
            Person person1 = new Person(firstName,lastName,person.getAddress(),person.getCity(),person.getZip(),person.getPhone(),person.getEmail());
            insertPerson(person1);
        }
    }

}
