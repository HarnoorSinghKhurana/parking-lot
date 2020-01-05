package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.service.ParkingService;
import com.gojek.parkinglot.service.StorageService;
import com.gojek.parkinglot.validate.BaseValidator;
import com.gojek.parkinglot.validate.ParkingValidator;

public class ParkingServiceImpl implements ParkingService {

    private StorageService storageService;

    private BaseValidator validator;

    public ParkingServiceImpl(StorageService storageService, BaseValidator validator) {
        this.storageService = storageService;
        this.validator = new ParkingValidator();
    }

    @Override
    public void createParkingLot(Integer slots) {
        validator.validateParkingNotExists();
        storageService.createParkingLot(slots);
    }

    @Override
    public void leaveSlot(Integer slot) {
        validator.validateParkingExists();
        storageService.leaveSlot(slot);
    }

    @Override
    public void park(Vehicle vehicle) {
        validator.validateParkingExists();
        storageService.park(vehicle);
    }

    @Override
    public void getStatus() {
        validator.validateParkingExists();
        storageService.getStatus();
    }

    @Override
    public void getRegNumberForColor(String color) {
        validator.validateParkingExists();
        storageService.getRegNumberForColor(color);
    }

    @Override
    public void getSlotNumbersFromColor(String color) {
        validator.validateParkingExists();
        storageService.getSlotNumbersFromColor(color);
    }

    @Override
    public void getSlotNoFromRegistrationNo(String color) {
        validator.validateParkingExists();
        storageService.getSlotNoFromRegistrationNo(color);
    }
}
