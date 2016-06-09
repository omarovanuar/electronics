package com.epam.electronics.entity;

public class TV extends Electronic {
    private static final double CONSUMPTION_COEFFICIENT = 1.022;
    private double diagonal;
    private double brightness;

    public TV(Integer id, String title, double price, double powerCapacity, double diagonal, double brightness) {
        super(id, title, price, powerCapacity);
        this.diagonal = diagonal;
        this.brightness = brightness;
    }

    public void turnOn() {}

    public double getConsumption() {
        return getPowerCapacity() * CONSUMPTION_COEFFICIENT;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getBrightness() {
        return brightness;
    }

    @Override
    public String toString() {
        return "TV {" + super.toString() +
                ", diagonal = " + diagonal + " cm" +
                ", brightness = " + brightness + " cd/m2}";
    }
}
