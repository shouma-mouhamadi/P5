package com.ocr.safetynet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class Person {
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String address;
    @NotBlank
    private final String city;
    @NotBlank
    private final String zip;
    @NotBlank
    private final String phone;
    @NotBlank
    private final String email;

    public Person(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("address") String address,
            @JsonProperty("city") String city,
            @JsonProperty("zip") String zip,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getZip() {
        return zip;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
}
