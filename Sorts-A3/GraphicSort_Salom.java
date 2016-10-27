import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;

public class GraphicSort_Salom {
	private static JFrame frame;
	private static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

	public static void main(String[] args) {
	  frame = new JFrame("Sort Program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 700);
		frame.setVisible(true);
		generateList();
		quickSort(0, rectangles.size() - 1);
	}

	public static int randomRange(int minimum, int maximum) { return minimum + (int)(Math.random() * (maximum - minimum + 1)); }

	public static void generateList() {
		for(int i = 0; i < 10; i++)
			rectangles.add(addComponent(new Rectangle(i, randomRange(100, 600), Color.BLACK)));
	}

	public static Rectangle addComponent(Rectangle component) {
		frame.add(component);
		frame.setVisible(true);
		frame.repaint();
		return component;
	}

	public static void quickSort(int left, int right) {
		if (left < right){
            int pivot = rectangles.get(right).getOurHeight();
            int pos = left - 1;
            for (int i = left; i < right; i++)
                if (rectangles.get(i).getOurHeight() <= pivot)
                    swap(++pos, i);
            swap(pos + 1, right);
            quickSort(left, pos);
            quickSort(pos + 1, right);
        }
	}

	public static void swap(int a, int b) {
		Rectangle rectA = rectangles.get(a);
		Rectangle rectB = rectangles.get(b);
		int animTime = 100;
		try {
		  Thread.sleep(animTime);
		} catch(InterruptedException ex) {
		  Thread.currentThread().interrupt();
		}
		rectA.setColor(Color.BLUE);
		rectB.setColor(Color.BLUE);
		frame.repaint();
		try {
		  Thread.sleep(animTime);
		} catch(InterruptedException ex) {
		  Thread.currentThread().interrupt();
		}
		int bHeight = new Integer(rectA.getOurHeight());
		int aHeight = new Integer(rectB.getOurHeight());
		rectA.setHeight(aHeight);
		rectB.setHeight(bHeight);
		frame.repaint();
		try {
		  Thread.sleep(animTime);
		} catch(InterruptedException ex) {
		  Thread.currentThread().interrupt();
		}
		rectA.setColor(Color.BLACK);
		rectB.setColor(Color.BLACK);
		frame.repaint();
	}
}

class Rectangle extends JComponent {
	int height;
	int width;
	int pos;
	Color color;

	public Rectangle (int pos, int height, Color color) {
		this.pos = pos;
		this.height = height;
		this.width = 1;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getOurHeight() { return height; }

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(color);
		g2.fill(new Rectangle2D.Double(pos*60 + 20, 700-height, width*60, height));
		g2.setPaint(Color.WHITE);
		g2.draw(new Rectangle2D.Double(pos*60 + 20, 700-height, width*60, height));
	}
}
