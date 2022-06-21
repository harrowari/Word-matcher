import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

// Parse text file as array list.

public class part1 {

    public static ArrayList<String> arrayListInput(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                String word = scanner.next().replaceAll("[^a-zA-Z\\s]", " ");
                list.add(word.toLowerCase()); // ensure that word matches are not missed due to case. also, compareTo function used in merge sort is case dependent.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> wordsListInput(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine().toLowerCase()); // no regex applied as words list is consistently formatted
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> filteredArrayList(ArrayList<String> x, ArrayList<String> y) {
        ArrayList<String> common_words = new ArrayList<String>();

        for (String temp : y) { // iterate through the two array lists to find word matches
            if (x.contains(temp)) {
                common_words.add(temp);
            }
        }
        return common_words;
    }

    ArrayList<String> textList = part1.arrayListInput("");
    ArrayList<String> wordsList = part1.wordsListInput("");
    ArrayList<String> forSorting = part1.filteredArrayList(wordsList, textList);
    

    public static void main(String[] args) {

        // code to test output
        ArrayList<String> textList = part1.arrayListInput("");
        ArrayList<String> wordsList = part1.wordsListInput("");
        ArrayList<String> forSorting = part1.filteredArrayList(wordsList, textList);

        System.out.println(forSorting);
        
    }
}