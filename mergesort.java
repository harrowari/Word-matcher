import java.io.*;
import java.util.ArrayList;

// Main code including the merge sort, calculation of average run time and number of swaps/conversions. See mergesorttests.java for the
// calculation of runtime by 100 word intervals.  

class mergesort {

    // retrieve data for sorting from other classes
    static ArrayList<String> textList = part1.arrayListInput("");
    static ArrayList<String> wordsList = part1.wordsListInput("");
    static ArrayList<String> forSorting = part1.filteredArrayList(wordsList, textList);
    public static Integer count = 0; // for counting the number of swaps
    static String[] forSortingArr = forSorting.toArray(new String[forSorting.size()]); 
    // I have converted to an array as the array will be of fixed length thus I will not benefit from arraylist's 
    // ability to append faster - something which requires greater memory allocation to operate. 
  


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

        int time_test_iterations = 100; 
        long start = System.nanoTime();
        for (int i = 0; i < time_test_iterations; i++) {
            mergeSort(forSortingArr, forSortingArr.length);}
        long estimatedTime = System.nanoTime() - start;
        long tenaverage = estimatedTime / time_test_iterations;

        System.out.println("\n" + "");

        System.out.println("Sorted array: ");
        for (int i = 0; i < forSortingArr.length; ++i) {
            System.out.print(forSortingArr[i] + ", ");
        }
        System.out.println("\n" + "");

        System.out.println("Performance data");
        System.out.println("\n" + "");

        System.out.println("Estimated time (" + time_test_iterations + " test average): " + (tenaverage) + " nanoseconds.");
        System.out.println("Number of swaps: " + count);
        System.out.println("\n" + "");


    }
}