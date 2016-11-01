import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;

/**
 * GraphicSort_SalomMcConnell (class)
 */
public class GraphicSort_SalomMcConnell {
 private static JFrame frame;
 private static ArrayList < Rectangle > rectangles = new ArrayList < Rectangle > ();

 private static final int ANIMATION_TIME_DELAY = 300;
 /**
  * main
  * @param String[] args
  * @return void
  */
 public static void main(String[] args) {
  frame = new JFrame("Sort Program");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(640, 700);
  frame.setVisible(true);
  generateList();
  quickSort(0, rectangles.size() - 1);
  try {
   Thread.sleep(2000);
  } catch (InterruptedException ex) {
   Thread.currentThread().interrupt();
  }
  System.exit(0);
 }

 /**
  * randomRange
  * @param int minimum
  * @param int maximum
  * @return int
  */
 public static int randomRange(int minimum, int maximum) {
  return minimum + (int)(Math.random() * (maximum - minimum + 1));
 }

 /**
  * generateList
  * @return void
  */
 public static void generateList() {
  for (int i = 0; i < 30; i++)
   rectangles.add(addComponent(new Rectangle(i, randomRange(100, 600), Color.BLACK)));
 }

 /**
  * addComponent
  * @param Rectangle component
  * @return Rectangle
  */
 public static Rectangle addComponent(Rectangle component) {
  frame.add(component);
  frame.setVisible(true);
  frame.repaint();
  return component;
 }

 /**
  * quickSort
  * @param int left
  * @param int right
  * @return void
  */
 public static void quickSort(int left, int right) {
  if (right - left <= 0) return;
  else {
   int pivotInd = right;
   for (int i = left; i < right; i++) {
    rectangles.get(i).setColor(Color.GREEN);
    frame.repaint();
   }
   int partition = partition(left, right, pivotInd);
   for (int i = left; i <= right; i++) {
    rectangles.get(i).setColor(Color.BLACK);
    frame.repaint();
   }
   quickSort(left, partition - 1);
   quickSort(partition + 1, right);
  }
 }

 /**
  * partition
  * @param int left
  * @param int right
  * @param int pivot
  * @return int
  */
 public static int partition(int left, int right, int pivot) {
  int leftPointer = left - 1;
  int rightPointer = right;
  Rectangle pivotRect = rectangles.get(pivot);
  pivotRect.setColor(Color.RED);
  frame.repaint();
  while (true) {
   while (rectangles.get(++leftPointer).getOurHeight() < pivotRect.getOurHeight()) {}
   while (rightPointer > 0 && rectangles.get(--rightPointer).getOurHeight() > pivotRect.getOurHeight()) {}
   if (leftPointer >= rightPointer) break;
   else swap(leftPointer, rightPointer);
  }
  swap(leftPointer, right);
  pivotRect.setColor(Color.GREEN);
  frame.repaint();
  return leftPointer;
 }

 /**
  * swap
  * @param int a
  * @param int b
  * @return void
  */
 public static void swap(int a, int b) {
  if (a == b) {
   return;
  }
  Rectangle rectA = rectangles.get(a);
  Rectangle rectB = rectangles.get(b);
  try {
   Thread.sleep(ANIMATION_TIME_DELAY);
  } catch (InterruptedException ex) {
   Thread.currentThread().interrupt();
  }
  rectA.setColor(Color.BLUE);
  rectB.setColor(Color.BLUE);
  frame.repaint();
  try {
   Thread.sleep(ANIMATION_TIME_DELAY);
  } catch (InterruptedException ex) {
   Thread.currentThread().interrupt();
  }
  int bHeight = new Integer(rectA.getOurHeight());
  int aHeight = new Integer(rectB.getOurHeight());
  rectA.setHeight(aHeight);
  rectB.setHeight(bHeight);
  frame.repaint();
  try {
   Thread.sleep(ANIMATION_TIME_DELAY);
  } catch (InterruptedException ex) {
   Thread.currentThread().interrupt();
  }
  rectA.setColor(Color.GREEN);
  rectB.setColor(Color.GREEN);
  frame.repaint();
 }
}

/**
 * Rectangle (class)
 */
class Rectangle extends JComponent {
 int height;
 int width;
 int pos;
 Color color;

 public static final int RECTANGLE_WIDTH = 20;

 public Rectangle(int pos, int height, Color color) {
  this.pos = pos;
  this.height = height;
  this.width = 1;
  this.color = color;
 }

 /**
  * setColor
  * @param Color color
  * @return void
  */
 public void setColor(Color color) {
  this.color = color;
 }

 /**
  * setHeight
  * @param int height
  * @return void
  */
 public void setHeight(int height) {
  this.height = height;
 }

 /**
  * getOurHeight
  * @return int
  */
 public int getOurHeight() {
  return height;
 }

 /**
  * paintComponent
  * @param Graphics g
  * @return void
  */
 protected void paintComponent(Graphics g) {
  Graphics2D g2 = (Graphics2D) g;
  g2.setPaint(color);
  g2.fill(new Rectangle2D.Double(pos * RECTANGLE_WIDTH + 20, 700 - height, width * RECTANGLE_WIDTH, height));
  g2.setPaint(Color.WHITE);
  g2.draw(new Rectangle2D.Double(pos * RECTANGLE_WIDTH + 20, 700 - height, width * RECTANGLE_WIDTH, height));
 }
}
