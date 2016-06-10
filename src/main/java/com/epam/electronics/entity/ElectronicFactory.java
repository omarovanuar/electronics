package com.epam.electronics.entity;

import com.epam.electronics.entity.electronic_name.Name;

import java.io.IOException;

public class ElectronicFactory {
    public static Electronic createFromFile(String[] strings) throws IOException {
        Electronic electronic;
        int i = 1;
        switch (Name.valueOf(strings[0].toUpperCase())) {
            case FRIDGE:
                electronic = new Fridge(Integer.valueOf(strings[i]), strings[++i], Double.valueOf(strings[++i]),
                        Double.valueOf(strings[++i]), strings[++i], Integer.valueOf(strings[++i]), Double.valueOf(strings[++i]));
                break;
            case VACUUMCLEANER:
                electronic = new VacuumCleaner(Integer.valueOf(strings[i]), strings[++i], Double.valueOf(strings[++i]),
                        Double.valueOf(strings[++i]), Double.valueOf(strings[++i]));
                break;
            case WASHINGMACHINE:
                electronic = new WashingMachine(Integer.valueOf(strings[i]), strings[++i], Double.valueOf(strings[++i]),
                        Double.valueOf(strings[++i]), strings[++i], Integer.valueOf(strings[++i]), Double.valueOf(strings[++i]));
                break;
            case TV:
                electronic = new TV(Integer.valueOf(strings[i]), strings[++i], Double.valueOf(strings[++i]),
                        Double.valueOf(strings[++i]), Double.valueOf(strings[++i]), Double.valueOf(strings[++i]));
                break;
            case LAPTOP:
                electronic = new Laptop(Integer.valueOf(strings[i]), strings[++i], Double.valueOf(strings[++i]),
                        Double.valueOf(strings[++i]), Double.valueOf(strings[++i]), Double.valueOf(strings[++i]));
                break;
            default:
                throw new IOException();
        }
        return electronic;
    }
}
