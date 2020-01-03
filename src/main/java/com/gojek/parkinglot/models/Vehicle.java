package com.gojek.parkinglot.models;

import java.util.Random;

public abstract class Vehicle {

    private long id;
    private String registrationNumber;
    private Color color;

    public Vehicle(String registrationNumber, Color color) {
        this.id = new Random().nextLong();
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
