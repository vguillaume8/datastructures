import java.util.*;

/**
 * Finds the duplicate characters in a String
 * @author Vinston Guillaume
 */
public class FindDupCharacters{

    public static void main(String[] args){
        printDupCharacter("Programming");
        printDupCharacter("Combination");
        printDupCharacter("Java");
    }

    /**
     * Find all duplicate characters in a String and print
     * each of them.
     */
    public static void printDupCharacter(String word){
        char[] characters = word.toCharArray();

        // build HashMap with character and number of appearances
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(Character ch : characters){
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            } else{
                map.put(ch, 1);
            }
        }

        // Iterate through HashMap to print all duplicate characters
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        System.out.printf("List of duplicate characters in String '%s' %n", word);
        for(Map.Entry<Character, Integer> entry: entrySet){
            if(entry.getValue() > 1){
                System.out.printf("%s: %d %n", entry.getKey(), entry.getValue());
            }
        }
    }


}