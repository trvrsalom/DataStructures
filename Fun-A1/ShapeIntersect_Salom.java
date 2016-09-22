import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShapeIntersect_Salom {
	public static void main(String[] args) {
		new ShapeIntersect();
	}
}

class ShapeIntersect {
	private JFrame frame;

	private RectangleComponent rectA;
	private RectangleComponent rectB;

	private CircleComponent circ;

	public ShapeIntersect (){
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("Shape Intersect");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		rectA = new RectangleComponent(25, 45, 50, 90);
		rectB = new RectangleComponent(10, 10, 70, 45);

		int circX = Integer.parseInt(JOptionPane.showInputDialog("Enter an integer X coordinate for the circle: "));
		int circY = Integer.parseInt(JOptionPane.showInputDialog("Enter an integer Y coordinate for the circle: "));
		int circR = Integer.parseInt(JOptionPane.showInputDialog("Enter the integer radius for the circle: "));

		circ = new CircleComponent(circX, circY, circR);

		circ.setColor(intersects(rectA, circ) || intersects(rectB, circ) ? Color.RED : Color.GREEN);

		frame.add(circ);
		frame.setVisible(true);
		frame.add(rectA);
		frame.setVisible(true);
		frame.add(rectB);
		frame.setVisible(true);

	}

	private boolean intersects(RectangleComponent r, CircleComponent c) {
		int closestX = Math.max(r.x, Math.min(r.x2, c.x));
		int closestY = Math.max(r.y, Math.min(r.y2, c.y));
		int distanceX = c.x - closestX;
		int distanceY = c.y - closestY;
		double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
		return distance < c.radius;
	}
}

class RectangleComponent extends JComponent {
	public int x, y, x2, y2;

	public RectangleComponent(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Rectangle rect = new Rectangle(x, y, x2, y2);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(rect);
		g2.setColor(Color.BLACK);
		g2.draw(rect);
	}
}

class CircleComponent extends JComponent {
	public int x, y, radius;
	private Color c = Color.CYAN;

	public CircleComponent(int x, int y, int radius) {
		this.x = x - radius;
		this.y = y - radius;
		this.radius = radius;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Ellipse2D.Double circ = new Ellipse2D.Double(x, y, radius, radius);
		g2.setColor(c);
		g2.fill(circ);
	}
}
