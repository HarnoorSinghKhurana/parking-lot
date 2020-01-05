package com.gojek.parkinglot.dao.Impl;

import com.gojek.parkinglot.dao.StorageStructure;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.strategy.BaseStrategy;
import com.gojek.parkinglot.strategy.NearestAllotStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleStorage<T extends Vehicle> implements StorageStructure<T> {

    private Integer capacity;
    private Integer available;
    private BaseStrategy parkingStrategy;
    private Map<Integer, T> slotVehicleMap;

    private VehicleStorage(Integer capacity, Integer available, BaseStrategy parkingStrategy) {
        this.capacity = capacity;
        this.available = available;
        this.parkingStrategy = parkingStrategy;
        this.slotVehicleMap = new HashMap<>();
        if (parkingStrategy == null) {
            this.parkingStrategy = new NearestAllotStrategy();
        }
        for (int i = 1; i <= capacity; i++) {
            slotVehicleMap.put(i, null);
            parkingStrategy.addSlot(i);
        }
    }

    private static VehicleStorage instance = null;


    static <T extends Vehicle> VehicleStorage<T> create(Integer capacity, Integer available, BaseStrategy parkingStrategy) {
        if (VehicleStorage.instance == null) {
            synchronized (StorageStructure.class) {
                if (VehicleStorage.instance == null) {
                    VehicleStorage.instance = new VehicleStorage(capacity, available, parkingStrategy);
                }
            }
        }
        return VehicleStorage.instance;
    }

    @Override
    public Integer park(T vehicle) {
        if (this.available == 0) {
            throw new ParkingLotException(ExceptionCode.PARKING_FULL, ExceptionCode.PARKING_FULL.getMessage());
        }
        Integer allocatedSlot = this.parkingStrategy.getSlot();
        this.parkingStrategy.removeSlot(allocatedSlot);
        if (this.slotVehicleMap.containsValue(vehicle)) {
            throw new ParkingLotException(ExceptionCode.VEHICLE_ALREADY_EXIST, ExceptionCode.VEHICLE_ALREADY_EXIST.getMessage());
        }
        slotVehicleMap.put(allocatedSlot, vehicle);
        available--;
        return allocatedSlot;

    }

    @Override
    public void leaveSLot(Integer slot) {
        if (slotVehicleMap.get(slot) == null) {
            throw new ParkingLotException(ExceptionCode.SLOT_ALREADY_EMPTY, ExceptionCode.SLOT_ALREADY_EMPTY.getMessage());
        }
        available++;
        parkingStrategy.addSlot(slot);
        slotVehicleMap.put(slot, null);
    }

    @Override
    public List<String> getStatus() {
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle) {
                statusList.add(i + "\t\t" + vehicle.getRegistrationNumber() + "\t\t" + vehicle.getColor());
            }
        }
        return statusList;
    }

    @Override
    public List<String> getRegNumberForColor(String color) {
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle && vehicle.getColor().equalsIgnoreCase(color)) {
                statusList.add(vehicle.getRegistrationNumber());
            }
        }
        return statusList;
    }

    @Override
    public List<Integer> getSlotNumbersFromColor(String color) {
        List<Integer> slotList = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle && vehicle.getColor().equalsIgnoreCase(color)) {
                slotList.add(i);
            }
        }
        return slotList;
    }

    @Override
    public Integer getSlotNoFromRegistrationNo(String registrationNo) {
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= capacity; i++) {
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle && registrationNo.matches(vehicle.getRegistrationNumber())) {
                result = i;
            }
        }
        return result;
    }
}
