package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Firestation {
    @NotBlank
    private final String address;
    @NotBlank
    private final String station;

    public Firestation(@JsonProperty("address") String address, @JsonProperty("station") String station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public String getStation() {
        return station;
    }
}
