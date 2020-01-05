package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.strategy.BaseStrategy;
import com.gojek.parkinglot.strategy.NearestAllotStrategy;

import java.util.*;

public class StorageParkingSlot<T extends Vehicle> {

    private Integer capacity;
    private Integer available;
    private BaseStrategy parkingStrategy;
    private Map<Integer, T> slotVehicleMap;

    private StorageParkingSlot(Integer capacity, Integer available, BaseStrategy parkingStrategy) {
        this.capacity = capacity;
        this.available = available;
        this.parkingStrategy = parkingStrategy;
        this.slotVehicleMap = new HashMap<>();
        if(parkingStrategy == null){
            this.parkingStrategy = new NearestAllotStrategy();
        }
        for (int i = 1; i <= capacity; i++){
            slotVehicleMap.put(i, null);
            parkingStrategy.addSlot(i);
        }
    }

    private static StorageParkingSlot instance = null;

    public static <T extends Vehicle> StorageParkingSlot<T> createStructure(Integer capacity, Integer available, BaseStrategy parkingStrategy) {
        if (instance == null) {
            synchronized (StorageParkingSlot.class) {
                if (instance == null) {
                    instance = new StorageParkingSlot(capacity, available, parkingStrategy);
                }
            }
        }
        return instance;
    }


    public Integer park(T vehicle) {
        if(this.available == 0){
            throw new ParkingLotException(ExceptionCode.PARKING_FULL, ExceptionCode.PARKING_FULL.getMessage());
        }
        Integer allocatedSlot = this.parkingStrategy.getSlot();
        this.parkingStrategy.removeSlot(allocatedSlot);
        if(this.slotVehicleMap.containsValue(vehicle)){
            throw new ParkingLotException(ExceptionCode.VEHICLE_ALREADY_EXIST, ExceptionCode.VEHICLE_ALREADY_EXIST.getMessage());
        }
        slotVehicleMap.put(allocatedSlot, vehicle);
        available --;
        return allocatedSlot;

    }

    public void leaveSLot(Integer slot) {
        if(slotVehicleMap.get(slot) == null){
            throw new ParkingLotException(ExceptionCode.SLOT_ALREADY_EMPTY, ExceptionCode.SLOT_ALREADY_EMPTY.getMessage());
        }
        available++;
        parkingStrategy.addSlot(slot);
        slotVehicleMap.put(slot, null);
    }

    public List<String> getStatus() {
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i <= capacity; i++){
            Vehicle vehicle = slotVehicleMap.get(i);
            if (null != vehicle){
                statusList.add(i + " " + vehicle.getRegistrationNumber() + " " + vehicle.getColor());
            }
        }
        return statusList;
    }
}
