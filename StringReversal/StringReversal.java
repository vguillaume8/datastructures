import java.util.Stack;
public class StringReversal{

//  private String stringToBeReversed;

/*  StringReversal(String s){
    stringToBeReversed = s;
  }*/

  public String reverseString(String stringToBeReversed){
    Stack<Character> stack = new Stack<Character>();

    for(int i = 0; i < stringToBeReversed.length(); i++){
      stack.push(stringToBeReversed.charAt(i));
    }
    String result = "";
    while(!stack.isEmpty()){
      result = result + stack.pop();
    }
    return result;
  }
}
