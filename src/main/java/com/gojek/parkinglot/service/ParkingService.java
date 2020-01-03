package com.gojek.parkinglot.service;

import com.gojek.parkinglot.models.Car;
import com.gojek.parkinglot.models.Color;

public interface ParkingService extends BaseService {

    void createParkingLot(Integer slots);

    void leaveSlot(Integer slot);

    void park(Car car);

    void getStatus();

    void getRegNumberForColor(Color valueOf);

    void getSlotNumbersFromColor(String commandWithInput);

    void getSlotNoFromRegistrationNo(String commandWithInput);
}
