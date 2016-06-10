package com.epam.electronics.parser;

import com.epam.electronics.entity.Electronic;
import com.epam.electronics.entity.ElectronicFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElectronicSAXParser extends DefaultHandler{
    private List<Electronic> list = new ArrayList<>();
    private String[] electronic = new String[8];
    private StringBuilder thisElement;
    private int i;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML document with electronics...");
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        thisElement = new StringBuilder();
        if (qName.equals("electronic")) {
            i = 0;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        if (qName.equals("electronic")) {
            try {
                list.add(ElectronicFactory.createFromFile(electronic));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String elementVal = thisElement.toString();
            if (qName.equals("type")) {
                electronic[i] = elementVal;
            } else if (qName.equals("id")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("title")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("price")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("powercapacity")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("dimension")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("section")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("freezetime")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("dustcc")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("functions")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("maxLinenWeight")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("diagonal")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("brightness")) {
                electronic[++i] = elementVal;
            } else if (qName.equals("hardDriveCapacity")) {
                electronic[++i] = elementVal;
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
