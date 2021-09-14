package com.arkanoid.utils;

/**
 * @author Tom Ben-Dor
 */
public class Counter {
    private int value = 0;

    /**
     * Adds the value by number.
     *
     * @param number a number.
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Subtracts the value by number.
     *
     * @param number a number.
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return value;
    }
}
