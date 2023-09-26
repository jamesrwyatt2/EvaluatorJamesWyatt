package james;


import java.security.SecureRandom;
import java.sql.Time;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Evaluator is used to evaluate each of the following sort methods
 * Methods for sort figure 19.4, 19.5, 19.6
 * Reverse and array reference page 622
 * Display a Table page 44
 * @author james wyatt
 */
public class Evaluator {

    // Sort array using selection Sort
    public static void selectionSortWyatt(int[] data) {
        // Loop over data.length-1 elements
        for(int i = 0; i < data.length - 1; i++) {
            int smallest = i;

            // Loop to find index of smallest element
            for(int index = i + 1; index < data.length; index ++) {
                if(data[index] < data[smallest]) {
                    smallest = index;
                }
            }
            swap(data, i, smallest);
        }
    }

    // Helper method to swap values in two elements
    private static void swap(int[] data, int first, int second) {
        int temporary = data[first];
        data[first] = data[second];
        data[second] = temporary;
    }

    // Sort array using insertion sort
    public static void insertionSortWyatt(int[] data) {
        // Loop over data.length-1 elements
        for(int next = 1; next < data.length; next++) {
            int insert = data[next]; //value to insert
            int moveItem = next;

            while(moveItem > 0 && data[moveItem-1] > insert) {
                //shift elements right one slot
                data[moveItem] = data[moveItem-1];
                moveItem--;
            }

            data[moveItem] = insert; // Place inserted element
        }
    }

    // Calls recursive sortArray method to begin merge sorting
    public static void mergeSortWyatt(int[] data) {
        sortArray(data, 0, data.length-1);
    }

    // Splits array, sorts subarray and merges sub arrays into sorted array
    private static void sortArray(int[] data, int low, int high) {
        // Test base case: size of array equals 1
        if((high - low) >= 1) { // if not base case
            int middle1 = (low + high) / 2; // Calculates middle of array
            int middle2 = middle1 + 1; // calculates next element over

            // split array in half; sort each half (recursive calls)
            sortArray(data, low, middle1);
            sortArray(data, middle2, high);

            // Merge two sorted arrays after split calls return
            merge(data, low, middle1, middle2, high);
        }
    }

    //merger two sorted sub arrays into one sorted subarray
    private static void merge(int[] data, int left, int middle1, int middle2, int right) {
        int leftIndex = left; // index into left subarray
        int rightIndex = middle2; // index into right subarray
        int combinedIndex = left; // index into temporary working array
        int[] combined = new int[data.length]; // Working array
        // While arrays until reaching end of either
        while(leftIndex <= middle1 && rightIndex <= right) {
            if(data[leftIndex] <= data[rightIndex]) {
                combined[combinedIndex++] = data[leftIndex++];
            }
            else {
                combined[combinedIndex++] = data[rightIndex++];
            }
        }
        // If left array is empty
        if(leftIndex == middle2) {
            //copy in rest of right array
            while(rightIndex <= right) {
                combined[combinedIndex++] = data[rightIndex++];
            }
        }
        else { // If right array is empty
            while(leftIndex <= middle1) {
                combined[combinedIndex++] = data[leftIndex++];
            }
        }
        // Copy values back into original array
        for(int i = left; i <= right; i++) {
            data[i] = combined[i];
        }
    }

    // Returns an Array 100,000 in order 1 to 100,000
    public static int[] arrayRandomSorted(){
        SecureRandom generator = new SecureRandom();
        return generator.ints(1000,1,1000).sorted().toArray();
    }

    // Returns an Array 100,000 no order
    public static int[] arrayRandomUnsorted(){
        SecureRandom generator = new SecureRandom();
        return generator.ints(1000,1,1000).toArray();
    }

    // Returns an Array 100,000 in reverse order 100,000 to 1
    // Generate int[], stream into List<Integer> for reverse, stream back into int[]
    public static int[] arrayRandomSortedReverse(){
        SecureRandom generator = new SecureRandom();
        int[] intArray = generator.ints(1000,1,1000).sorted().toArray();
        List<Integer> myList = new java.util.ArrayList<>(Arrays.stream(intArray).boxed().toList());
        Collections.reverse(myList);
        return myList.stream().mapToInt(i->i).toArray();
    }

    // Method to get best time from the 3 test
    public static long findBest(long sortTimeOne, long sortTimeTwo, long sortTimeThree) {
        long best;
        if(sortTimeOne < sortTimeTwo && sortTimeOne < sortTimeThree){
            best = sortTimeOne;
        }
        else if(sortTimeTwo < sortTimeOne && sortTimeTwo < sortTimeThree) {
            best = sortTimeTwo;
        }
        else {
            best = sortTimeThree;
        }
        return best;
    }
    // Method to get average time from the test
    public static long findAverage(long sortTimeOne, long sortTimeTwo, long sortTimeThree) {
        return (sortTimeOne + sortTimeTwo + sortTimeThree) / 3;
    }
    // Method to get worse time from the test
    public static long findWorse(long sortTimeOne, long sortTimeTwo, long sortTimeThree) {
        // find worse
        long worse;
        if(sortTimeOne > sortTimeTwo && sortTimeOne > sortTimeThree){
            worse = sortTimeOne;
        }
        else if(sortTimeTwo > sortTimeOne && sortTimeTwo > sortTimeThree) {
            worse = sortTimeTwo;
        }
        else {
            worse = sortTimeThree;
        }
        return worse;
    }


    // Main method running data sort test
    public static void main(String[] arg) {

        // Generate Data of different types
        int[] dataRandomSorted = arrayRandomSorted();
        int[] dataRandomUnsorted = arrayRandomUnsorted();
        int[] dataRandomReverseSorted = arrayRandomSortedReverse();
        // Start and end time for each test
        long startTime;
        long endTime;
        // Sort times for each test
        long sortTimeOne;
        long sortTimeTwo;
        long sortTimeThree;
        // Best, average, worse times for each test
        long best;
        long average;
        long worse;

        // Run Selection Sort for all three data sets
        startTime = System.nanoTime();
        selectionSortWyatt(dataRandomSorted);
        endTime = System.nanoTime();
        sortTimeOne = endTime - startTime;

        startTime = System.nanoTime();
        selectionSortWyatt(dataRandomUnsorted);
        endTime = System.nanoTime();
        sortTimeTwo = endTime - startTime;

        startTime = System.nanoTime();
        selectionSortWyatt(dataRandomReverseSorted);
        endTime = System.nanoTime();
        sortTimeThree = endTime - startTime;

        // find Average
        best = findBest(sortTimeOne, sortTimeTwo, sortTimeThree);
        average = findAverage(sortTimeOne, sortTimeTwo, sortTimeThree);
        worse = findWorse(sortTimeOne, sortTimeTwo, sortTimeThree);


        // Create table Header
        System.out.println("\t\t\t\tBEST\t\tAVERAGE\t\tWORST ");
        // Add line to table
        System.out.printf("SELECTION SORT:\t%s\t\t%s\t\t%s\n",best,average,worse);
        // update data
        dataRandomSorted = arrayRandomSorted();
        dataRandomUnsorted = arrayRandomUnsorted();
        dataRandomReverseSorted = arrayRandomSortedReverse();

        // Run Insertion Sort for all three data sets insertionSortWyatt
        startTime = System.nanoTime();
        insertionSortWyatt(dataRandomSorted);
        endTime = System.nanoTime();
        sortTimeOne = endTime - startTime;

        startTime = System.nanoTime();
        insertionSortWyatt(dataRandomUnsorted);
        endTime = System.nanoTime();
        sortTimeTwo = endTime - startTime;

        startTime = System.nanoTime();
        insertionSortWyatt(dataRandomReverseSorted);
        endTime = System.nanoTime();
        sortTimeThree = endTime - startTime;

        // find Average
        best = findBest(sortTimeOne, sortTimeTwo, sortTimeThree);
        average = findAverage(sortTimeOne, sortTimeTwo, sortTimeThree);
        worse = findWorse(sortTimeOne, sortTimeTwo, sortTimeThree);
        //Add line to table
        System.out.printf("INSERTION SORT:\t%s\t\t%s\t\t%s\n",best,average,worse);

        // Run Merge Sort for all three data sets
        startTime = System.nanoTime();
        mergeSortWyatt(dataRandomSorted);
        endTime = System.nanoTime();
        sortTimeOne = endTime - startTime;

        startTime = System.nanoTime();
        mergeSortWyatt(dataRandomUnsorted);
        endTime = System.nanoTime();
        sortTimeTwo = endTime - startTime;

        startTime = System.nanoTime();
        mergeSortWyatt(dataRandomReverseSorted);
        endTime = System.nanoTime();
        sortTimeThree = endTime - startTime;

        // find Average
        best = findBest(sortTimeOne, sortTimeTwo, sortTimeThree);
        average = findAverage(sortTimeOne, sortTimeTwo, sortTimeThree);
        worse = findWorse(sortTimeOne, sortTimeTwo, sortTimeThree);
        //Add line to table
        System.out.printf("MERGE SORT:\t\t%s\t\t%s\t\t%s",best,average,worse);

    }
}
