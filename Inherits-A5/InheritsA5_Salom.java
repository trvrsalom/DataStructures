import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

/**
 * @author Trevor Salom
 *
 * Self Evaluation:
 * This project was very fun. The strengths of the code include the strong design
 * of the JComponent subclasses, and the weaknesses include the wild card imports.
 * If built properly, a battleship game would be straightforward to implement, with
 * the most difficult part being able to efficiently implement the game logic.
 */

public class InheritsA5_Salom extends JFrame {
	private static int clicks = 0;
	private static int[] pointX;
	private static int[] pointY;
	private static Color[] COLOR_OPTIONS = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
	protected static Color colorChoice;
	public static void main(String[] args) {

	  JFrame frame = new JFrame("Triangle Program");

		JOptionPane.showMessageDialog(frame, "This program achieves the following: " +
		"\nallows the user to specify a triangle with three mouse presses. After " +
		"\nthe first mouse press, draw a small dot. After the second mouse press, " +
		"\ndraw a line joining the first two points. After the third mouse press, " +
		"\ndraw the entire triangle. The fourth mouse press erases the old triangle " +
		"\nand starts a new one. " +
		"\n\nTo use the program click anywhere in the window multiple times.");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		frame.setVisible(true);
		frame.getContentPane().addMouseListener(new MouseAdapter() {
		  @Override
		  public void mouseClicked(MouseEvent e) {
				JComponent newComponent;
				switch (clicks) {
					case 1:
						pointX[1] = e.getX();
						pointY[1] = e.getY();
						newComponent = new Line(pointX, pointY, colorChoice);
						clicks++;
						break;
					case 2:
						pointX[2] = e.getX();
						pointY[2] = e.getY();
						newComponent = new Triangle(pointX, pointY, colorChoice);
						clicks++;
						break;
					default:
						colorChoice = COLOR_OPTIONS[(int)((Math.random())*(COLOR_OPTIONS.length))];
						clicks = 0;
						pointX = new int[3];
						pointY = new int[3];
						pointX[0] = e.getX();
						pointY[0] = e.getY();
						newComponent = new Point(pointX, pointY, colorChoice);
						clicks++;
						break;
				}
				frame.getContentPane().removeAll();
				frame.add(newComponent);
				frame.setVisible(true);
				frame.repaint();
		  }
		});
	}
}

class Point extends JComponent {
	int x, y;
	int DIAMETER = 10;
	Color color;

	public Point(int[] x, int[] y, Color color) {
		this.x = x[0] - (DIAMETER/2);
		this.y = y[0] - (DIAMETER/2);
		this.color = color;
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(color);
		g2.fill(new Ellipse2D.Double(x, y, DIAMETER, DIAMETER));
	}
}

class Line extends JComponent {
	int[] x;
	int[] y;
	Color color;

	public Line (int[] x, int[] y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(color);
		g2.draw(new Line2D.Double(x[0], y[0], x[1], y[1]));
	}
}

class Triangle extends JComponent {
	int[] x;
	int[] y;
	Color color;

	public Triangle(int[] x, int[] y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Polygon triangle = new Polygon(x, y, x.length);
		g2.setPaint(color);
		g2.fill(triangle);
	}
}
