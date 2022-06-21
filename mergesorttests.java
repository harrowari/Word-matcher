import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

// This code is for calculating the time taken to sort the text by one hundred word intervals. The actual driver code for the merge sort is a duplicate of that 
// in the mergesort.java file.

public class mergesorttests {
    static ArrayList<String> textList = part1.arrayListInput("");
    static ArrayList<String> wordsList = part1.wordsListInput("");
    static ArrayList<String> forSorting = part1.filteredArrayList(wordsList, textList);
    public static Integer count = 0;
    static String[] forSortingArr = forSorting.toArray(new String[forSorting.size()]);    

    static String[] first100 = Arrays.copyOfRange(forSortingArr, 0, 100);
    static String[] second100 = Arrays.copyOfRange(forSortingArr, 100, 200);
    static String[] third100 = Arrays.copyOfRange(forSortingArr, 200, 300);
    static String[] fourth100 = Arrays.copyOfRange(forSortingArr, 300, 400);
    static String[] fifth100 = Arrays.copyOfRange(forSortingArr, 400, 500);

    public static void merge(String[] left_sort, String[] right_sort, String[] arr, int left_size, int right_size) {
        
        int i = 0, l = 0, r = 0;

        // iterate through the two arrays and move smallest elements (left vs right) into the new array 
        while(l<left_size && r<right_size) {
            if (left_sort[l].compareTo(right_sort[r]) < 0) { // sorting based on lexacographic comparison of two strings
                arr[i] = left_sort[l];
                l++;
            }
            else {
                arr[i] = right_sort[r];
                r++;
            }
            i++;
            count++; // count variable accumulates with every swap involved in the merge. 
        }

        // move any remaining elements in the left array into the new array
        while (l<left_size) {
            arr[i] = left_sort[l];
            l++;
            i++;
            count++;
        }
        // move any remaining elements in the right array into the new array
        while (r<right_size) {
            arr[i] = right_sort[r];
            r++;
            i++;
            count++;
        }
    }

    public static void mergeSort(String[] arr, int len) {
        if (len < 2) {return;} // basecase

        int mid = len/2;
        String [] left_sort = new String[mid];
        String [] right_sort = new String[len - mid];


        // Form two separate arrays 
        int j = 0;
        for (int i = 0; i < len; ++i) {

            if (i<mid) {
                left_sort[i] = arr[i];
            }
            else {
                right_sort[j] = arr[i];
                j = j+1;
            }
        }

        // Subarray division using recursion --> then sort these two subarrays
        mergeSort(left_sort, mid);
        mergeSort(right_sort, len-mid);

        // merge the separate, sorted    parts together
        merge(left_sort, right_sort, arr, mid, len-mid);

    }


    public static void main( String args[] ) {

        // time execution at 100 word intervals. 
        System.out.println("\n" + "");
        System.out.println("Performance data for individual 100 word segments.");
        System.out.println("\n" + "");

        long start_first = System.nanoTime();
        mergeSort(first100, first100.length);
        long estimatedTime_first = System.nanoTime() - start_first;

        long start_second = System.nanoTime();
        mergeSort(second100, second100.length);
        long estimatedTime_second = System.nanoTime() - start_second;

        long start_third = System.nanoTime();
        mergeSort(third100, third100.length);
        long estimatedTime_third = System.nanoTime() - start_third;

        long start_fourth = System.nanoTime();
        mergeSort(fourth100, third100.length);
        long estimatedTime_fourth = System.nanoTime() - start_fourth;

        long start_fifth = System.nanoTime();
        mergeSort(fifth100, fifth100.length);
        long estimatedTime_fifth = System.nanoTime() - start_fifth;

        System.out.println("First 100 took: " + estimatedTime_first + " nanoseconds");
        System.out.println("Second 100 took: " + estimatedTime_second + " nanoseconds");
        System.out.println("Third 100 took: " + estimatedTime_third + " nanoseconds");
        System.out.println("Fourth 100 took: " + estimatedTime_fourth + " nanoseconds");
        System.out.println("Fifth 100 took: " + estimatedTime_fifth + " nanoseconds");

        System.out.println("\n" + "");



    }
}

// code adapted from CMT219 week 8 lecture and lab exercise (Saxena, N., 2022). I have utilised the pseudocode given in the 
// lab exercise sheet and also the merge sort example provided, adapting this for use with a string array. 