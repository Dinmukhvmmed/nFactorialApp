package application.service;

import java.util.HashMap;

public class CreateBigram {
    private static HashMap<String, Integer> bigrams = new HashMap<>();

    public static HashMap<String, Integer> create() {
        for (String str : TextReader.reader()) {
            str = "^" + str + "$";
            for (int i = 1; i < str.length(); i++) {
                String letters = Character.toString(str.charAt(i - 1)) + Character.toString(str.charAt(i));
                addToMap(letters);
            }
        }
        return bigrams;
    }

    private static void addToMap(String letters) {
        if (!bigrams.containsKey(letters)) {
            bigrams.put(letters, 1);
        } else {
            bigrams.put(letters, bigrams.get(letters) + 1);
        }
    }
}
