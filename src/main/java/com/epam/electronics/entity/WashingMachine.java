package com.epam.electronics.entity;

public class WashingMachine extends Electronic {
    private String dimension;
    private int functionsNumber;
    private double maxLinenWeight;

    public WashingMachine() {
    }

    public WashingMachine(Integer id, String title, double price, double powerCapacity, String dimension, int functionsNumber, double maxLinenWeight) {
        super(id, title, price, powerCapacity);
        this.dimension = dimension;
        this.functionsNumber = functionsNumber;
        this.maxLinenWeight = maxLinenWeight;
        consumptionCoefficient = 1.02;
    }

    public void wash() {
        System.out.println(getClass().getSimpleName() + ": " + getTitle() + "is washing your clothes");
    }

    public double calculateConsumption() {
        return getPowerCapacity() * consumptionCoefficient;
    }

    public String createFileData() {
        return getDimension() + ";" + getFunctionsNumber() + ";" + getMaxLinenWeight();
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

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setFunctionsNumber(int functionsNumber) {
        this.functionsNumber = functionsNumber;
    }

    public void setMaxLinenWeight(double maxLinenWeight) {
        this.maxLinenWeight = maxLinenWeight;
    }

    @Override
    public String toString() {
        return "WashingMachine {" +super.toString() +
                ", dimension = " + dimension + " cm" +
                ", functionsNumber = " + functionsNumber +
                ", maxLinenWeight = " + maxLinenWeight + " kg}";
    }

}
