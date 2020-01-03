package com.gojek.parkinglot.exception;

public enum ExceptionCode {

    PARKING_ALREADY_EXIST("Parking Exists already!"),
    INVALID_PARKING_SLOTS("Parking Slots Provided in request are not in proper format"),
    INVALID_REQUEST("Unexpected Error exists in Request");

    private String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
