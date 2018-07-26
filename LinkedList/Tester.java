public class Tester{

  public static void main(String[] args){
    LinkedList list = new LinkedList();

    list.addAtFront(1);
    list.addAtFront(2);
    list.addAtFront(4);
    list.addAtFront(8);
    list.addAtEnd(5);
    list.printList();
    //list.reverse(list.head);
    System.out.println();
    list.delete(8);
    list.printList();
  }
}
