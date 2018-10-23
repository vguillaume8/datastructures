public class LinkedListTester{

  public static void main(String[] args){
    LinkedList list = new LinkedList();

    list.addAtFront(1);
    list.addAtFront(2);
    list.addAtFront(4);
    list.addAtFront(8);
    list.addAtEnd(5);
    list.printList();
    list.reverse(list);
    System.out.println();
    list.delete(8);
    list.printList();
  }
}
