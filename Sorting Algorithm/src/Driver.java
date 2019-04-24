import java.util.Arrays;
import java.util.Random;

/**
 * Console program that creates an array of random integers and then uses a sorting algorithm to sort it
 * @author Brandon Skar
 * @version 1.0
 */
public class Driver
{
    private static final int ARRAY_SIZE = 45;
    private static final int GROUP_SIZE = 7;
    private static final int RANDOM_NUMBERS = 100;

    /**
     * Generates an array of random integers and displays the work of the sorting algorithm
     * one step at a time
     * @param args Main method
     */
    public static void main(String[] args)
    {
        SortingAssignment object = new SortingAssignment();
        Random rand = new Random();
        int[] arr = new int[ARRAY_SIZE];
        for(int i = 0; i < arr.length; i++) {
            int num = rand.nextInt(RANDOM_NUMBERS + 1);
            arr[i] = num;
        }

        //show what we are generating
        System.out.println("Generating an array of size " +
                arr.length + " with elements in the range of [" + 1 + "," + RANDOM_NUMBERS + "]");

        //original array
        System.out.print("Original: ");
        System.out.println(Arrays.toString(arr));

        //sorting with # groups
        System.out.println("Sorting with " + GROUP_SIZE + " groups");

        object.sort(arr, GROUP_SIZE);

        //sorted array
        System.out.print("Sorted: ");
        System.out.println(Arrays.toString(arr));

        //inverse detected
        System.out.println("Detected inversions? " + isSorted(arr));
    }

    private static boolean isSorted(int[] arr)
    {
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > (arr[i + 1])) {
                return true;
            }
        }
        return false;
    }
}
