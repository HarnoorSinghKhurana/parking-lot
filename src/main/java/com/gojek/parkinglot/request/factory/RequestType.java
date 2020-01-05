package com.gojek.parkinglot.request.factory;

import com.gojek.parkinglot.constants.CommandConstants;
import com.gojek.parkinglot.constants.ParkingLotConstants;

public abstract class RequestType {

    public abstract void begin();

    public boolean validate(String command) {
        try {
            String[] commandWithValues = command.split(ParkingLotConstants.SPACE);

            if (!CommandConstants.commandsMap.containsKey(commandWithValues[0])) {
                return Boolean.valueOf(ParkingLotConstants.NOT_VALID);
            } else {
                Integer paramsCount = CommandConstants.commandsMap.get(commandWithValues[0]);
                if (paramsCount != (commandWithValues.length-1)) {
                    return Boolean.valueOf(ParkingLotConstants.NOT_VALID);
                }
            }
            return Boolean.valueOf(ParkingLotConstants.VALID);
        } catch (Exception e){
            return Boolean.valueOf(ParkingLotConstants.NOT_VALID);
        }
    }

}
