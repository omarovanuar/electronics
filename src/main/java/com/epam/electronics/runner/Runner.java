package com.epam.electronics.runner;

import com.epam.electronics.datareader.ReaderInterface;
import com.epam.electronics.datareader.TxtFileReader;
import com.epam.electronics.entity.Electronic;
import com.epam.electronics.exception.InvalidNewItemException;
import com.epam.electronics.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws InvalidNewItemException {
        boolean repeat = true;

        List<Electronic> list = new ArrayList<>();
        ReaderInterface reader;

        while(repeat) {
            System.out.println("1: read from file");
            System.out.println("2: add new electronic to the list");
            System.out.println("3: sort list by price");
            System.out.println("4: find electronics by consumption range");
            System.out.println("5: plug in some electronics");
            System.out.println("6: calculate total consumption");
            System.out.println("7: show list");
            System.out.println("0: exit");

            try {
                Scanner scanner = new Scanner(System.in);
                int action = scanner.nextInt();

                switch (action) {
                    case 0:
                        repeat = false;
                        System.out.println("Completed!");
                        break;

                    case 1:
                        reader = new TxtFileReader();
                        list = reader.readData();
                        break;

                    case 2:
                        list.add(Utils.addNewElectronic());
                        break;

                    case 3:
                        Utils.sortByPrice(list);
                        break;

                    case 4:
                        System.out.println("Type min of consumption");
                        double min = new Scanner(System.in).nextDouble();
                        System.out.println("Type max of consumption");
                        double max = new Scanner(System.in).nextDouble();
                        Utils.findByPowerCapacityRange(min, max, list);
                        break;

                    case 5:
                        Utils.plugIn(list);
                        break;

                    case 6:
                        Utils.calculateTotalConsumption(list);
                        break;

                    case 7:
                        for (Electronic entity : list) {
                            System.out.println(entity.toString());
                        }
                        break;

                    default:
                        System.out.println("Incorrect value! Please, try again!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect value! Please, try again!");
            } catch (InvalidNewItemException | IOException e) {
                e.getMessage();
            }

        }

    }

}