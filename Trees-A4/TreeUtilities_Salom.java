import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class TreeUtilities_Salom {
	public static void main(String[] args) {
		TreeUtilities util = new TreeUtilities();
		TreeNode parent = new TreeNode(0);

		TreeDisplay disp = new TreeDisplay();
		disp.displayTree(parent);
		createTree(1, parent);
		TreeNode copy = util.copy(parent);
		//parent = util.createDecodingTree(disp);
		System.out.println(util.sameShape(parent, copy));
		util.preOrder(parent, disp);
		util.inOrder(parent, disp);
		util.postOrder(parent, disp);
	}

	public static void createTree(int i, TreeNode parent) {
		if(i < 5) {
			parent.setLeft(new TreeNode(i));
			parent.setRight(new TreeNode(i));
			createTree(i+1, parent.getRight());
			createTree(i+1, parent.getLeft());
		}
	}
}

//A container for useful static methods that operate on TreeNode objects.
class TreeUtilities
{
	//the random object used by this class
	private static java.util.Random random = new java.util.Random();

	//used to prompt for command line input
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	//precondition:  t is non-empty
	//postcondition: returns the value in the leftmost node of t.
	public static Object leftmost(TreeNode t)
	{
		while(t.getLeft() != null) t = t.getLeft();
		return t.getValue();
	}

	//precondition:  t is non-empty
	//postcondition: returns the value in the rightmost node of t.
	public static Object rightmost(TreeNode t)
	{
		if(t.getLeft() != null) return rightmost(t.getLeft());
		return t.getValue();
	}

	//postcondition: returns the maximum depth of t, where an empty tree
	//               has depth 0, a tree with one node has depth 1, etc
	public static int maxDepth(TreeNode t)
	{
		if(t.getLeft() == null && t.getRight() == null) return 1;
		else if(t.getLeft() != null && t.getRight() == null) return 1 + maxDepth(t.getLeft());
		else if(t.getLeft() == null && t.getRight() != null) return 1 + maxDepth(t.getRight());
		return 1 + Math.max(maxDepth(t.getLeft()), maxDepth(t.getRight()));
	}

	//postcondition: each node in t has been lit up on display
	//               in a pre-order traversal
	public static void preOrder(TreeNode t, TreeDisplay display)
	{
		display.visit(t);
		if(t.getLeft() != null) preOrder(t.getLeft(), display);
		if(t.getRight() != null) preOrder(t.getRight(), display);
	}

	//postcondition: each node in t has been lit up on display
	//               in an in-order traversal
	public static void inOrder(TreeNode t, TreeDisplay display)
	{
		if(t.getLeft() != null) inOrder(t.getLeft(), display);
		display.visit(t);
		if(t.getRight() != null) inOrder(t.getRight(), display);
	}

	//postcondition: each node in t has been lit up on display
	//               in a post-order traversal
	public static void postOrder(TreeNode t, TreeDisplay display)
	{
		if(t.getLeft() != null) postOrder(t.getLeft(), display);
		if(t.getRight() != null) postOrder(t.getRight(), display);
		display.visit(t);
	}

	//useful method for building a randomly shaped
	//tree of a given maximum depth
	public static TreeNode createRandom(int depth)
	{
		if (random.nextInt((int)Math.pow(2, depth)) == 0)
			return null;
		return new TreeNode(new Integer(random.nextInt(10)),
		                    createRandom(depth - 1),
		                    createRandom(depth - 1));
	}

	//returns the number of nodes in t
	public static int countNodes(TreeNode t)
	{
		int sum = 1;
		sum += t.getLeft() != null ? countNodes(t.getLeft()) : 0;
		sum += t.getRight() != null ? countNodes(t.getRight()) : 0;
		return sum;
	}

	//returns the number of leaves in t
	public static int countLeaves(TreeNode t)
	{
		return (t.getLeft() == null && t.getRight() == null ? 1 : (t.getRight() != null ? countLeaves(t.getRight()) : 0) + (t.getLeft() != null ? countLeaves(t.getLeft()) : 0));
	}

	//precondition:  all values in t are Integer objects
	//postcondition: returns the sum of all values in t
	public static int sum(TreeNode t)
	{
		return (int)t.getValue() + (t.getRight() != null ? sum(t.getRight()) : 0) + (t.getLeft() != null ? sum(t.getLeft()) : 0);
	}

	//postcondition:  returns a new tree, which is a complete copy
	//                of t with all new TreeNode objects pointing
	//                to the same values as t (in the same order, shape, etc)
	public static TreeNode copy(TreeNode t)
	{
		TreeNode np = new TreeNode(t.getValue());
		if(t.getLeft() != null) np.setLeft(copy(t.getLeft()));
		if(t.getRight() != null) np.setRight(copy(t.getRight()));
		return np;
	}

	//postcondition:  returns true if t1 and t2 have the same
	//                shape (but not necessarily the same values);
	//                otherwise, returns false
	public static boolean sameShape(TreeNode t1, TreeNode t2)
	{
		boolean ret = true;
		if(t1.getRight() != null && t2.getRight() != null) ret = ret && sameShape(t1.getRight(), t2.getRight());
		if(t1.getLeft() != null && t2.getLeft() != null) ret = ret && sameShape(t1.getLeft(), t2.getLeft());
		if((t1.getRight() == null && t2.getRight() != null) || (t1.getRight() != null && t2.getRight() == null)) ret = false;
		if((t1.getLeft() == null && t2.getLeft() != null) || (t1.getLeft() != null && t2.getLeft() == null)) ret = false;
		return ret;
	}

	//postcondition:  plays a game of twenty questions
	public static void twentyQuestions()
	{
		try
		{
			TreeDisplay display = new TreeDisplay();
			TreeNode knowledge = new TreeNode("Mr. Feinberg");
			display.displayTree(knowledge);
			while (true)
			{
				System.out.println("\nThink of a person or thing, and press enter");
				br.readLine();
				twentyQuestionsRound(knowledge, display);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	//postcondition:  plays a round of twenty questions, asking the user questions as it
	//                walks down the given knowledge tree, lighting up the display as it goes;
	//                modifies the tree to include information learned.
	private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
	throws IOException
	{
		throw new Error("Implement me!");
	}

	//postcondition:  returns a tree for decoding Morse code
	public static TreeNode createDecodingTree(TreeDisplay display)
	{
		TreeNode tree = new TreeNode("Morse Tree");
		display.displayTree(tree);
		insertMorse(tree, "a", ".-", display);
		insertMorse(tree, "b", "-...", display);
		insertMorse(tree, "c", "-.-.", display);
		insertMorse(tree, "d", "-..", display);
		insertMorse(tree, "e", ".", display);
		insertMorse(tree, "f", "..-.", display);
		insertMorse(tree, "g", "--.", display);
		insertMorse(tree, "h", "....", display);
		insertMorse(tree, "i", "..", display);
		insertMorse(tree, "j", ".---", display);
		insertMorse(tree, "k", "-.-", display);
		insertMorse(tree, "l", ".-..", display);
		insertMorse(tree, "m", "--", display);
		insertMorse(tree, "n", "-.", display);
		insertMorse(tree, "o", "---", display);
		insertMorse(tree, "p", ".--.", display);
		insertMorse(tree, "q", "--.-", display);
		insertMorse(tree, "r", ".-.", display);
		insertMorse(tree, "s", "...", display);
		insertMorse(tree, "t", "-", display);
		insertMorse(tree, "u", "..-", display);
		insertMorse(tree, "v", "...-", display);
		insertMorse(tree, "w", ".--", display);
		insertMorse(tree, "x", "-..-", display);
		insertMorse(tree, "y", "-.--", display);
		insertMorse(tree, "z", "--..", display);
		return tree;
	}

	//postcondition:  inserts the given letter into the decodingTree,
	//                in the appropriate position, as determined by
	//                the given Morse code sequence; lights up the display
	//                as it walks down the tree
	private static void insertMorse(TreeNode decodingTree, String letter,
	                                String code, TreeDisplay display)
	{
		for(char i : code.toCharArray()) {
			if(i == '.') {
				if(decodingTree.getRight() == null) decodingTree.setRight(new TreeNode(""));
				decodingTree = decodingTree.getRight();
			}
			else if(i == '-') {
				if(decodingTree.getLeft() == null) decodingTree.setLeft(new TreeNode(""));
				decodingTree = decodingTree.getLeft();
			}
		}
		decodingTree.setValue(letter);
		display.visit(decodingTree);
	}

	//precondition:  ciphertext is Morse code, consisting of dots, dashes, and spaces
	//postcondition: uses the given decodingTree to return the decoded message;
	//               lights up the display as it walks down the tree
	public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
	{
		String str = "";
		for(String word : cipherText.split(" ")) {
			for(char i : word.toCharArray()) {
				System.out.println(i + "");
				System.out.println(i == '.');
				if(i == '.') {
					decodingTree = decodingTree.getRight();
				}
				else if(i == '-') {
					decodingTree = decodingTree.getLeft();
				}
			}
			System.out.println(decodingTree.getValue());
			display.visit(decodingTree);
			str += decodingTree.getValue();
		}
		System.out.println("");
		return str;
	}

	//precondition:  expTree is an expression tree consisting of Integer objects
	//               joined by "+" and "*" operators
	//postcondition: returns the value of the expression tree
	public static int eval(TreeNode expTree)
	{
		throw new Error("Implement me!");
	}

	//precondition:  exp represents an arithmetic expression,
	//               consisting of "+", "*", paretheses and numbers
	//postcondition: returns an expression tree to represent this arithmetic expression
	public static TreeNode createExpressionTree(String exp)
	{
		throw new Error("Implement me!");
	}
}

//a graphical component for displaying the contents of a binary tree.
//
//sample usage:
//
// TreeDisplay display = new TreeDisplay();
// display.displayTree(someTree);
// display.visit(someNode);
class TreeDisplay extends JComponent
{
	//number of pixels between text and edge
	private static final int ARC_PAD = 2;

	//the tree being displayed
	private TreeNode root = null;

	//the node last visited
	private TreeNode visiting = null;

	//the set of all nodes visited so far
	private Set visited = new HashSet();

	//number of milliseconds to pause when visiting a node
	private int delay = 500;

	//creates a frame with a new TreeDisplay component.
	//(constructor returns the TreeDisplay component--not the frame).
	public TreeDisplay()
	{
		//create surrounding frame
		JFrame frame = new JFrame("Tree Display");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//add the TreeDisplay component to the frame
		frame.getContentPane().add(this);

		//show frame
		frame.pack();
		frame.setVisible(true);

		java.util.Timer timer = new java.util.Timer();
		TimerTask task = new TimerTask()
		{
			public void run()
			{
				TreeDisplay.this.repaint();
			}
		};
		timer.schedule(task, 0, 1000);
	}

	//tells the frame the default size of the tree
	public Dimension getPreferredSize()
	{
		return new Dimension(400, 300);
	}

	//called whenever the TreeDisplay must be drawn on the screen
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		Dimension d = getSize();

		//draw white background
		g2.setPaint(Color.white);
		g2.fill(new Rectangle2D.Double(0, 0, d.width, d.height));

		int depth = TreeUtilities.maxDepth(root);

		if (depth == 0)
			//no tree to draw
			return;

		//hack to avoid division by zero, if only one level in tree
		if (depth == 1)
			depth = 2;

		//compute the size of the text
		FontMetrics font = g2.getFontMetrics();
		int leftPad = font.stringWidth(
			TreeUtilities.leftmost(root).toString()) / 2;
		int rightPad = font.stringWidth(
			TreeUtilities.rightmost(root).toString()) / 2;
		int textHeight = font.getHeight();

		//draw the actual tree
		drawTree(g2, root, leftPad + ARC_PAD,
		         d.width - rightPad - ARC_PAD,
		         textHeight / 2 + ARC_PAD,
		         (d.height - textHeight - 2 * ARC_PAD) / (depth - 1));
	}

	//draws the tree, starting from the given node, in the region with x values ranging
	//from minX to maxX, with y value beginning at y, and next level at y + yIncr.
	private void drawTree(Graphics2D g2, TreeNode t, int minX, int maxX, int y, int yIncr)
	{
		//skip if empty
		if (t == null)
			return;

		//compute useful coordinates
		int x = (minX + maxX) / 2;
		int nextY = y + yIncr;

		//draw black lines
		g2.setPaint(Color.black);
		if (t.getLeft() != null)
		{
			int nextX = (minX + x) / 2;
			g2.draw(new Line2D.Double(x, y, nextX, nextY));
		}
		if (t.getRight() != null)
		{
			int nextX = (x + maxX) / 2;
			g2.draw(new Line2D.Double(x, y, nextX, nextY));
		}

		//measure text
		FontMetrics font = g2.getFontMetrics();
		String text = t.getValue().toString();
		int textHeight = font.getHeight();
		int textWidth = font.stringWidth(text);

		//draw the box around the node
		Rectangle2D.Double box = new Rectangle2D.Double(
			x - textWidth / 2 - ARC_PAD, y - textHeight / 2 - ARC_PAD,
			textWidth + 2 * ARC_PAD, textHeight + 2 * ARC_PAD);//, ARC_PAD, ARC_PAD);
		Color c;
		//color depends on whether we haven't visited, are visiting, or have visited.
		if (t == visiting)
			c = Color.YELLOW;
		else if (visited.contains(t))
			c = Color.ORANGE;
		else
			c = new Color(187, 224, 227);
		g2.setPaint(c);
		g2.fill(box);
		//draw black border
		g2.setPaint(Color.black);
		g2.draw(box);

		//draw text
		g2.drawString(text, x - textWidth / 2, y + textHeight / 2);

		//draw children
		drawTree(g2, t.getLeft(), minX, x, nextY, yIncr);
		drawTree(g2, t.getRight(), x, maxX, nextY, yIncr);
	}

	//tells the component to switch to displaying the given tree
	public void displayTree(TreeNode root)
	{
		this.root = root;

		//signal that the display needs to be redrawn
		repaint();
	}

	//light up this particular node, indicating we're visiting it.
	public void visit(TreeNode t)
	{
		//if we've already visited it, we assume this is a new traversal,
		//and reset the set of visited nodes.
		if (visited.contains(t))
			visited = new HashSet();

		//update visiting and visited
		visiting = t;
		visited.add(t);

		//signal that the display needs to be redrawn
		repaint();

		//pause, so you can see the traversal
		try
		{
			Thread.sleep(delay);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	//change the length of time to pause when visiting a node
	public void setDelay(int delay)
	{
		this.delay = delay;
	}
}

class TreeNode
{
	private Object value;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(Object initValue)
	{
		value = initValue; left = null; right = null;
	}

	public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
	{
		value = initValue; left = initLeft; right = initRight;
	}

	public Object getValue() {
		return value;
	}
	public TreeNode getLeft() {
		return left;
	}
	public TreeNode getRight() {
		return right;
	}

	public void setValue(Object theNewValue) {
		value = theNewValue;
	}
	public void setLeft(TreeNode theNewLeft) {
		left = theNewLeft;
	}
	public void setRight(TreeNode theNewRight) {
		right = theNewRight;
	}
}
