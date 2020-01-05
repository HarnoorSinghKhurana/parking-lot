package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.models.Vehicle;

import java.util.List;

public interface StorageDao<T extends Vehicle> {

    void createParkingLot(Integer slots);

    Integer park(T vehicle);

    Integer leaveSlot(Integer slotNumber);

    List<String> status();

    List<String> getRegNumberForColor(String color);

    List<Integer> getSlotNumbersFromColor(String color);

    Integer getSlotNoFromRegistrationNo(String registrationNo);
}
