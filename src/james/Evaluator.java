package james;


import java.security.SecureRandom;
import java.util.Arrays;

public class Evaluator {

    // Sort array using selection Sort
    public static void selectionSort(int[] data) {
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

    public static void insertionSort(int[] data) {
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
    public static void mergeSort(int[] data) {
        sortArray(data, 0, data.length-1);
    }

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

    public static void main(String[] arg) {
        SecureRandom generator = new SecureRandom();

        // Create unordered array of 10 random ints
        int[] data = generator.ints(10,10,91).toArray();

        System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
//        selectionSort(data);
//        insertionSort(data);
        mergeSort(data);
        System.out.printf("Sorted array: %s%n%n", Arrays.toString(data));

    }
}
