package com.epam.electronics.entity;

import com.epam.electronics.exception.InvalidNewItemException;
import com.epam.electronics.entity.electronic_name.Name;

import java.io.IOException;
import java.util.Scanner;

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

    public static Electronic createFromInput() throws InvalidNewItemException, IOException {
        Electronic electronic;

        System.out.println("0: add fridge");
        System.out.println("1: add vacuum cleaner");
        System.out.println("2: add washing machine");
        System.out.println("3: add TV");
        System.out.println("4: add laptop");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        if (action < 0 || action > 4) {
            System.out.println("Incorrect value!");
            throw new InvalidNewItemException();
        }

        System.out.println("Type the id:");
        int id = new Scanner(System.in).nextInt();
        System.out.println("Type the title:");
        String title = new Scanner(System.in).nextLine();
        System.out.println("Type the price:");
        double price = new Scanner(System.in).nextDouble();
        System.out.println("Type the power capacity:");
        double powerCapacity = new Scanner(System.in).nextDouble();

        switch (Name.values()[action]) {

            case FRIDGE:
                System.out.println("Type the dimension:");
                String dimension1 = new Scanner(System.in).nextLine();
                System.out.println("Type the number of sections:");
                int sectionNumber = new Scanner(System.in).nextInt();
                System.out.println("Type the freezing time:");
                double freezeTime = new Scanner(System.in).nextDouble();
                electronic = new Fridge(id, title, price, powerCapacity, dimension1, sectionNumber, freezeTime);
                break;

            case VACUUMCLEANER:
                System.out.println("Type the capacity of dust collector:");
                double dustCollectorCapacity = new Scanner(System.in).nextDouble();
                electronic = new VacuumCleaner(id, title, price, powerCapacity, dustCollectorCapacity);
                break;

            case WASHINGMACHINE:
                System.out.println("Type the dimension:");
                String dimension2 = new Scanner(System.in).nextLine();
                System.out.println("Type the number of functions:");
                int functionsNumber = new Scanner(System.in).nextInt();
                System.out.println("Type the maximum of linen weight:");
                double maxLinenWeight = new Scanner(System.in).nextDouble();
                electronic = new WashingMachine(id, title, price, powerCapacity, dimension2, functionsNumber, maxLinenWeight);
                break;

            case TV:
                System.out.println("Type the diagonal:");
                double diagonal1 = new Scanner(System.in).nextDouble();
                System.out.println("Type the brightness:");
                double brightness = new Scanner(System.in).nextDouble();
                electronic = new TV(id, title, price, powerCapacity, diagonal1, brightness);
                break;

            case LAPTOP:
                System.out.println("Type the diagonal:");
                double diagonal2 = new Scanner(System.in).nextDouble();
                System.out.println("Type the brightness:");
                double hardDriveCapacity = new Scanner(System.in).nextDouble();
                electronic = new Laptop(id, title, price, powerCapacity, diagonal2, hardDriveCapacity);
                break;

            default:
                System.out.println("Incorrect value!");
                throw new InvalidNewItemException();
        }

        return electronic;
    }
}
