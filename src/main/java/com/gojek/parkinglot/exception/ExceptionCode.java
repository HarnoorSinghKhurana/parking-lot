package com.gojek.parkinglot.exception;

public enum ExceptionCode {

    PARKING_ALREADY_EXIST("Parking Exists already"),
    PARKING_DOES_NOT_EXIST("Parking Does Not Exists"),
    PARKING_FULL("Sorry, parking lot is full"),
    INVALID_PARKING_SLOTS("Parking Slots Provided in request are not in proper format"),
    VEHICLE_ALREADY_EXIST("Sorry, vehicle is already parked."),
    SLOT_ALREADY_EMPTY("Slot is already empty"),
    COMPUTATION_ERROR("Unexpected Error Occurred"),
    INVALID_REQUEST("Unexpected Error exists in Request");

    private String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
