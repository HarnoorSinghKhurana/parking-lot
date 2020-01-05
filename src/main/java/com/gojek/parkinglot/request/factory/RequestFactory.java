package com.gojek.parkinglot.request.factory;

import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.dao.impl.StorageDaoImpl;
import com.gojek.parkinglot.exception.ExceptionCode;
import com.gojek.parkinglot.exception.unchecked.InvalidInputTypeException;
import com.gojek.parkinglot.request.command.RequestCommand;
import com.gojek.parkinglot.service.impl.ParkingServiceImpl;
import com.gojek.parkinglot.service.impl.StorageServiceImpl;
import com.gojek.parkinglot.validate.ParkingValidator;

public class RequestFactory {

    private RequestFactory() {
        throw new IllegalStateException("RequestFactory cannot be instantiated : Utility class");
    }

    public static RequestType process(String... args) {
        Command command = new RequestCommand(
                new ParkingServiceImpl(
                        new StorageServiceImpl(new StorageDaoImpl()), new ParkingValidator()));
        switch (args.length) {
            case 0:
                return new InteractiveRequestType(command);
            case 1:
                return new FileRequestType(args[0], command);
            default:
                throw new InvalidInputTypeException(ExceptionCode.INVALID_REQUEST,
                        ExceptionCode.INVALID_REQUEST.getMessage());
        }
    }
}
