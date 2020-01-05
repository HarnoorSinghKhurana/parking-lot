package com.gojek.parkinglot.constants;

public final class ParkingLotConstants {

    private ParkingLotConstants() {
        throw new IllegalStateException("Utility class :: cannot be instantiated");
    }

    public static final String NOT_FOUND = "Not found";
    public static final String SPACE = " ";
    public static final String VALID = "true";
    public static final String NOT_VALID = "false";
    public static final String EXIT = "exit";
    public static final String CREATE_PARKING_LOT = "create_parking_lot";
    public static final String PARK = "park";
    public static final String LEAVE = "leave";
    public static final String STATUS = "status";
    public static final String REG_NUMBER_FOR_CARS_WITH_COLOR = "registration_numbers_for_cars_with_colour";
    public static final String SLOTS_NUMBER_FOR_CARS_WITH_COLOR = "slot_numbers_for_cars_with_colour";
    public static final String SLOTS_NUMBER_FOR_REG_NUMBER = "slot_number_for_registration_number";
    public static final int PARKING_FULL = -1;
    public static final int VEHICLE_EXISTS = -2;
    public static final int SLOT_ALREADY_EMPTY = -1;
}
