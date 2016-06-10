package com.epam.electronics.parser;

import com.epam.electronics.entity.*;
import com.epam.electronics.entity.electronic_name.Name;
import com.epam.electronics.exception.InvalidNewItemException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.IOException;
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
        if (qName.equals("electronic")) {
                list.add(electronic);
        } else {
            String elementVal = thisElement.toString();
            if (qName.equals("id")) {
                electronic.setId(Integer.parseInt(elementVal));
            } else if (qName.equals("title")) {
                electronic.setTitle(elementVal);
            } else if (qName.equals("price")) {
                electronic.setPrice(Double.parseDouble(elementVal));
            } else if (qName.equals("powercapacity")) {
                electronic.setPowerCapacity(Double.parseDouble(elementVal));
            } else if (qName.equals("dimension")) {
                if (type.equals(FRIDGE)) {
                    Fridge fridge = (Fridge) electronic;
                    fridge.setDimension(elementVal);
                } else if (type.equals(WASHINGMACHINE)){
                    WashingMachine washingMachine = (WashingMachine) electronic;
                    washingMachine.setDimension(elementVal);
                }
            } else if (qName.equals("section")) {
                Fridge fridge = (Fridge) electronic;
                fridge.setSectionNumber(Integer.parseInt(elementVal));
            } else if (qName.equals("freezetime")) {
                Fridge fridge = (Fridge) electronic;
                fridge.setFreezeTime(Double.parseDouble(elementVal));
            } else if (qName.equals("dustcc")) {
                VacuumCleaner vacuumCleaner = (VacuumCleaner) electronic;
                vacuumCleaner.setDustCollectorCapacity(Double.parseDouble(elementVal));
            } else if (qName.equals("functions")) {
                WashingMachine washingMachine = (WashingMachine) electronic;
                washingMachine.setFunctionsNumber(Integer.parseInt(elementVal));
            } else if (qName.equals("maxLinenWeight")) {
                WashingMachine washingMachine = (WashingMachine) electronic;
                washingMachine.setMaxLinenWeight(Double.parseDouble(elementVal));
            } else if (qName.equals("diagonal")) {
                if (type.equals(TV)) {
                    TV tv = (TV) electronic;
                    tv.setDiagonal(Double.parseDouble(elementVal));
                } else if (type.equals(LAPTOP)){
                    Laptop laptop = (Laptop) electronic;
                    laptop.setDiagonal(Double.parseDouble(elementVal));
                }
            } else if (qName.equals("brightness")) {
                TV tv = (TV) electronic;
                tv.setBrightness(Double.parseDouble(elementVal));
            } else if (qName.equals("hardDriveCapacity")) {
                Laptop laptop = (Laptop) electronic;
                laptop.setHardDriveCapacity(Double.parseDouble(elementVal));
            }
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
