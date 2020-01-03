package com.gojek.parkinglot.request.command;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.constants.ParkingLotConstants;

public class RequestCommand implements Command {

    @Override
    public void execute(String command) {
        switch (command){
            case ParkingLotConstants.CREATE_PARKING_LOT:
                break;
            case ParkingLotConstants.LEAVE:
                break;
            case ParkingLotConstants.PARK:
                break;
            case ParkingLotConstants.STATUS:
                break;
            case ParkingLotConstants.REG_NUMBER_FOR_CARS_WITH_COLOR:
                break;
            case ParkingLotConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
                break;
            case ParkingLotConstants.SLOTS_NUMBER_FOR_REG_NUMBER:
                break;
            default:
                break;

        }
    }
}