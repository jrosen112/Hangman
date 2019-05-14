import java.util.*;
import java.io.*;

public class Hangman {

    static class Letter {
        String actual;
        String guess;
        boolean guessed = false;

        public Letter(String s) {
            this.actual = s;
            this.guess = "_";
        }
    }

    public static void main(String[] args) {
        Scanner sc;
        ArrayList<String> array = new ArrayList<String>();
        try {
            sc = new Scanner(new File("words.txt"));
        } catch(FileNotFoundException e) {
            System.err.println("File not found.");
            return;
        }
        while(sc.hasNext()) {
            array.add(sc.nextLine()); // populates array with words from text file
        }
        Collections.shuffle(array); // randomizes array
        String word = array.get(0);
        hangman(word);
    }

    public static void hangman(String word) {
        boolean completed = false;
        int strikes = 0;
        int correct = 0;
        ArrayList<String> alreadyGuessed = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        ArrayList<Letter> letters = new ArrayList<Letter>();
        for(int i = 0; i < word.length(); i++) {
            Letter l = new Letter(""+word.charAt(i));
            letters.add(l);
        }
        for(int i = 0; i < word.length(); i++) {
            System.out.print("_ ");
        }
        System.out.println();
        while(!completed) {
            System.out.println("Guess one letter: ");
            String c = sc.nextLine();
            if(word.contains(c)) {
                for(Letter letter : letters) {
                    if(letter.actual.equals(c)) {
                        letter.guess = c;
                        correct++;
                    }
                }
                alreadyGuessed.add(c);
            } else {
                strikes++;
                alreadyGuessed.add(c);
            }
            System.out.println();
            for(Letter letter : letters) {
                System.out.print(letter.guess + " ");
            }
            System.out.println();
            System.out.print("Already guessed: ");
            for(String s : alreadyGuessed) {
                System.out.print(s + "  ");
            }
            System.out.println();
            if(strikes == 6) {
                System.out.println("Game over! The word was " + word);
                return;
            }
            System.out.println("Strikes: " + strikes + "/6");
            if(correct == word.length()) {
                System.out.println("Congratulations! You've won.");
                completed = true;
            }
        }
    }
}
