package com.epam.electronics.runner;

import com.epam.electronics.datareader.DBReader;
import com.epam.electronics.datareader.ReaderInterface;
import com.epam.electronics.datareader.TxtFileReader;
import com.epam.electronics.datareader.XMLReader;
import com.epam.electronics.datawriter.TxtFileWriter;
import com.epam.electronics.entity.Electronic;
import com.epam.electronics.entity.ElectronicFactory;
import com.epam.electronics.exception.InvalidNewItemException;
import com.epam.electronics.exception.NoElectronicsException;
import com.epam.electronics.exception.PowerCapacityRangeException;
import com.epam.electronics.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// TODO: 6/10/2016 change runner without scanner
public class Runner {
    private static final String FILE_PATH = "electronics.txt";

    public static void main(String[] args) throws InvalidNewItemException {
        boolean repeat = true;

        List<Electronic> list = new ArrayList<>();
        ReaderInterface reader;

        while(repeat) {
            System.out.println("1: read from file");
            System.out.println("2: read from h2");
            System.out.println("3: read from xml");
            System.out.println("4: add new electronic to the list");
            System.out.println("5: sort list by price");
            System.out.println("6: find electronics by consumption range");
            System.out.println("7: plug in some electronics");
            System.out.println("8: calculate total consumption");
            System.out.println("9: show list");
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
                        reader = new TxtFileReader(FILE_PATH);
                        list = reader.readData();
                        break;

                    case 2:
                        reader = new DBReader();
                        list = reader.readData();
                        break;

                    case 3:
                        reader = new XMLReader();
                        list = reader.readData();
                        break;

                    case 4:
                        Electronic electronicFromInput = ElectronicFactory.createFromInput();
                        list.add(electronicFromInput);
                        TxtFileWriter.writeData(electronicFromInput, FILE_PATH);
                        break;

                    case 5:
                        Utils.sortByPrice(list);
                        break;

                    case 6:
                        System.out.println("Type min of consumption");
                        double min = new Scanner(System.in).nextDouble();
                        System.out.println("Type max of consumption");
                        double max = new Scanner(System.in).nextDouble();
                        Utils.findByPowerCapacityRange(min, max, list);
                        break;

                    case 7:
                        Utils.plugIn(list);
                        break;

                    case 8:
                        Utils.calculateTotalConsumption(list);
                        break;

                    case 9:
                        if (list.isEmpty()) {
                            throw new NoElectronicsException();
                        }
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
            } catch (InvalidNewItemException | PowerCapacityRangeException | NoElectronicsException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Something is wrong with your IO");
            }

        }

    }

}