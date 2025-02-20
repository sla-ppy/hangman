import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Vocabulary {
    // the words contained in vocabulary.txt are 5-14 letter words, 10 of each length
    public static char[] generate() {
        // get strings from input file into ArrayList<String>
        ArrayList<String> wordsData = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/vocabulary.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordsData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // convert from ArrayList<String> to String[]
        String[] words = wordsData.toArray(new String[0]);

        // choose and return random word
        Random random = new Random();
        int RNG = random.nextInt(0, words.length);
        return words[RNG].toCharArray();
    }
}
