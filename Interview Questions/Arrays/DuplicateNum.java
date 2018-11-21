import java.util.Arrays;


/**
 * Removes duplicates from this array
 * @author Vinston Guillaume
 */

 public class DuplicateNum {


    public static void main(String args[]){

        int[][] test = new int[][]{
            {1, 1,2 , 2, 3, 4, 5},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 3, 4, 5, 6, 7},
            {1, 2, 1, 1, 1, 1, 1},};

        for(int[] input:test){
            System.out.println("Array with Duplicates: " + Arrays.toString(input));
            System.out.println("After removing Duplicates: " + Arrays.toString(removeDuplicates(input)));
        }
    }

    /**
     * Method  to remove dupliactes from Aray without using Collection classes
     * eg. Set or Arraylist.
     * Sorts array and then compates adjacent object, removing duplicates
     */
    public static int[] removeDuplicates(int[] numbersWithDuplicates){

        // Sorting array ti bring duplicates together
        Arrays.sort(numbersWithDuplicates);

        int[] result = new int[numbersWithDuplicates.length];
        int previous = numbersWithDuplicates[0];
        result[0] = previous;

        for(int i = 1; i  < numbersWithDuplicates.length; i++){
            int ch = numbersWithDuplicates[i];

            if(previous != ch){
                result[i] = ch;
            }

            previous = ch;
        }

        return result;
    }
    
 }