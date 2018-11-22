import java.util.Arrays;

/**
 * QuickSort algorithm
 * @author Vinston Guillaume
 */

 public class QuickSortTest {

    public static void main(String[] args){

        // unsorted integer array
        int[] unsorted = {6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("Unsorted array: " + Arrays.toString(unsorted));

        QuickSort algorithm = new QuickSort();

        // sorting integer array using quicksort algorithm
        algorithm.sort(unsorted);

        // printing sorted array
        System.out.println("Sorted array: " + Arrays.toString(unsorted));

    }

 }

 /**
  * Sorts numbers using QuickSort Algorithm. It is a divide
  * and conquer algortithm that divides the orginal list, sorts it,
  * and then merges it to create sorted output
  * @author Vinston Guillaume
  */
  class QuickSort{
      private int input[];
      private int length;

      public void sort(int[] numbers){
          if(numbers == null || numbers.length == 0){
              return;
          }
          
          this.input = numbers;
          length = numbers.length;
          quickSort(0, length - 1);
      }

      /**
       * Implements in-place quicksort alogrithm recursively
       */
      private void quickSort(int low, int high){
          int i = low;
          int j = high;

          // pivot is middle index
          int pivot = input[low + (high - low) / 2];

          // Divide into two arrays
          while(i <= j){
              /**
               * Searches for a number from the left that 
               * is greater than the pivot
               * and searches from the right a number less
               * than the pivot
               * Once complete the numbers are swapped
               */
              while(input[i] < pivot){
                  i++;
              }
              while(input[j] > pivot){
                  j--;
              }
              if(i <= j){
                  swap(i, j);
                  i++;
                  j--;
              }
            }
        }

        private void swap(int i, int j){
            int temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }
  }