/*
  Linked list is often used to implement other data structures. It is a squence of nodes where
  each node stores its own data and a pointer to the location of the next node.

  Advantages: Simillar to an array but no immutable. Can easily insert or remove elements without
  reallocation or reorganization of the entire structure because the data items don't need to be
  stored contigously.

  Disadvantages: Random access is not allowed. We must access nodes sequentially starting from
  the head one. Extra memory is required for each element of the list
*/

public class LinkedList{

//------------------------------------------------------------------
  static Node head = null;
//------------------------------------------------------------------
    static class Node{
      int data;
      Node next;

    Node(int data){
      this.data = data;
      next = null;
    }
  }

  public void addAtFront(int data){
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
  }
//------------------------------------------------------------------
  public void addAtEnd(int data){
    Node newNode = new Node(data);
    if(head == null){
      head = newNode;
    } else{
      Node temp = head;

      while(temp.next != null){
        temp = temp.next;
      }

      temp.next = newNode;
    }
  }
//------------------------------------------------------------------
  public void removeFront(){
    head = head.next;
  }
//------------------------------------------------------------------
  public void printList(){
    Node temp = head;
    while(temp != null){
      System.out.print(temp.data+"-> ");
      temp = temp.next;;;
    }

  }
//------------------------------------------------------------------
// Reverse LinkedList
  public LinkedList reverse(LinkedList list){
    Node prev = null;
    Node current = list.head;

    while(current != null){
      Node next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    list.head = prev;
    return list;
  }
//------------------------------------------------------------------
  public void delete(int key){
    Node temp = head, prev = null;
    if(temp != null && temp.data == key){
      head = temp.next;
      return;
    }

    while(temp != null && temp.data != key){
      prev = temp;
      temp = temp.next;
    }

    if(temp == null){
      return;
    }

    prev.next = temp.next;
  }



}
