package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.models.Vehicle;

import java.util.List;

public interface StorageStructure<T extends Vehicle> {


    Integer park(T vehicle);

    void leaveSLot(Integer slot);

    List<String> getStatus();

    List<String> getRegNumberForColor(String color);

    List<Integer> getSlotNumbersFromColor(String colour);

    Integer getSlotNoFromRegistrationNo(String registrationNo);
}
