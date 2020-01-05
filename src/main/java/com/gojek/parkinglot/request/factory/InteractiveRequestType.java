package com.gojek.parkinglot.request.factory;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InteractiveRequestType implements RequestType {

    private Command command;

    public InteractiveRequestType() {
    }

    public InteractiveRequestType(Command command) {
        this.command = command;
    }

    @Override
    public void begin() {
        while (true) {
            try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));) {
                String input = bufferReader.readLine().trim();
                if (input.equalsIgnoreCase(ParkingLotConstants.EXIT)) {
                    break;
                } else {
                    validateAndExecuteCommand(input);
                }
            } catch (Exception e) {
                throw new ParkingLotException(ExceptionCode.INVALID_REQUEST, ExceptionCode.INVALID_REQUEST.getMessage());
            }
        }
    }

    private void validateAndExecuteCommand(String input) {
        if (validate(input)) {
            try {
                command.execute(input.trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            printHelpGuild();
        }
    }

    //TO-DO
    private void printHelpGuild() {
        System.out.println("Input should be of following type only");
    }

}
