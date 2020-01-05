package com.gojek.parkinglot.dao.Impl;

import com.gojek.parkinglot.dao.StorageDao;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.strategy.NearestAllotStrategy;

import java.util.List;

public class StorageDaoImpl<T extends Vehicle> implements StorageDao<T> {

    private VehicleStorage storageStructure;

    @Override
    public void createParkingLot(Integer slots) {
        if (storageStructure != null)
            throw new ParkingLotException(ExceptionCode.PARKING_ALREADY_EXIST, ExceptionCode.PARKING_ALREADY_EXIST.getMessage());
        storageStructure = VehicleStorage.create(slots, slots, new NearestAllotStrategy());
    }

    @Override
    public Integer park(T vehicle) {
        if (storageStructure == null)
            throw new ParkingLotException(ExceptionCode.PARKING_DOES_NOT_EXIST, ExceptionCode.PARKING_DOES_NOT_EXIST.getMessage());
        return storageStructure.park(vehicle);
    }

    @Override
    public void leaveSlot(Integer slotNumber) {
        storageStructure.leaveSLot(slotNumber);
    }

    @Override
    public List<String>  status() {
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
