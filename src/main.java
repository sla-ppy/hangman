import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

public class main {
    public static void fillRemainingGuesses (ArrayList<Character> remainingGuesses) {
        char[] englishAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char c : englishAlphabet) {
            remainingGuesses.add(c);
        }
    }

    public static void main(String[] args) {
        // rng 1 string and convert to char[]
        String[] wordArray = {"stride", "retain", "coerce", "symbol", "person", "scream", "murder", "volume", "devote", "wander", "create", "flight", "rotate", "junior", "harbor"};
        Random random = new Random();
        int RNG = random.nextInt(1, wordArray.length);
        char[] charArray = wordArray[RNG].toCharArray();

        // needed for taking input
        InputStreamReader InputObj = new InputStreamReader(System.in);
        BufferedReader BufferObj = new BufferedReader(InputObj);

        ArrayList<Character> incorrectGuesses = new ArrayList<Character>(); // we use arraylist instead of vectors
        ArrayList<Character> remainingGuesses = new ArrayList<Character>();
        fillRemainingGuesses(remainingGuesses);
        char[] guessedChars = {'_', '_', '_', '_', '_', '_'};

        System.out.println("Hangman - Guess the 6 letter, lowercase only word!");
        String clearScreen = "\r\n\r\n\r\n";

        boolean gameWon = false;
        while(!gameWon) {
            // check if we won already
            for (int i = 0; i < guessedChars.length; i++) {
                if (guessedChars[i] == '_') {
                    break;
                }
                if (i == guessedChars.length -1) {
                    System.out.println("Game won!");
                    gameWon = true;
                    break;
                }
            }
            if (gameWon) {
                break;
            }

            // check if we have guesses remaining
            if (incorrectGuesses.size() == 10) {
                System.out.println("You ran out of guesses. Game lost!");
                System.exit(0);
            }

            // take input
            System.out.println("Guess a character: ");
            char c = 'A';
            try {
                c = (char) BufferObj.read();
                BufferObj.read(); // omit '\n' going after previous char
            } catch (IOException e) {        // expections in java must be handled
                e.printStackTrace();
            }

            // check if guess was correct
            boolean guessedCorrectly = false;
            for (int i = 0; i < charArray.length; i++) {
                if (!guessedCorrectly) {
                    if (c == charArray[i]) {
                        System.out.println("Your guess was correct!");
                        guessedChars[i] = charArray[i];
                        guessedCorrectly = true;
                        remainingGuesses.remove(Character.valueOf(c));
                        break;
                    }
                }
            }

            // incorrect guess
            if (!guessedCorrectly) {
                System.out.println("Your guess was incorrect!");
                incorrectGuesses.add(c);
                remainingGuesses.remove(Character.valueOf(c));
            }

            // game progress info
            System.out.println(guessedChars);
            System.out.println("You can make " + (10 - incorrectGuesses.size()) + " more wrong guesses!");
            System.out.println("Remaining guesses: " + remainingGuesses);
            System.out.println(clearScreen);
        }
    }
}