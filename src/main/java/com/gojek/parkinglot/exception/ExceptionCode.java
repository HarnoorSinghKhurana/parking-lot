package com.gojek.parkinglot.exception;

public enum ExceptionCode {

    PARKING_ALREADY_EXIST("Parking Exists already!"),
    INVALID_REQUEST("Invalid Request");

    private String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
