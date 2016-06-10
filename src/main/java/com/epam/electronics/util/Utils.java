package com.epam.electronics.util;

import com.epam.electronics.entity.Electronic;
import com.epam.electronics.exception.NoElectronicsException;
import com.epam.electronics.exception.PowerCapacityRangeException;

import java.util.*;

public class Utils {
    private static final Comparator<Electronic> PRICE_COMPARATOR = (o1, o2) -> o1.getPrice().compareTo(o2.getPrice());

    public static void findByPowerCapacityRange(double min, double max, List<Electronic> list) throws NoElectronicsException, PowerCapacityRangeException {
        if (list.isEmpty()) {
            throw new NoElectronicsException();
        }
        List<Electronic> result = new LinkedList<>();
        for (Electronic electronic : list) {
            if (electronic.getPowerCapacity() >= min && electronic.getPowerCapacity() <= max) {
                result.add(electronic);
            }
        }
        if (result.isEmpty()) {
            throw new PowerCapacityRangeException(min, max);
        }
        result.forEach(System.out::println);
    }

    public static void calculateTotalConsumption(List<Electronic> list) throws NoElectronicsException {
        if (list.isEmpty()) {
            throw new NoElectronicsException();
        }
        double result = 0;
        for (Electronic electronic : list) {
            if (electronic.isPluggedIn()) {
                result += electronic.calculateConsumption();
            }
        }
        System.out.println("Total consumption is: " + result);
    }

    public static void sortByPrice(List<Electronic> list) throws NoElectronicsException {
        if (list.isEmpty()) {
            throw new NoElectronicsException();
        }
        Collections.sort(list, PRICE_COMPARATOR);
        list.forEach(System.out::println);
    }

    public static void plugIn(List<Electronic> list) throws NoElectronicsException {
        if (list.isEmpty()) {
            throw new NoElectronicsException();
        }
        Random random = new Random();
        for (Electronic electronic : list) {
            if (random.nextBoolean()) electronic.plugIn();
            else electronic.plugOff();
        }
    }

}
