public class DoubleLinkedList{
  Node head;

  class Node{
    int data;
    Node prev;
    Node next;

    Node(int d){
      data = d;
    }
  }
//------------------------------------------------------------------
  public void push(int newData){
    Node newNode = new Node(newData);
    newNode.next = head;
    newNode.prev = null;

    if(head != null){
      head.prev = newNode;
      head = newNode;
    }
  }
//------------------------------------------------------------------

  public void insertAfter(Node prevNode, int newData){
    if(prevNode == null){
      System.out.println("Previous node cannot be null");
      return;
    }

    Node newNode = new Node(newData);
    newNode.next = prevNode.next;
    prevNode.next = newNode;
    newNode.prev = prevNode;
    if(newNode.next != null){
      newNode.next.prev = newNode;
    }
  }
//------------------------------------------------------------------
    public void append(int newData){
      Node newNode = new Node(newData);
      Node last = head;
      newNode.next = null;
      if(head == null){
        newNode.prev = null;
        head = newNode;
        return;
      }

      while(last.next != null){
        last = last.next;
      }
      last.next = newNode;
      newNode.prev = last;
    }

//------------------------------------------------------------------
public void printList(Node node){
      Node last = null;
      System.out.println("Traversal in forward Direction");
      while (node != null) {
          System.out.print(node.data + " ");
          last = node;
          node = node.next;
      }
      System.out.println();
      System.out.println("Traversal in reverse direction");
      while (last != null) {
          System.out.print(last.data + " ");
          last = last.prev;
      }
  }
}
