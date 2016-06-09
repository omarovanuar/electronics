package com.epam.electronics.util;

import com.epam.electronics.entity.*;
import com.epam.electronics.exception.InvalidNewItemException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.epam.electronics.datareader.TxtFileReader.FILE_PATH;

public class Utils {
    private static final Comparator<Electronic> PRICE_COMPARATOR = (o1, o2) -> o1.getPrice().compareTo(o2.getPrice());

    public static void findByPowerCapacityRange(double min, double max, List<Electronic> list) {
        List<Electronic> result = new LinkedList<>();
        for (Electronic electronic : list) {
            if (electronic.getPowerCapacity() >= min && electronic.getPowerCapacity() <= max) {
                result.add(electronic);
            }
        }
        result.forEach(System.out::println);
    }

    public static void calculateTotalConsumption(List<Electronic> list) {
        double result = 0;
        for (Electronic electronic : list) {
            if (electronic.isPluggedIn()) {
                result += electronic.getConsumption();
            }
        }
        System.out.println("Total consumption is: " + result);
    }

    public static void sortByPrice(List<Electronic> list) {
        Collections.sort(list, PRICE_COMPARATOR);
        list.forEach(System.out::println);
    }

    public static Electronic addNewElectronic() throws InvalidNewItemException, IOException {
        Electronic electronic;

        System.out.println("1: add fridge");
        System.out.println("2: add vacuum cleaner");
        System.out.println("3: add washing machine");
        System.out.println("4: add TV");
        System.out.println("5: add laptop");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        if (action < 0 || action > 5) {
            System.out.println("Incorrect value!");
            throw new InvalidNewItemException();
        }

        File file = new File(FILE_PATH);
        FileWriter fileWriter = new FileWriter(file, true);

        System.out.println("Type the id:");
        int id = new Scanner(System.in).nextInt();
        System.out.println("Type the title:");
        String title = new Scanner(System.in).nextLine();
        System.out.println("Type the price:");
        double price = new Scanner(System.in).nextDouble();
        System.out.println("Type the power capacity:");
        double powerCapacity = new Scanner(System.in).nextDouble();

        switch (action) {

            case 1:
                System.out.println("Type the dimension:");
                String dimension1 = new Scanner(System.in).nextLine();
                System.out.println("Type the number of sections:");
                int sectionNumber = new Scanner(System.in).nextInt();
                System.out.println("Type the freezing time:");
                double freezeTime = new Scanner(System.in).nextDouble();
                electronic = new Fridge(id, title, price, powerCapacity, dimension1, sectionNumber, freezeTime);
                fileWriter.write("\nFridge:" + id + ":" + title + ":" + price + ":" + powerCapacity + ":" + dimension1 + ":" + sectionNumber + ":" + freezeTime);
                fileWriter.close();
                break;

            case 2:
                System.out.println("Type the capacity of dust collector:");
                double dustCollectorCapacity = new Scanner(System.in).nextDouble();
                electronic = new VacuumCleaner(id, title, price, powerCapacity, dustCollectorCapacity);
                fileWriter.write("\nVacuum cleaner:" + id + ":" + title + ":" + price + ":" + powerCapacity + ":" + dustCollectorCapacity);
                fileWriter.close();
                break;

            case 3:
                System.out.println("Type the dimension:");
                String dimension2 = new Scanner(System.in).nextLine();
                System.out.println("Type the number of functions:");
                int functionsNumber = new Scanner(System.in).nextInt();
                System.out.println("Type the maximum of linen weight:");
                double maxLinenWeight = new Scanner(System.in).nextDouble();
                electronic = new WashingMachine(id, title, price, powerCapacity, dimension2, functionsNumber, maxLinenWeight);
                fileWriter.write("\nWashing machine:" + id + ":" + title + ":" + price + ":" + powerCapacity + ":" + dimension2 + ":" + functionsNumber + ":" + maxLinenWeight);
                fileWriter.close();
                break;

            case 4:
                System.out.println("Type the diagonal:");
                double diagonal1 = new Scanner(System.in).nextDouble();
                System.out.println("Type the brightness:");
                double brightness = new Scanner(System.in).nextDouble();
                electronic = new TV(id, title, price, powerCapacity, diagonal1, brightness);
                fileWriter.write("\nTV:" + id + ":" + title + ":" + price + ":" + powerCapacity + ":" + diagonal1 + ":" + brightness);
                fileWriter.close();
                break;

            case 5:
                System.out.println("Type the diagonal:");
                double diagonal2 = new Scanner(System.in).nextDouble();
                System.out.println("Type the brightness:");
                double hardDriveCapacity = new Scanner(System.in).nextDouble();
                electronic = new Laptop(id, title, price, powerCapacity, diagonal2, hardDriveCapacity);
                fileWriter.write("\nLaptop:" + id + ":" + title + ":" + price + ":" + powerCapacity + ":" + diagonal2 + ":" + hardDriveCapacity);
                fileWriter.close();
                break;

            default:
                System.out.println("Incorrect value!");
                throw new InvalidNewItemException();
        }

        return electronic;
    }


    public static void plugIn(List<Electronic> list) {
        Random random = new Random();
        for (Electronic electronic : list) {
            electronic.setPluggedIn(random.nextBoolean());
            System.out.println(electronic.getTitle() + ": " + electronic.isPluggedIn());
        }
    }
}
