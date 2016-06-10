package com.epam.electronics.entity;

public class Fridge extends Electronic {
    private String dimension;
    private int sectionNumber;
    private double freezeTime;

    public Fridge(Integer id, String title, double price, double powerCapacity, String dimension, int sectionNumber, double freezeTime) {
        super(id, title, price, powerCapacity);
        this.dimension = dimension;
        this.sectionNumber = sectionNumber;
        this.freezeTime = freezeTime;
        consumptionCoefficient = 1.025;
    }

    public void freeze(){
        System.out.println(getClass().getSimpleName() + ": " + getTitle() + "is freezing your food");
    }

    public double calculateConsumption() {
        return getPowerCapacity() * consumptionCoefficient;
    }

    public String createFileData() {
        return getClass().getSimpleName() + ":" + createCommonFileData() + ":" + getDimension() + ":" + getSectionNumber() + ":" + getFreezeTime();
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
