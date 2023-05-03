package application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextReader {
    private static final String PATH = "src/main/resources/names.txt";
    private static List<String> namesList = new ArrayList<>();

    public static List<String> reader() {
        try {
            namesList = Files.readAllLines(Paths.get(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return namesList;
    }
}
