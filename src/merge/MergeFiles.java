package merge;


import java.io.*;
import java.util.*;


public class MergeFiles {
    private String[] words;


    public MergeFiles(String[] words) {
        this.words = words;
    }


    public void setWords(String[] words) {
        this.words = words;
    }


    public String[] getWords() {
        return words;
    }


    public String[] alphabetize() {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words.length - i - 1; j++) {
                if (words[j].compareToIgnoreCase(words[j + 1]) > 0) {
                    String temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }
        return words;
    }


    public void alphabetizedWords() {
        String[] alphabetizedWords = alphabetize();
        System.out.println(Arrays.toString(alphabetizedWords));
    }


    public static void main(String[] args) {
        String[] file1Words = readWordsFromFile("C:\\Users\\akonz\\eclipse-workspace\\MergeFiles_OdunaAkonzee_052223_pd4\\src\\merge\\abc_A.cvhs.txt");
        String[] file2Words = readWordsFromFile("C:\\Users\\akonz\\eclipse-workspace\\MergeFiles_OdunaAkonzee_052223_pd4\\src\\merge\\abc_B.cvhs.txt");


        String[] allWords = new String[file1Words.length + file2Words.length];
        System.arraycopy(file1Words, 0, allWords, 0, file1Words.length);
        System.arraycopy(file2Words, 0, allWords, file1Words.length, file2Words.length);


        MergeFiles merge = new MergeFiles(allWords);
        merge.alphabetizedWords();


        // Write the array to a new file called abc_C.cvhs
        try (FileWriter writer = new FileWriter("C:\\Users\\akonz\\eclipse-workspace\\MergeFiles_OdunaAkonzee_052223_pd4\\src\\merge\\abc_C.cvhs.txt")) {
            for (String word : allWords) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String[] readWordsFromFile(String filename) {
        ArrayList<String> wordsList = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split line into words
                wordsList.addAll(Arrays.asList(words));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return wordsList.toArray(new String[0]);
    }
}
