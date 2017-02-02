/**
 * Comments: Overall the program works very well, and does a fine job of correctly
 * inserting integers between -50 & 50, and the printing them out using inorder traversal.
 *
 * Comments for removal: Works well removing nodes from the tree. Handles all
 * cases with a stupid amount of if statements. Uses a helper method added to the TreeNode class
 */
public class TreeTester_Salom {
	/**
	 * Tester method. Randomly inserts into tree and prints.
	 * @param String[] args
	 * @return void
	 */
	public static void main(String[] args) {
		BSTree bs = new BSTree<Integer>();
		for(int i = 0; i < 10; i++) {
			bs.insert(i);
			bs.print();
		}
		for(int i = 0; i < 10; i++) {
			bs.remove(i);
			bs.print();
		}
		bs.remove(0);
	}
}

/**
 * Binary Search Tree class
 */
class BSTree<T> {

	private TreeNode<T> stump = null;

	/**
	 * Inserts a comparable object into the tree.
	 * @param Comparable comp
	 * @return void
	 */
	public void insert(Comparable comp) {
		stump = insertRecursively(stump, new TreeNode<T>((T)comp));
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
		System.out.println();
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

	/**
	 * Method to remove nodes.
	 * @param value to be removed
	 * @return The removed value.
	 */
	public T remove(T node) {
		if(stump == null) return null;
		else {
			if(stump.getValue() == node) {
				TreeNode<T> fakeStump = new TreeNode<T>(node);
				fakeStump.setLeft(stump);
				Boolean ret = stump.remove(node, fakeStump);
				stump = fakeStump.getLeft();
				return ret ? node : null;
			}
			else {
				return stump.remove(node, null) ? node : null;
			}
		}
	}
}

/**
 * Tree node class
 */
class TreeNode<T>  {
	private T value;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(T initValue)
	{
		value = initValue; left = null; right = null;
	}

	public TreeNode(T initValue, TreeNode initLeft, TreeNode initRight)
	{
		value = initValue; left = initLeft; right = initRight;
	}

	public T getValue() {
		return value;
	}
	public TreeNode getLeft() {
		return left;
	}
	public TreeNode getRight() {
		return right;
	}

	public void setValue(T theNewValue) {
		value = theNewValue;
	}
	public void setLeft(TreeNode theNewLeft) {
		left = theNewLeft;
	}
	public void setRight(TreeNode theNewRight) {
		right = theNewRight;
	}

	public Boolean remove(T value, TreeNode parent) {
		if(((Comparable)value).compareTo(this.value) < 0) {
			if(left != null) return left.remove(value, this);
			else return null;
		}
		else if(((Comparable)value).compareTo(this.value) > 0) {
			if(right != null) return right.remove(value, this);
			else return null;
		}
		else {
			if (left != null && right != null) {
				this.value = (T)right.minValue();
				right.remove(this.value, this);
			}
			else if (parent.left == this) parent.left = (left != null) ? left : right;
			else if (parent.right == this) parent.right = (left != null) ? left : right;
			return true;
		}
	}

  public T minValue() {
    if (left == null) return value;
    else return (T)left.minValue();
  }
}
