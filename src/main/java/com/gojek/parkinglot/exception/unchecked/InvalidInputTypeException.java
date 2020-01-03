package com.gojek.parkinglot.exception.unchecked;

import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;

public class InvalidInputTypeException extends ParkingLotException {

    public InvalidInputTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidInputTypeException(ExceptionCode code, String message) {
        super(code, message);
    }

    public InvalidInputTypeException(String message) {
        super(message);
    }
}
