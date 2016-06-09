package com.epam.electronics.entity;

public class Fridge extends Electronic {
    private static final double CONSUMPTION_COEFFICIENT = 1.023;
    private String dimension;
    private int sectionNumber;
    private double freezeTime;

    public Fridge(Integer id, String title, double price, double powerCapacity, String dimension, int sectionNumber, double freezeTime) {
        super(id, title, price, powerCapacity);
        this.dimension = dimension;
        this.sectionNumber = sectionNumber;
        this.freezeTime = freezeTime;
    }

    public void freeze(){
    }

    public double getConsumption() {
        return getPowerCapacity() * CONSUMPTION_COEFFICIENT;
    }

    public String getDimension() {
        return dimension;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public double getFreezeTime() {
        return freezeTime;
    }

    @Override
    public String toString() {
        return "Fridge {" + super.toString() +
                ", dimension = " + dimension + " cm" +
                ", sectionNumber = " + sectionNumber +
                ", freezeTime = " + freezeTime + " min}";
    }
}
