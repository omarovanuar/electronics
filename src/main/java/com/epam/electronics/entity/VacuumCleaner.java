package com.epam.electronics.entity;

public class VacuumCleaner extends Electronic {
    private static final double CONSUMPTION_COEFFICIENT = 1.017;
    private double dustCollectorCapacity;

    public VacuumCleaner(Integer id, String title, double price, double powerCapacity, double dustCollectorCapacity) {
        super(id, title, price, powerCapacity);
        this.dustCollectorCapacity = dustCollectorCapacity;
    }

    public void clean() {}

    public double getConsumption() {
        return getPowerCapacity() * CONSUMPTION_COEFFICIENT;
    }

    public double getDustCollectorCapacity() {
        return dustCollectorCapacity;
    }

    @Override
    public String toString() {
        return "VacuumCleaner {" + super.toString() +
                ", dustCollectorCapacity = " + dustCollectorCapacity + " kg}";
    }
}
