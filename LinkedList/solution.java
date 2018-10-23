public class Solution{

  public boolean parseSolution(String s){
    //Stack<Character> stack = new Stack<Character>();
    char temp = "";
    int ind1;
    int ind2;
    int length = s.length();
    for(int i = 0; i < length; i++){
      temp = s.charAt(i);

      for(int j = i+1; j < i+2; j++){
        if(temp == s.charAt(i)){
          ind1  = i - 1;
          ind2 = i;
          s = parseString(s, ind1, ind2);
          i = 0;
        }

      }
    }
    if(s.length == 0){
      return true;
    }

    return false;
  }

  public String parseString(String s, int ind1, int ind2){
    String newString = "";
    for(int i = 0; i < s.length(); i++){
      if(i != ind1 || i != ind2){
        newString = newString + s.charAt(i);
      }

    }
    return newString;
  }

  public static void main(String[] args){
    String s = "abccba";
    System.out.println(parseSolution(s));

  }
}
