package com.epam.electronics.entity;

public abstract class Electronic {
    private Integer id;
    private String title;
    private double price;
    private double powerCapacity;
    private boolean isPluggedIn = false;

    public Electronic() {
    }

    public Electronic(Integer id, String title, double price, double powerCapacity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.powerCapacity = powerCapacity;
    }

    public abstract double getConsumption();

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public double getPowerCapacity() {
        return powerCapacity;
    }

    public boolean isPluggedIn() {
        return isPluggedIn;
    }

    public void setPluggedIn(boolean pluggedIn) {
        isPluggedIn = pluggedIn;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", title = " + title +
                ", price = " + price + " KZT" +
                ", powerCapacity = " + powerCapacity + " W" +
                ", isPluggedIn = " + isPluggedIn;
    }
}
