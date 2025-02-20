import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        char[] chosenWord = Vocabulary.generate();

        // prepare guess structure equal in length to chosen word
        StringBuilder string = new StringBuilder();
        string.append("_".repeat(chosenWord.length));
        char[] guessedChars = new char[chosenWord.length];
        string.getChars(0, chosenWord.length, guessedChars, 0);

        // needed for taking input
        InputStreamReader InputObj = new InputStreamReader(System.in);
        BufferedReader BufferObj = new BufferedReader(InputObj);

        ArrayList<Character> incorrectGuesses = new ArrayList<>(); // we use arraylist instead of vectors

        System.out.println("Hangman - Guess the 6 letter, lowercase only word!");

        while(true) {
            // check if we have guesses remaining
            if (incorrectGuesses.size() == 10) {
                System.out.println("You ran out of lives. Game lost!");
                System.exit(0);
            }

            // take input
            System.out.println("Guess a character: ");
            char c = 'A';
            try {
                c = (char) BufferObj.read();
                c = Character.toLowerCase(c);
                BufferObj.read(); // omit '\n' going after previous char
            } catch (IOException e) {        // expections in java must be handled
                e.printStackTrace();
            }

            // check if guess was correct
            boolean guessedCorrectly = false;
            for (char value : chosenWord) {
                if (c == value) {
                    System.out.println("Your guess was correct!");
                    guessedCorrectly = true;
                    break;
                }
            }
            ;
            if (guessedCorrectly) {
                for (int i = 0; i < chosenWord.length; i++) {
                    if (c == chosenWord[i]) {
                        guessedChars[i] = chosenWord[i];
                    }
                }
            }

            // incorrect guess
            if (!guessedCorrectly) {
                System.out.println("Your guess was incorrect!");
                incorrectGuesses.add(c);
            }

            // game progress info
            System.out.println(guessedChars);
            System.out.println("Lives left: " + (10 - incorrectGuesses.size()));
            if (!incorrectGuesses.isEmpty()) {
                System.out.println(incorrectGuesses);
            }
            System.out.println("\r\n");

            // check if we won already
            for (int i = 0; i < guessedChars.length; i++) {
                if (guessedChars[i] == '_') {
                    break;
                }
                if (i == guessedChars.length -1) {
                    System.out.println("Game won!");
                    break;
                }
            }
        }
    }
}