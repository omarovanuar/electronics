package com.epam.electronics.datawriter;

import com.epam.electronics.entity.Electronic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtFileWriter {

    public static void writeData(Electronic electronic, String filePath) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file, true);

        fileWriter.write("\n" + electronic.getClass().getSimpleName() +
                electronic.getId() + ":" +
                electronic.getTitle() + ":" +
                electronic.getPrice() + ":" +
                electronic.getPowerCapacity() + ":" +
                electronic.createFileData());
        fileWriter.close();
    }
}
