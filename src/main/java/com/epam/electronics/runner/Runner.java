package com.epam.electronics.runner;

import com.epam.electronics.datareader.DBReader;
import com.epam.electronics.datareader.TxtFileReader;
import com.epam.electronics.datareader.XMLReader;
import com.epam.electronics.datawriter.TxtFileWriter;
import com.epam.electronics.entity.Electronic;
import com.epam.electronics.exception.NoElectronicsException;
import com.epam.electronics.exception.PowerCapacityRangeException;
import com.epam.electronics.util.Utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Runner {
    private static final String TXT_READ_PATH = "electronics.txt";
    private static final String TXT_WRITE_PATH = "electronics_result.txt";
    private static final String XML_PATH = "electronics.xml";

    public static void main(String[] args) {
        List<Electronic> list;

        try {
            list = new DBReader().readData();
            list.addAll(new TxtFileReader(TXT_READ_PATH).readData());
            list.addAll(new XMLReader(XML_PATH).readData());

            Utils.sortByPrice(list);

            Utils.plugIn(list);
            Utils.calculateTotalConsumption(list);

            System.out.println("Type min of power capacity (W)");
            double min = new Scanner(System.in).nextDouble();
            System.out.println("Type max of power capacity (W)");
            double max = new Scanner(System.in).nextDouble();
            List<Electronic> searchResult = Utils.findByPowerCapacityRange(min, max, list);
            searchResult.forEach(System.out::println);

            for (Electronic electronic : searchResult) {
                TxtFileWriter.writeData(electronic, TXT_WRITE_PATH);
            }

        } catch (InputMismatchException e) {
            System.out.println("Incorrect value!");
        } catch (PowerCapacityRangeException | NoElectronicsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something is wrong with your IO");
        }
    }

}