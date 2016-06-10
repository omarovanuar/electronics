package com.epam.electronics.entity;

public class VacuumCleaner extends Electronic {
    private double dustCollectorCapacity;

    public VacuumCleaner() {
    }

    public VacuumCleaner(Integer id, String title, double price, double powerCapacity, double dustCollectorCapacity) {
        super(id, title, price, powerCapacity);
        this.dustCollectorCapacity = dustCollectorCapacity;
        consumptionCoefficient = 1.017;
    }

    public void clean() {
        System.out.println(getClass().getSimpleName() + ": " + getTitle() + "is cleaning your floors");
    }

    public double calculateConsumption() {
        return getPowerCapacity() * consumptionCoefficient;
    }

    public String createFileData() {
        return ":" + getDustCollectorCapacity();
    }

    public double getDustCollectorCapacity() {
        return dustCollectorCapacity;
    }

    public void setDustCollectorCapacity(double dustCollectorCapacity) {
        this.dustCollectorCapacity = dustCollectorCapacity;
    }

    @Override
    public String toString() {
        return "VacuumCleaner {" + super.toString() +
                ", dustCollectorCapacity = " + dustCollectorCapacity + " kg}";
    }
}
