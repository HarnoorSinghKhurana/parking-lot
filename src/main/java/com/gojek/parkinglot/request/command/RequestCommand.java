package com.gojek.parkinglot.request.command;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.unchecked.InvalidParkingSlotsException;
import com.gojek.parkinglot.models.Car;
import com.gojek.parkinglot.service.ParkingService;

public class RequestCommand implements Command {

    private ParkingService parkingService;

    public RequestCommand(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @Override
    public void execute(String command) {
        String[] commandWithInputs = command.split(ParkingLotConstants.SPACE);
        switch (commandWithInputs[0]){
            case ParkingLotConstants.CREATE_PARKING_LOT:
                try {
                    Integer slots = Integer.parseInt(commandWithInputs[1]);
                    parkingService.createParkingLot(slots);
                } catch (NumberFormatException ne){
                    throw new InvalidParkingSlotsException(ExceptionCode.INVALID_PARKING_SLOTS,
                            ExceptionCode.INVALID_PARKING_SLOTS.getMessage());
                }
                break;
            case ParkingLotConstants.LEAVE:
                try {
                    Integer slot = Integer.parseInt(commandWithInputs[1]);
                    parkingService.leaveSlot(slot);
                } catch (NumberFormatException ne){
                    throw new InvalidParkingSlotsException(ExceptionCode.INVALID_PARKING_SLOTS,
                            ExceptionCode.INVALID_PARKING_SLOTS.getMessage());
                }
                break;
            case ParkingLotConstants.PARK:
                parkingService.park(new Car(commandWithInputs[1], commandWithInputs[2]));
                break;
            case ParkingLotConstants.STATUS:
                parkingService.getStatus();
                break;
            case ParkingLotConstants.REG_NUMBER_FOR_CARS_WITH_COLOR:
                parkingService.getRegNumberForColor(commandWithInputs[1]);
                break;
            case ParkingLotConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
                parkingService.getSlotNumbersFromColor(commandWithInputs[1]);
                break;
            case ParkingLotConstants.SLOTS_NUMBER_FOR_REG_NUMBER:
                parkingService.getSlotNoFromRegistrationNo(commandWithInputs[1]);
                break;
            default:
                break;

        }
    }
}