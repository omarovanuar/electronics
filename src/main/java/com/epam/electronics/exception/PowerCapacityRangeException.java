package com.epam.electronics.exception;

public class PowerCapacityRangeException extends Exception {
    private double minConsumption;
    private double maxConsumption;

    public PowerCapacityRangeException(double minConsumption, double maxConsumption) {
        this.minConsumption = minConsumption;
        this.maxConsumption = maxConsumption;
    }

    public String getMessage() {
        return "[PowerCapacityRangeException]: Electronic equipment with consumption range: "
                + minConsumption + "-" + maxConsumption + " was not found";
    }
}
