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

            data[moveItem] = insert;
        }

    }

    public static void main(String[] arg) {
        SecureRandom generator = new SecureRandom();

        // Create unordered array of 10 random ints
        int[] data = generator.ints(10,10,91).toArray();

        System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
//        selectionSort(data);
        insertionSort(data);
        System.out.printf("Sorted array: %s%n%n", Arrays.toString(data));

    }
}
