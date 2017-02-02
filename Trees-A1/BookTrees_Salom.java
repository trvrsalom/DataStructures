/**
 * R17.1) A straight line. Lamda-esque
 * R17.2) int maxSiblings(Node parent) {
 * 					int max = parent.getChildren().length;
 * 					for(Node i : parent.getChildren()) {
 *      			max = maxSiblings(i) > max ? maxSiblings(i) : max;
 * 					}
 * 					return max;
 * 				}
 * R17.5) A binary is a tree where each node only has two children. In a binary search tree the left node is less than the parent and the right node is greater than the parent.
 * 					Binary 3				Binary Search Tree   5
 * 								2 1														2 7
 * R17.6)	A balanced tree has an equal height on each side of the parent
 * 					balanced	3				unbalanced	5
 * 									2   1								3   4
 * 																		1	 2
 * R17.7)						    			Adam
 * 											     	 				Eve
 * 									    			   Diana    Romeo
 * 											   						 Juliet
 * 											   			    Harry	 Tom
 * R17.8)									Harry
 * 								Diana             Tom
 * 						Adam	  	Eve		Juliet
 * 															  Romeo
 * Both Print: Adam Diana Eve Harry Juliet Romeo Tom
 *
 * R17.9) 2 7 4 1 8 5 3 9 6 10
 * R17.13)															Mary
 * 													Had											was
 * 						       a						Little			snow		white.
 * 						      	fleece	lamb
 * 						       as			its
 * R17.14)
 * 			Preorder)	Mary had a fleece as little lamb it was snow white
 * 			In order) A as fleece had its lab. little mary snow was white
 * 			Postorder) as fleece a its lamb little had snow white was Mary
 *
 */

public class BookTrees_Salom {
	public static void main(String[] args) {

	}
}
