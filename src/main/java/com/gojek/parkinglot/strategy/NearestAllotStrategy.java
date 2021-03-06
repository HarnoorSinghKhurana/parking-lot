package com.gojek.parkinglot.strategy;

import java.util.TreeSet;

public class NearestAllotStrategy implements BaseStrategy {

    private TreeSet<Integer> slots;

    public NearestAllotStrategy(){
        slots = new TreeSet<>();
    }

    @Override
    public Integer getSlot() {
        return slots.first();

    }

    @Override
    public void removeSlot(Integer slot) {
        slots.remove(slot);
    }

    @Override
    public void addSlot(Integer slot) {
        slots.add(slot);
    }
}
