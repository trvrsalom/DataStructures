/**
 * Comments: Overall the program works very well, and does a fine job of correctly
 * inserting integers between -50 & 50, and the printing them out using inorder traversal.
 */
public class TreeTester_Salom {
	/**
	 * Tester method. Randomly inserts into tree and prints.
	 * @param String[] args
	 * @return void
	 */
	public static void main(String[] args) {
		BSTree bs = new BSTree();
		for(int i = 0; i < 10; i++) {
			double randNumber = Math.random();
			int randomInt = (int)(randNumber * 100) + 1 - 50;
			bs.insert(randomInt);
		}
		bs.print();
	}
}

/**
 * Binary Search Tree class
 */
class BSTree {

	private TreeNode stump = null;

	/**
	 * Inserts a comparable object into the tree.
	 * @param Comparable comp
	 * @return void
	 */
	public void insert(Comparable comp) {
		stump = insertRecursively(stump, new TreeNode(comp));
	}

	/**
	 * Helper method to do the actual insertion recursively
	 * @param TreeNode root
	 * @param TreeNode leaf
	 * @return TreeNode
	 */
	private TreeNode insertRecursively(TreeNode root, TreeNode leaf) {
		if(root == null) return leaf;
		else if (((Comparable)leaf.getValue()).compareTo((Comparable)root.getValue()) > 0) root.setRight(insertRecursively(root.getRight(), leaf));
		else root.setLeft(insertRecursively(root.getLeft(), leaf));
		return root;
	}

	/**
	 * Prints the tree
	 * @return void
	 */
	public void print() {
		printTraverse(stump);
	}

	/**
	 * Helper method to do the actual printing recursively
	 * @param TreeNode node
	 * @return void
	 */
	private void printTraverse(TreeNode node) {
		if(node != null) {
			printTraverse(node.getLeft());
			System.out.print(node.getValue() + " ");
			printTraverse(node.getRight());
		}
	}
}

/**
 * Tree node class
 */
class TreeNode {
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
