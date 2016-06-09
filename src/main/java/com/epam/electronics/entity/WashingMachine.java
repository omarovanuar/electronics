package com.epam.electronics.entity;

public class WashingMachine extends Electronic {
    private static final double CONSUMPTION_COEFFICIENT = 1.02;
    private String dimension;
    private int functionsNumber;
    private double maxLinenWeight;

    public WashingMachine(Integer id, String title, double price, double powerCapacity, String dimension, int functionsNumber, double maxLinenWeight) {
        super(id, title, price, powerCapacity);
        this.dimension = dimension;
        this.functionsNumber = functionsNumber;
        this.maxLinenWeight = maxLinenWeight;
    }

    public void wash() {}

    public double getConsumption() {
        return getPowerCapacity() * CONSUMPTION_COEFFICIENT;
    }

    public String getDimension() {
        return dimension;
    }

    public int getFunctionsNumber() {
        return functionsNumber;
    }

    public double getMaxLinenWeight() {
        return maxLinenWeight;
    }

    @Override
    public String toString() {
        return "WashingMachine {" +super.toString() +
                ", dimension = " + dimension + " cm" +
                ", functionsNumber = " + functionsNumber +
                ", maxLinenWeight = " + maxLinenWeight + " kg}";
    }

}
