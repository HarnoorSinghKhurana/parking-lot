package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dao.StorageDao;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.service.ParkingService;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingServiceImpl implements ParkingService {

    private StorageDao storageDao;

    public ParkingServiceImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    @Override
    public void createParkingLot(Integer slots) {
        try {
            storageDao.createParkingLot(slots);
            System.out.println("Created parking lot with " + slots + " slots");
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }

    @Override
    public void park(Vehicle vehicle) {
        try {
            Integer allocatedSlot = storageDao.park(vehicle);
            System.out.println("Allocated slot number: " + allocatedSlot);
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }

    @Override
    public void leaveSlot(Integer slotNumber) {
        try {
            storageDao.leaveSlot(slotNumber);
            System.out.println("Slot number " + slotNumber + " is free");
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }

    @Override
    public void getStatus() {
        try {
            List<String> statusList = storageDao.status();
            if (statusList.size() == 0) {
                System.out.println("Parking does not exist!");
            } else {
                System.out.println("Slot No.\tRegistration No.\tColor");
                statusList.forEach(System.out::println);
            }
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }

    @Override
    public void getRegNumberForColor(String color) {
        try {
            List<String> registrationList = storageDao.getRegNumberForColor(color);
            if (registrationList.size() == 0)
                System.out.println("Not Found");
            else {
                System.out.println(String.join(",", registrationList));
            }
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }

    @Override
    public void getSlotNumbersFromColor(String color) {
        try {
            List<Integer> slotList = storageDao.getSlotNumbersFromColor(color);
            if (slotList.size() == 0)
                System.out.println("Not Found");
            else {
                System.out.println(slotList.stream().map(String::valueOf)
                        .collect(Collectors.joining(",")));
            }
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }

    @Override
    public void getSlotNoFromRegistrationNo(String registrationNo) {
        try {
            Integer value = storageDao.getSlotNoFromRegistrationNo(registrationNo);
            System.out.println(value != Integer.MIN_VALUE ? value : "Not Found");
        } catch (Exception e) {
            if (!(e instanceof ParkingLotException)) {
                throw new ParkingLotException(ExceptionCode.COMPUTATION_ERROR, ExceptionCode.COMPUTATION_ERROR.getMessage());
            }
            throw e;
        }
    }
}
