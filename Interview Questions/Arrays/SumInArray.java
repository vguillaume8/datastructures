import  java.util.Arrays;

/**
 * Finds the pair of integers that equal a given sum
 * @author Vinston Guillaume
 */

 public class SumInArray {

    public static void main(String[] args){
        int[] numbers = {2, 4, 3, 5, 7, 8, 9};
        int[] numbersWithDuplicates = {2, 4, 3, 5, 6, -2, 4, 7, 8, 9};

        findPair(numbers, 7);
        findPair(numbersWithDuplicates, 7);
    }

    /**
     * Prints all pairs of integer values from the given array that are equal to the sum
     * complexity is O(n^2)
     */
    public static void findPair(int[] array, int sum){
        
        for(int i = 0; i < array.length; i++){
            int first = array[i];

            for(int j = i + 1; j < array.length; j++){
                int second = array[j];

                if((first + second) == sum){
                    System.out.printf("(%d, %d) %n", first, second);
                }
            }
        }
    }
 }