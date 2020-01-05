package com.gojek.parkinglot.models;

import java.util.Objects;
import java.util.Random;

public abstract class Vehicle {

    private long id;
    private String registrationNumber;
    private String color;

    public Vehicle(String registrationNumber, String color) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getId() == vehicle.getId() &&
                Objects.equals(getRegistrationNumber(), vehicle.getRegistrationNumber()) &&
                getColor() == vehicle.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRegistrationNumber(), getColor());
    }
}
