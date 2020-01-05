package com.gojek.parkinglot.service;

import com.gojek.parkinglot.models.Vehicle;

public interface StorageService extends BaseService{
    void createParkingLot(Integer slots);

    void park(Vehicle vehicle);

    void leaveSlot(Integer slotNumber);

    void getStatus();

    void getRegNumberForColor(String color);

    void getSlotNumbersFromColor(String color);

    void getSlotNoFromRegistrationNo(String registrationNo);
}
