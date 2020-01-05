package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.StorageDao;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.storage.impl.VehicleStorage;
import com.gojek.parkinglot.strategy.NearestAllotStrategy;

import java.util.List;

public class StorageDaoImpl<T extends Vehicle> implements StorageDao<T> {

    private VehicleStorage storageStructure;

    @Override
    public void createParkingLot(Integer slots) {
        storageStructure = VehicleStorage.init(slots, slots, new NearestAllotStrategy());
    }

    @Override
    public Integer park(T vehicle) {
        return storageStructure.park(vehicle);
    }

    @Override
    public Integer leaveSlot(Integer slotNumber) {
        return storageStructure.leaveSlot(slotNumber);
    }

    @Override
    public List<String> status() {
        return storageStructure.getStatus();
    }

    @Override
    public List<String> getRegNumberForColor(String color) {
        return storageStructure.getRegNumberForColor(color);
    }

    @Override
    public List<Integer> getSlotNumbersFromColor(String color) {
        return storageStructure.getSlotNumbersFromColor(color);
    }

    @Override
    public Integer getSlotNoFromRegistrationNo(String registrationNo) {
        return storageStructure.getSlotNoFromRegistrationNo(registrationNo);
    }
}
