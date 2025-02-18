import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

public class main {
    public static boolean compareArrays(char[] a, ArrayList<Character> b) {
        // we have to sort, otherwise we'll encounter this problem where even if the correct guess characters are collected, the order will be bad
        // |s|cream
        // |a|ecrsm  -> comparison fails!
        Arrays.sort(a);
        String convert = b.toString(); // fixme: converting to string means adding '[' and ']' needlessly
        char[] bb = convert.toCharArray();
        Arrays.sort(bb);

        // compare
        for (int i = 0; i < a.length; i++) {
            if (bb[i] != a[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hangman - Guess the 6 letter, lowercase letter only word!");

        // 1. take random word and convert into chararray
        Random random = new Random();
        int RNG = random.nextInt(1, 15);
        String[] wordArray = {"stride", "retain", "coerce", "symbol", "person", "scream", "murder", "volume", "devote", "wander", "create", "flight", "rotate", "junior", "harbor"};
        char[] charArray = wordArray[RNG].toCharArray();

        // 2. take guess from user
        InputStreamReader InputObj = new InputStreamReader(System.in);
        BufferedReader BufferObj = new BufferedReader(InputObj);

        boolean gameWon = false;
        while(!gameWon) {
            System.out.println("Guess a character: ");
            char c = 'A';
            try {
                c = (char) BufferObj.read();
                BufferObj.read(); // omit '\n' going after previous char
            } catch (IOException e) {        // expections in java must be handled
                e.printStackTrace();
            }

            ArrayList<Character> correctGuesses = new ArrayList<Character>();   // we use arraylist instead of vectors
            ArrayList<Character> incorrectGuesses = new ArrayList<Character>();

            // 3. check if we have guesses remaining
            int incorrectGuessAmount = 0;
            if (incorrectGuessAmount == 10) {
                System.out.println("You ran out of guesses. Game lost!");
                System.exit(0);
            }

            // 4.a check if guess was correct
            boolean guessedCorrectly = false;
            for (int i = 0; i < charArray.length; i++) {
                if (!guessedCorrectly) {
                    if (c == charArray[i]) {
                        System.out.println("Your guess was correct!");
                        correctGuesses.add(c);
                        guessedCorrectly = true;
                    }
                }
            }

            // winning condition
            if (!correctGuesses.isEmpty()) {
                gameWon = compareArrays(charArray, correctGuesses);
            }

            // 4.b incorrect guess
            if (!guessedCorrectly) {
                System.out.println("Your guess was incorrect!");
                incorrectGuessAmount++;
                incorrectGuesses.add(c);
                guessedCorrectly = false;
            }
            // fixme: incorrect guesses aren't rendered after 2nd turn
            System.out.println("Incorrect guesses: " + incorrectGuesses);
        }
    }
}