package com.epam.electronics.entity;

public class Laptop extends Electronic {
    private static final double CONSUMPTION_COEFFICIENT = 1.006;
    private double diagonal;
    private double hardDriveCapacity;

    public Laptop(Integer id, String title, double price, double powerCapacity, double diagonal, double hardDriveCapacity) {
        super(id, title, price, powerCapacity);
        this.diagonal = diagonal;
        this.hardDriveCapacity = hardDriveCapacity;
    }

    public void waitingMode() {}

    public double getConsumption() {
        return getPowerCapacity() * CONSUMPTION_COEFFICIENT;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getHardDriveCapacity() {
        return hardDriveCapacity;
    }

    @Override
    public String toString() {
        return "Laptop {" + super.toString() +
                ", diagonal = " + diagonal + " cm" +
                ", hardDriveCapacity = " + hardDriveCapacity + " gb}";
    }
}
