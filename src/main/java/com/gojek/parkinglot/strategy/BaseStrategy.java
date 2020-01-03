package com.gojek.parkinglot.strategy;

public interface BaseStrategy {

    Integer getSlot();

    void freeSlot(Integer slot);

    void addSlot(Integer slot);
}
