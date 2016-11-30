import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
/**
 * LinkedLists_TrevorSalom (class)
 * Tester class
 */
public class LinkedLists_TrevorSalom {
 /**
  * main
  * tester method
  * @param String[] args
  * @return void
  */
 public static void main(String[] args) {
  SingleLinkedList < Integer > list = new SingleLinkedList < Integer > ();
  list.addFirst(3);
  list.print();
  list.addLast(9);
  list.print();
  list.removeLast();
  list.print();
  list.addLast(10);
  list.print();
  System.out.println(list.get(1));
  list.add(0, 99);
  list.add(3, 100);
  list.print();
  list.remove(2);
  list.print();
  System.out.println("Size: " + list.size());
 }
}
class SingleLinkedList < E > {
 private Node first;
 /**
  * Node (class)
  * linked list node object class
  */
 private class Node {
  public E data;
  public Node next;
  /**
   * setData
   * Sets the data of the node
   * @param E data
   * @return Node
   */
  public Node setData(E data) {
   this.data = data;
   return this;
  }
  /**
   * setNext
   * sets the next element of the node
   * @param Node next
   * @return Node
   */
  public Node setNext(Node next) {
   this.next = next;
   return this;
  }
 }
 public SingleLinkedList() {
  first = null;
 }
 /**
  * getFirst
  * returns the first element of the list
  * @return E
  */
 public E getFirst() {
  if (first == null)
   throw new IndexOutOfBoundsException();
  return first.data;
 }
 /**
  * removeFirst
  * removes the first element of the list
  * @return E
  */
 public E removeFirst() {
  if (first == null)
   throw new IndexOutOfBoundsException();
  E element = first.data;
  first = first.next;
  return element;
 }
 /**
  * addFirst
  * adds an element to the start of the list
  * @param E element
  * @return void
  */
 public void addFirst(E element) {
  Node newNode = new Node();
  newNode.data = element;
  newNode.next = first;
  first = newNode;
 }
 /**
  * addLast
  * adds an element to the end of the list
  * @param E element
  * @return void
  */
 public void addLast(E element) {
  Node node = first;
  while (node.next != null) {
   node = node.next;
  }
  node.next = new Node();
  node.next.data = element;
 }
 /**
  * removeLast
  * remove the last element from the list
  * @return E
  */
 public E removeLast() {
  Node node = first;
  while (node.next.next != null) {
   node = node.next;
  }
  Node toRet = node.next;
  node.next = null;
  return toRet.data;
 }
 /**
  * print
  * prints the list
  * @return void
  */
 public void print() {
  Node node = first;
  while (node.next != null) {
   System.out.print(node.data + ",");
   node = node.next;
  }
  System.out.println(node.data);
 }
 /**
  * get
  * gets the element at index
  * @param int index
  * @return E
  */
 public E get(int index) {
  int i = 0;
  Node node = first;
  while (i < index) {
   i++;
   if (node == null) throw new IndexOutOfBoundsException();
   else node = node.next;
  }
  return node.data;
 }
 /**
  * add
  * adds an element to index
  * @param int index
  * @param E element
  * @return void
  */
 public void add(int index, E element) {
  if (index == 0) first = new Node().setData(element).setNext(first);
  else {
   int i = 1;
   Node node = first;
   while (i < index) {
    i++;
    if (node == null) throw new IndexOutOfBoundsException();
    else node = node.next;
   }
   Node n = new Node();
   n.setData(element);
   n.setNext(node.next);
   node.setNext(n);
  }
 }
 /**
  * remove
  * removes an element at index
  * @param int index
  * @return E
  */
 public E remove(int index) {
  if (index == 0) return removeFirst();
  else {
   int i = 1;
   Node node = first;
   while (i < index) {
    i++;
    if (node == null) throw new NoSuchElementException();
    else node = node.next;
   }
   Node ret = node.next;
   node.next = node.next.next;
   return ret.data;
  }
 }
 /**
  * size
  * returns the size of the list
  * @return int
  */
 public int size() {
  int size = 0;
  Node node = first;
  while (node != null) {
   size++;
   node = node.next;
  }
  return size;
 }
}
