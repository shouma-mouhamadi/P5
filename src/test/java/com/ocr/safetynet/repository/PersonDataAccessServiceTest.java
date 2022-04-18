package com.ocr.safetynet.repository;

import com.ocr.safetynet.model.MedicalRecord;
import com.ocr.safetynet.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonDataAccessServiceTest {

    private static PersonDataAccessService personDataAccessService;
    private Person person;
    private ArrayList<String> addressList;
    private static ArrayList<Person> personArrayList;
    private static ArrayList<MedicalRecord> medicalRecordArrayList;
    public static MedicalRecordDataAccessService medicalRecordDataAccessService;


    @BeforeAll
    private static void setUp() {
        personDataAccessService = new PersonDataAccessService();
        personArrayList = new ArrayList<>();
        medicalRecordArrayList = new ArrayList<>();
        medicalRecordDataAccessService = new MedicalRecordDataAccessService();
    }

    @BeforeEach
    private void setUpPerTest() {
        person = new Person(
                "Shouma",
                "Mouhamadi",
                "rue inconnue",
                "Meaux",
                "77100",
                "0749800192",
                "shouma77100@gmail.com");

        addressList = new ArrayList<>() {{
            add(person.getAddress());
        }};
        personArrayList.add(person);
        personDataAccessService.addPersons(personArrayList);
    }

    @Test
    void selectAllPersonsTest() {
        personDataAccessService.selectAllPersons();
        assertEquals(personDataAccessService.selectAllPersons(), personArrayList);
    }

    @Test
    void selectPersonsByOneFirestationAddressTest() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "Shouma",
                "Mouhamadi",
                "21/03/1990",
                new ArrayList<>() {{add("Paxlovid");add("Xevudy");}},
                new ArrayList<>() {{add("pollens");}});
        medicalRecordArrayList.add(medicalRecord);
        medicalRecordDataAccessService.addMedicalRecords(medicalRecordArrayList);
        personDataAccessService.addMedicalRecordDAS(medicalRecordDataAccessService);

        personDataAccessService.selectPersonsByOneFirestationAddress(person.getAddress());
    }

    @Test
    void selectPersonsInfoByFirestationAddressTest() {
        personDataAccessService.selectPersonsInfoByFirestationAddress(addressList);
    }

    @Test
    void selectPersonsInfoTest(){
        Person person2 = new Person(
                "Bob",
                "Mouhamadi",
                "rue inconnue",
                "Meaux",
                "77100",
                "0749800192",
                "shouma77100@gmail.com");
        //personArrayList.add(person);
        personArrayList.add(person2);
        personDataAccessService.addPersons(personArrayList);

        MedicalRecord medicalRecord = new MedicalRecord(
                "Shouma",
                "Mouhamadi",
                "21/03/1990",
                new ArrayList<>() {{add("Paxlovid");add("Xevudy");}},
                new ArrayList<>() {{add("pollens");}});

        MedicalRecord medicalRecord2 = new MedicalRecord(
                "Bob",
                "Mouhamadi",
                "21/03/1990",
                new ArrayList<>() {{add("Paxlovid");add("Xevudy");}},
                new ArrayList<>() {{add("pollens");}});

        medicalRecordArrayList.add(medicalRecord);
        medicalRecordArrayList.add(medicalRecord2);
        medicalRecordDataAccessService.addMedicalRecords(medicalRecordArrayList);
        personDataAccessService.addMedicalRecordDAS(medicalRecordDataAccessService);

        personDataAccessService.selectPersonInfo(person.getFirstName(), person.getLastName());
    }

    @Test
    void selectPersonsByFirestationAddressTest() {
        personDataAccessService.selectPersonsByFirestationAddress(addressList);
    }

    @Test
    void selectPhonesByFirestationAddressTest() {
        personDataAccessService.selectPhonesPersonsByFirestationAddress(addressList);
    }

    @Test
    void selectEmailByCityTest() {
        personDataAccessService.selectEmailsPersonsByCity(person.getCity());
    }

    @Test
    void selectChildrenByAddressTest() {
        personDataAccessService.selectChildrenByAddress(person.getAddress());
    }

    @Test
    void insertPersonTest() {
        personDataAccessService.insertPerson(person);
    }

    @Test
    void updatePersonByNameTest() {
        personDataAccessService.updatePerson(person.getFirstName(), person.getLastName(), person);
    }

}