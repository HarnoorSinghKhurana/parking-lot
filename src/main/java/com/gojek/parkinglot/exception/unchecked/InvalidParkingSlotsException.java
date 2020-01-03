package com.gojek.parkinglot.exception.unchecked;

import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;

public class InvalidParkingSlotsException extends ParkingLotException {

    public InvalidParkingSlotsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidParkingSlotsException(String message) {
        super(message);
    }

    public InvalidParkingSlotsException(ExceptionCode code, String message) {
        super(code, message);
    }
}
