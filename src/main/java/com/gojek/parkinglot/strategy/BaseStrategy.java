package com.gojek.parkinglot.strategy;

public interface BaseStrategy {

    Integer getSlot();

    void removeSlot(Integer slot);

    void addSlot(Integer slot);
}
