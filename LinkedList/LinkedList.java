/*
  Linked list is often used to implement other data structures. It is a squence of nodes where
  each node stores its own data and a pointer to the location of the next node.

  Advantages: Simillar to an array but no immutable. Can easily insert or remove elements without
  reallocation or reorganization of the entire structure because the data items don't need to be
  stored contigously.

  Disadvantages: Random access is not allowed. We must access nodes sequentially starting from
  the first one. Extra memory is required for each element of the list
*/

public class LinkedList{

    class Node{
    int data;
    Node next;

    Node(int data){
      this.data = data;
    }
  }
//------------------------------------------------------------------
  Node first = null;
//------------------------------------------------------------------
  public void addAtFront(int data){
    Node newNode = new Node(data);
    newNode.next = first;
    first = newNode;
  }
//------------------------------------------------------------------
  public void addAtEnd(int data){
    Node newNode = new Node(data);
    if(first == null){
      first = newNode;
    } else{
      Node temp = first;

      while(temp.next != null){
        temp = temp.next;
      }

      temp.next = newNode;
    }
  }
//------------------------------------------------------------------
  public void removeFront(){
    first = first.next;
  }
//------------------------------------------------------------------
  public void printList(){
    Node temp = first;
    while(temp != null){
      System.out.print(temp.data+"-> ");
      temp = temp.next;;;
    }

  }
//------------------------------------------------------------------




}
