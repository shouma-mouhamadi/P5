package com.ocr.safetynet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

public class MedicalRecord {
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String birthdate;
    private final ArrayList<String> medications;
    private final ArrayList<String> allergies;

    public MedicalRecord(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("birthdate") String birthdate,
            @JsonProperty("medications") ArrayList<String> medications,
            @JsonProperty("allergies") ArrayList<String> allergies)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public ArrayList<String> getMedications() {
        return this.medications;
    }
    public ArrayList<String> getAllergies() {
        return this.allergies;
    }
}
