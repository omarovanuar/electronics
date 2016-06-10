package com.epam.electronics.datareader;

import com.epam.electronics.entity.Electronic;
import com.epam.electronics.parser.ElectronicSAXParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader implements ReaderInterface {
    private String filePath;

    public XMLReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Electronic> readData() {
        List<Electronic> list = new ArrayList<>();
        SAXParserFactory spfac = SAXParserFactory.newInstance();
        SAXParser sp;
        ElectronicSAXParser handler;
        try {
            sp = spfac.newSAXParser();
            handler = new ElectronicSAXParser();
            sp.parse(new File(filePath), handler);
            list = handler.getList();
        } catch (ParserConfigurationException | SAXException | IOException | IndexOutOfBoundsException e) {
            System.out.println("Parsing fails: " + e.getMessage());
        }

        return list;
    }
}
