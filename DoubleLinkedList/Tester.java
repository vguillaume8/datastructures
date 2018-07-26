public class Tester{

  public static void main(String[] args){
    DoubleLinkedList list = new DoubleLinkedList();

  // Insert 6. So linked list becomes 6->NULL
      list.append(6);

      // Insert 7 at the beginning. So linked list becomes 7->6->NULL
      list.push(7);

      // Insert 1 at the beginning. So linked list becomes 1->7->6->NULL
      list.push(1);

      // Insert 4 at the end. So linked list becomes 1->7->6->4->NULL
      list.append(4);

      // Insert 8, after 7. So linked list becomes 1->7->8->6->4->NULL
      list.insertAfter(list.head.next, 8);

      System.out.println("Created list is: ");
      list.printList(list.head);
  }
}
