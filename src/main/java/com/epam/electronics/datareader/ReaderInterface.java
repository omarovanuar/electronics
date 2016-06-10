package com.epam.electronics.datareader;

import com.epam.electronics.entity.Electronic;

import java.io.IOException;
import java.util.List;

public interface ReaderInterface {
    List<Electronic> readData() throws IOException;
}
