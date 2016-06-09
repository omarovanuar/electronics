package com.epam.electronics.datareader;

import com.epam.electronics.entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader implements ReaderInterface {
    public static final String FILE_PATH = "electronics.txt";
    private List<Electronic> electronics = new ArrayList<>();

    @Override
    public List<Electronic> readData() {
        String tempString;
        File file = new File(FILE_PATH);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((tempString = bufferedReader.readLine()) != null) {
                String[] strings = tempString.split(":");
                electronics.add(newElectronicFromFile(strings));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return electronics;
    }

    private Electronic newElectronicFromFile(String[] strings) throws IOException {
        Electronic electronic;
        switch (strings[0].toUpperCase()) {
            case "FRIDGE":
                electronic = new Fridge(Integer.valueOf(strings[1]), strings[2], Double.valueOf(strings[3]),
                        Double.valueOf(strings[4]), strings[5], Integer.valueOf(strings[6]), Double.valueOf(strings[7]));
                break;
            case "VACUUM CLEANER":
                electronic = new VacuumCleaner(Integer.valueOf(strings[1]), strings[2], Double.valueOf(strings[3]),
                        Double.valueOf(strings[4]), Double.valueOf(strings[5]));
                break;
            case "WASHING MACHINE":
                electronic = new WashingMachine(Integer.valueOf(strings[1]), strings[2], Double.valueOf(strings[3]),
                        Double.valueOf(strings[4]), strings[5], Integer.valueOf(strings[6]), Double.valueOf(strings[7]));
                break;
            case "TV":
                electronic = new TV(Integer.valueOf(strings[1]), strings[2], Double.valueOf(strings[3]),
                        Double.valueOf(strings[4]), Double.valueOf(strings[5]), Double.valueOf(strings[6]));
                break;
            case "LAPTOP":
                electronic = new Laptop(Integer.valueOf(strings[1]), strings[2], Double.valueOf(strings[3]),
                        Double.valueOf(strings[4]), Double.valueOf(strings[5]), Double.valueOf(strings[6]));
                break;
            default:
                throw new IOException();
        }
        return electronic;
    }


}
