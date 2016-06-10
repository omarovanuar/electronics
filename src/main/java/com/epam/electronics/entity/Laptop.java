package com.epam.electronics.entity;

public class Laptop extends Electronic {
    private double diagonal;
    private double hardDriveCapacity;

    public Laptop(Integer id, String title, double price, double powerCapacity, double diagonal, double hardDriveCapacity) {
        super(id, title, price, powerCapacity);
        this.diagonal = diagonal;
        this.hardDriveCapacity = hardDriveCapacity;
        consumptionCoefficient = 1.006;
    }

    public void waitingMode() {
        System.out.println(getClass().getSimpleName() + ": " + getTitle() + "is waiting for you");
    }

    public double calculateConsumption() {
        return getPowerCapacity() * consumptionCoefficient;
    }

    @Override
    public String createFileData() {
        return ":" + getDiagonal() + ":" + getHardDriveCapacity();
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
