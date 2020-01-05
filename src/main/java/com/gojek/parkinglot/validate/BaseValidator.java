package com.gojek.parkinglot.validate;


public interface BaseValidator {

    public abstract void validateParkingExists();

    public abstract void validateParkingNotExists();
}
