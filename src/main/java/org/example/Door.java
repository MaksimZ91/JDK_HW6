package org.example;

public class Door {
    private final String gift;

    public Door(String gift) {
        this.gift = gift;
    }

    public String getPrize() {
        return gift;
    }

    @Override
    public String toString() {
        return gift;
    }
}
