package com.epam.electronics.datareader;

import com.epam.electronics.entity.*;
import com.epam.electronics.entity.ElectronicFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader implements ReaderInterface {
    private String filePath;

    public TxtFileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Electronic> readData() {
        List<Electronic> electronics = new ArrayList<>();
        String tempString;
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((tempString = bufferedReader.readLine()) != null) {
                String[] strings = tempString.split(";");
                electronics.add(ElectronicFactory.createElectronic(strings));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Parameters of current Electronic is incorrect");
        } catch (IOException  e) {
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
}
