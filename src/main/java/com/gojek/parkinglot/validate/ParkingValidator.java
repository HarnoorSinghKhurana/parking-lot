package com.gojek.parkinglot.validate;

import com.gojek.parkinglot.storage.impl.VehicleStorage;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;

public class ParkingValidator implements BaseValidator {

    @Override
    public void validateParkingExists() {
        if (VehicleStorage.getInstance() == null) {
            throw new ParkingLotException(ExceptionCode.PARKING_DOES_NOT_EXIST, ExceptionCode.PARKING_DOES_NOT_EXIST.getMessage());
        }
    }

    @Override
    public void validateParkingNotExists() {
        if (VehicleStorage.getInstance() != null) {
            throw new ParkingLotException(ExceptionCode.PARKING_ALREADY_EXIST, ExceptionCode.PARKING_ALREADY_EXIST.getMessage());
        }
    }
}
