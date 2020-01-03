package com.gojek.parkinglot.exception;

public class ParkingLotException extends RuntimeException {

    private String errorCode = null;
    private Object[] errorParameters = null;
    private ExceptionCode exceptionCode;

    public ParkingLotException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ParkingLotException(String message) {
        super(message);
    }

    public ParkingLotException(ExceptionCode code, String message) {
        super(message);
        this.exceptionCode = code;
        this.errorCode = message;

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getErrorParameters() {
        return errorParameters;
    }

    public void setErrorParameters(Object[] errorParameters) {
        this.errorParameters = errorParameters;
    }
}
