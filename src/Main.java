import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Main {

    static char[] underscoresForGuessing;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        userGuess(drawGuessingUnderscores(findRandomWord(fileReader())));
    }

    public static String findRandomWord (String[] words) {
        Random rand = new Random();
        return words[rand.nextInt(words.length - 1)];
    }

    public static char[] drawGuessingUnderscores(String word) {
        char[] wordToArray = word.toCharArray();
        underscoresForGuessing = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (wordToArray[i] == ' ') {
                underscoresForGuessing[i] = ' ';
            } else {
                underscoresForGuessing[i] = '_';
            }
        }
        return wordToArray;
    }

    public static void userGuess(char[] wordArray) {

        boolean run;
        String guess = "";
        char c = ' ';
        int[] k = new int[10];
        int lostCounter = 0;

        System.out.print("Make your guess for: ");
        System.out.println(underscoresForGuessing);
        do {
            int u = 0;
            try {
                guess = sc.nextLine();
                c = guess.charAt(0);
                if(guess.length() > 1) {
                    System.out.println("Only one value at a time. Try again.");
                    continue;
                }
                for (int i = 0; i < wordArray.length; i++) {
                    if (wordArray[i] == c) {
                        k[u] = i;
                        u++;
                    }
                }
                if (u == 0) {
                    lostCounter++;
                }
                for (int i = 0; i < u; i++) {
                    underscoresForGuessing[k[i]] = c;
                }
            } catch (Exception e) {
                System.out.println("Illegal value. Try again.");
            }
            System.out.println(underscoresForGuessing);
            int win = 0;

            for (int i = 0; i < underscoresForGuessing.length; i++) {
                if (underscoresForGuessing[i] == '_') {
                    win++;
                }
            }
            if (win == 0) {
                System.out.println("You win!");
                break;
            }
        } while (lostCounter != 6);
        if (lostCounter == 6) {
            System.out.println("You lost!");
        }
    }

    public static String[] fileReader() {
        int i = 0;
        String[] words = new String[1];
        File file = new File("src/words");
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                words[i] = line;
                String[] tempArray = words;
                words = new String[words.length + 1];
                for(int u = 0; u < tempArray.length; u++)
                    words[u] = tempArray[u];
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return words;
    }
}
