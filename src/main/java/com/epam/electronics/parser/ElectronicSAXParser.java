package com.epam.electronics.parser;

import com.epam.electronics.entity.*;
import com.epam.electronics.entity.electronic_name.Name;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static com.epam.electronics.entity.electronic_name.Name.*;

public class ElectronicSAXParser extends DefaultHandler{
    private List<Electronic> list = new ArrayList<>();
    private Electronic electronic;
    private Name type;
    private StringBuilder thisElement;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML document with electronics...");
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        thisElement = new StringBuilder();
        if (qName.equals("electronic")) {
            type = Name.valueOf(atts.getValue("type").toUpperCase());
            switch (type) {
                case FRIDGE:
                    electronic = new Fridge();
                    break;
                case VACUUMCLEANER:
                    electronic = new VacuumCleaner();
                    break;
                case WASHINGMACHINE:
                    electronic = new WashingMachine();
                    break;
                case TV:
                    electronic = new TV();
                    break;
                case LAPTOP:
                    electronic = new Laptop();
                    break;
                default:
                    throw new RuntimeException();
            }
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        String elementVal = thisElement.toString();
        switch (qName) {
            case "electronic":
                list.add(electronic);
                break;
            case "id":
                electronic.setId(Integer.parseInt(elementVal));
                break;
            case "title":
                electronic.setTitle(elementVal);
                break;
            case "price":
                electronic.setPrice(Double.parseDouble(elementVal));
                break;
            case "powercapacity":
                electronic.setPowerCapacity(Double.parseDouble(elementVal));
                break;
            case "dimension":
                Fridge fridge;
                WashingMachine washingMachine;
                if (type.equals(FRIDGE)) {
                    fridge = (Fridge) electronic;
                    fridge.setDimension(elementVal);
                } else if (type.equals(WASHINGMACHINE)) {
                    washingMachine = (WashingMachine) electronic;
                    washingMachine.setDimension(elementVal);
                }
                break;
            case "section":
                fridge = (Fridge) electronic;
                fridge.setSectionNumber(Integer.parseInt(elementVal));
                break;
            case "freezetime":
                fridge = (Fridge) electronic;
                fridge.setFreezeTime(Double.parseDouble(elementVal));
                break;
            case "dustcc":
                VacuumCleaner vacuumCleaner = (VacuumCleaner) electronic;
                vacuumCleaner.setDustCollectorCapacity(Double.parseDouble(elementVal));
                break;
            case "functions":
                washingMachine = (WashingMachine) electronic;
                washingMachine.setFunctionsNumber(Integer.parseInt(elementVal));
                break;
            case "maxLinenWeight":
                washingMachine = (WashingMachine) electronic;
                washingMachine.setMaxLinenWeight(Double.parseDouble(elementVal));
                break;
            case "diagonal":
                TV tv;
                Laptop laptop;
                if (type.equals(TV)) {
                    tv = (TV) electronic;
                    tv.setDiagonal(Double.parseDouble(elementVal));
                } else if (type.equals(LAPTOP)){
                    laptop = (Laptop) electronic;
                    laptop.setDiagonal(Double.parseDouble(elementVal));
                }
                break;
            case "brightness":
                tv = (TV) electronic;
                tv.setBrightness(Double.parseDouble(elementVal));
                break;
            case "hardDriveCapacity":
                laptop = (Laptop) electronic;
                laptop.setHardDriveCapacity(Double.parseDouble(elementVal));
                break;
        }
        thisElement = new StringBuilder();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Stop parse XML document with electronics...");
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        thisElement.append(ch, start, length);
    }

    public List<Electronic> getList() {
        return list;
    }
}
