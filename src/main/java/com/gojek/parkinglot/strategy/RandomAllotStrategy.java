package com.gojek.parkinglot.strategy;

import java.util.LinkedList;
import java.util.Queue;

public class RandomAllotStrategy implements BaseStrategy {

    private Queue<Integer> slots;

    public RandomAllotStrategy() {
        this.slots = new LinkedList<>();
    }

    @Override
    public Integer getSlot() {
        return slots.peek();
    }

    @Override
    public void removeSlot(Integer slot) {
        slots.poll();
    }

    @Override
    public void addSlot(Integer slot) {
        slots.add(slot);
    }
}
