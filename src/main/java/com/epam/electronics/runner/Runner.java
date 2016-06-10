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
    private static final String TXT_PATH = "electronics.txt";
    private static final String XML_PATH = "electronics.xml";

    public static void main(String[] args) throws InvalidNewItemException {
        List<Electronic> list;

        try {
            list = new DBReader().readData();
            list.addAll(new TxtFileReader(TXT_PATH).readData());
            list.addAll(new XMLReader(XML_PATH).readData());
            Utils.sortByPrice(list);
            Utils.plugIn(list);
            Utils.calculateTotalConsumption(list);
            System.out.println("Type min of power capacity (W)");
            double min = new Scanner(System.in).nextDouble();
            System.out.println("Type max of power capacity (W)");
            double max = new Scanner(System.in).nextDouble();
            Utils.findByPowerCapacityRange(min, max, list);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect value!");
        } catch (PowerCapacityRangeException | NoElectronicsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something is wrong with your IO");
        }
    }

}