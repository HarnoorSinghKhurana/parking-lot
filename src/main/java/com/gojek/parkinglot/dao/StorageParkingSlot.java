package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.strategy.BaseStrategy;

import java.util.Map;

public class StorageParkingSlot<T extends Vehicle> {

    private Integer capacity;
    private Integer available;
    private BaseStrategy parkingStrategy;
    private Map<Integer, T> slotVehicleMap;

    public StorageParkingSlot(Integer capacity,
                               Integer available,
                               BaseStrategy parkingStrategy,
                               Map<Integer, T> slotVehicleMap) {
        this.capacity = capacity;
        this.available = available;
        this.parkingStrategy = parkingStrategy;
        this.slotVehicleMap = slotVehicleMap;
    }


}
