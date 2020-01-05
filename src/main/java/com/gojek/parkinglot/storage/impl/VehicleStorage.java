package com.gojek.parkinglot.storage.impl;

import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.storage.StorageStructure;
import com.gojek.parkinglot.strategy.BaseStrategy;
import com.gojek.parkinglot.strategy.NearestAllotStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class VehicleStorage<T extends Vehicle> implements StorageStructure<T> {

    private AtomicInteger capacity = new AtomicInteger();
    private AtomicInteger available = new AtomicInteger();
    private BaseStrategy parkingStrategy;
    private Map<Integer, T> slotVehicleMap;

    private VehicleStorage(Integer capacity, Integer available, BaseStrategy parkingStrategy) {
        this.capacity.set(capacity);
        this.available.set(available);
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


    public static <T extends Vehicle> VehicleStorage<T> init(Integer capacity, Integer available, BaseStrategy parkingStrategy) {
        if (VehicleStorage.instance == null) {
            synchronized (StorageStructure.class) {
                if (VehicleStorage.instance == null) {
                    VehicleStorage.instance = new VehicleStorage(capacity, available, parkingStrategy);
                }
            }
        }
        return VehicleStorage.instance;
    }

    public static VehicleStorage getInstance() {
        return VehicleStorage.instance;
    }

    public static void destroyInstance() {
        VehicleStorage.instance = null;
    }

    @Override
    public Integer park(T vehicle) {
        if (this.available.get() == 0) {
            return ParkingLotConstants.PARKING_FULL;
        }
        if (this.slotVehicleMap.containsValue(vehicle)) {
            return ParkingLotConstants.VEHICLE_EXISTS;
        }
        Integer allocatedSlot = this.parkingStrategy.getSlot();
        this.parkingStrategy.removeSlot(allocatedSlot);
        slotVehicleMap.put(allocatedSlot, vehicle);
        available.decrementAndGet();
        return allocatedSlot;

    }

    @Override
    public Integer leaveSlot(Integer slot) {
        if (slotVehicleMap.get(slot) == null) {
            return ParkingLotConstants.SLOT_ALREADY_EMPTY;
        }
        available.incrementAndGet();
        parkingStrategy.addSlot(slot);
        slotVehicleMap.put(slot, null);
        return slot;
    }

    @Override
    public List<String> getStatus() {
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i <= capacity.get(); i++) {
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle) {
                statusList.add(i + "           " + vehicle.getRegistrationNumber() + "      " + vehicle.getColor());
            }
        }
        return statusList;
    }

    @Override
    public List<String> getRegNumberForColor(String color) {
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i <= capacity.get(); i++) {
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
        for (int i = 1; i <= capacity.get(); i++) {
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
        for (int i = 1; i <= capacity.get(); i++) {
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle && registrationNo.matches(vehicle.getRegistrationNumber())) {
                result = i;
            }
        }
        return result;
    }
}
