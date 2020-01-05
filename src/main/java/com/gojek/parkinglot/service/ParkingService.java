package com.gojek.parkinglot.service;

import com.gojek.parkinglot.models.Vehicle;

public interface ParkingService extends BaseService {

    void createParkingLot(Integer slots);

    void leaveSlot(Integer slot);

    void park(Vehicle vehicle);

    void getStatus();

    void getRegNumberForColor(String valueOf);

    void getSlotNumbersFromColor(String color);

    void getSlotNoFromRegistrationNo(String color);
}
