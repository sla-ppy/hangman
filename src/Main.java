import java.util.Random;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // rng 1 string and convert to char[]
        //String[] wordArray = {"stride", "retain", "coerce", "symbol", "person", "scream", "murder", "volume", "devote", "wander", "create", "flight", "rotate", "junior", "harbor"};
        String[] wordArray = {"create"};
        //Random random = new Random();
        //int RNG = random.nextInt(1, wordArray.length);
        //char[] charArray = wordArray[RNG].toCharArray();
        char[] charArray = wordArray[0].toCharArray();

        // needed for taking input
        InputStreamReader InputObj = new InputStreamReader(System.in);
        BufferedReader BufferObj = new BufferedReader(InputObj);

        ArrayList<Character> incorrectGuesses = new ArrayList<Character>(); // we use arraylist instead of vectors
        ArrayList<Character> remainingGuesses = new ArrayList<Character>();
        char[] guessedChars = {'_', '_', '_', '_', '_', '_'};

        System.out.println("Hangman - Guess the 6 letter, lowercase only word!");

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
                }
            }
            if (gameWon) {
                break;
            }

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
            if (!guessedCorrectly) {
                for (char value : charArray) {
                    if (c == value) {
                        System.out.println("Your guess was correct!");
                        guessedCorrectly = true;
                        break;
                    }
                }
            };
            if (guessedCorrectly) {
                for (int i = 0; i < charArray.length; i++) {
                    if (c == charArray[i]) {
                        guessedChars[i] = charArray[i];
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
        }
    }
}