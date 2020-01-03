package com.gojek.parkinglot.request.factory;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.ParkingLotException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileRequestType extends RequestType {

    private File commandsFile;

    private Command command;

    public FileRequestType(){}

    public FileRequestType(String filePath, Command command) {
        this.commandsFile = new File(filePath);
        this.command = command;
    }

    @Override
    public void begin() {
        try {
            String input = null;
            BufferedReader bufferReader = new BufferedReader(new FileReader(commandsFile));
            int lineNo = 1;
            while ((input = bufferReader.readLine()) != null) {
                input = input.trim();
                if (validate(input)) {
                    try {
                        command.execute(input);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else
                    System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + input);
                lineNo++;
            }
        } catch (Exception e) {
            throw new ParkingLotException(ExceptionCode.INVALID_REQUEST, ExceptionCode.INVALID_REQUEST.getMessage());
        }
    }
}
