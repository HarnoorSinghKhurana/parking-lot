package com.gojek.parkinglot;

import com.gojek.parkinglot.request.factory.RequestFactory;

public class ParkingLotMainClass {

    public static void main(String[] args) {
        RequestFactory.process(args)
                .begin();
    }
}
