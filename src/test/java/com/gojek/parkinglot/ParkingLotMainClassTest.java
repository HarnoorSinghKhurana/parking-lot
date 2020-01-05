package com.gojek.parkinglot;


import com.gojek.parkinglot.dao.Impl.StorageDaoImpl;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.models.Car;
import com.gojek.parkinglot.service.ParkingService;
import com.gojek.parkinglot.service.impl.ParkingServiceImpl;
import com.gojek.parkinglot.service.impl.StorageServiceImpl;
import com.gojek.parkinglot.storage.impl.VehicleStorage;
import com.gojek.parkinglot.validate.ParkingValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;


public class ParkingLotMainClassTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Before
    public void init() {
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void onExit() {
        VehicleStorage.destroyInstance();
    }

    @Test
    public void createParkingLot() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(5);
        assertEquals("Created a parking lot with 5 slots\n", outStream.toString());
    }

    @Test
    public void createParkingLot_ExceptionAlreadyExists() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(5);
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.PARKING_ALREADY_EXIST.getMessage()));
        parkingService.createParkingLot(1);
    }

    @Test
    public void getStatus_ParkingLotCreated() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(5);
        parkingService.getStatus();
        assertEquals("Created a parking lot with 5 slots\nParking does not exist!\n", outStream.toString());
    }

    @Test
    public void getStatus_ParkingLotNotCreated() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.PARKING_DOES_NOT_EXIST.getMessage()));
        parkingService.getStatus();
    }

    @Test
    public void getStatus_ParkingLotCreated_WithData() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(5);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.getStatus();
        assertEquals("Createdaparkinglotwith5slots\nAllocatedslotnumber:1\nSlotNo.RegistrationNoColour\n" +
                "1PB02-AC-1234Red\n", outStream.toString().replace(" ", ""));
    }

    @Test
    public void parkVehicle() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(1);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        assertEquals("Createdaparkinglotwith1slots\nAllocatedslotnumber:1\n", outStream.toString().replace(" ", ""));
    }

    @Test
    public void parkVehicle_ParkingLotIsFull() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.PARKING_FULL.getMessage()));
        parkingService.createParkingLot(1);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.park(new Car("PB02-AC-12334", "Red"));
    }

    @Test
    public void parkVehicle_VehicleExists() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.VEHICLE_ALREADY_EXIST.getMessage()));
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.park(new Car("PB02-AC-1234", "Red"));
    }

    @Test
    public void parkVehicle_ComputationException() {
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.COMPUTATION_ERROR.getMessage()));
        parkingService.createParkingLot(-1);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
    }

    @Test
    public void unParkVehicle(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.park(new Car("PB02-AC-1111", "Red"));
        parkingService.leaveSlot(2);
        assertEquals("Createdaparkinglotwith3slots\nAllocatedslotnumber:1\nAllocatedslotnumber:2\n" +
                "Slotnumber2isfree\n", outStream.toString().replace(" ", ""));
    }

    @Test
    public void unParkVehicle_SlotIsEmpty(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.SLOT_ALREADY_EMPTY.getMessage()));
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.park(new Car("PB02-AC-1111", "Red"));
        parkingService.leaveSlot(3);
    }

    @Test
    public void unParkVehicle_UnexpectedException(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(is(ExceptionCode.COMPUTATION_ERROR.getMessage()));
        parkingService.createParkingLot(-1);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.park(new Car("PB02-AC-1111", "Red"));
        parkingService.leaveSlot(3);
    }

    @Test
    public void getRegNumberForColorTest(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.getRegNumberForColor("Red");
        assertEquals("Createdaparkinglotwith3slots\nAllocatedslotnumber:1\n" +
                "PB02-AC-1234\n", outStream.toString().replace(" ", ""));
    }

    @Test
    public void getRegNumberForColorTest_NotFound(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.getRegNumberForColor("Red");
        assertEquals("Createdaparkinglotwith3slots\nNotfound\n", outStream.toString().replace(" ", ""));
    }

    @Test
    public void getSlotNoFromRegistrationNoTest(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.getSlotNoFromRegistrationNo("PB02-AC-1234");
        assertEquals("Createdaparkinglotwith3slots\nAllocatedslotnumber:1\n1\n",
                outStream.toString().replace(" ", ""));
    }

    @Test
    public void getSlotNoFromRegistrationNoTest_NotFound(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.getSlotNoFromRegistrationNo("PB02-AC-9999");
        assertEquals("Createdaparkinglotwith3slots\nAllocatedslotnumber:1\n" +
                "Notfound\n", outStream.toString().replace(" ", ""));
    }

    @Test
    public void getSlotNumbersFromColorTest(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.getSlotNumbersFromColor("Red");
        assertEquals("Createdaparkinglotwith3slots\nAllocatedslotnumber:1\n1\n",
                outStream.toString().replace(" ", ""));
    }

    @Test
    public void getSlotNumbersFromColorTest_NotFound(){
        ParkingService parkingService = new ParkingServiceImpl(new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator());
        parkingService.createParkingLot(3);
        parkingService.park(new Car("PB02-AC-1234", "Red"));
        parkingService.getSlotNumbersFromColor("Blue");
        assertEquals("Createdaparkinglotwith3slots\nAllocatedslotnumber:1\n" +
                "Notfound\n", outStream.toString().replace(" ", ""));
    }

}