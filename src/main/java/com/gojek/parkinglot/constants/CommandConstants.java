package com.gojek.parkinglot.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandConstants {

    public static final Map<String, Integer> commandsMap;

    private CommandConstants() {
        throw new IllegalStateException("CommandConstants cannot be instantiated : Utility class");
    }

    static {
        Map<String, Integer> command = new HashMap<>();
        command.put(ParkingLotConstants.CREATE_PARKING_LOT, 1);
        command.put(ParkingLotConstants.PARK, 2);
        command.put(ParkingLotConstants.LEAVE, 1);
        command.put(ParkingLotConstants.STATUS, 0);
        command.put(ParkingLotConstants.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
        command.put(ParkingLotConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
        command.put(ParkingLotConstants.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
        commandsMap = Collections.unmodifiableMap(command);
    }

}
