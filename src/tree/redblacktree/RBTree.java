package tree.redblacktree;

public class RBTree {
	
	private RBTNode root;
	
	public RBTree() {
		
		root = null;
	}
	
	private void search() {
	}
	
	private void insert(RBTNode node) {
		
		RBTNode parent = null;
		RBTNode currentNode = this.root;
		
		while (null != currentNode) {
			parent = currentNode;
			if (node.key < currentNode.key) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
		node.parent = parent;
		// if this tree is empty
		if (null == parent) {
			this.root = node;
		} else {
			if (node.key < parent.key) {
				parent.left = node;
			} else {
				parent.right = node;
			}
		}
		
		// fix up the rb-tree
		insertFixup(node);
	}
	
	private void delete() {
	}
	
	//    |               |
	//    y 			  x         
	//   / \             / \
	//  ¦Á   x    ==> 	y   ¦Ã
	//     / \         / \
	//    ¦Â   ¦Ã	      ¦Á   ¦Â
	private void leftRotate(RBTNode node) {
		
		// move the rightChild's left child to currentNode's right
		RBTNode rightChild = node.right;
		node.right = rightChild.left;
		rightChild.left.parent = node;
		
		// check if currentNode has parent node
		// if currentNode is the root node
		if (null == node.parent) {
			this.root = rightChild;
		} else {
			if (node.parent.left == node) {
				node.parent.left = rightChild;
			} else {
				node.parent.right = rightChild;
			}
		}
		
		// move the currentNode to the rightChild's left
		rightChild.left = node;
		node.parent = rightChild;
	}
	
	//		|           |
	// 		x           y
	// 	   / \         / \
	// 	  y   ¦Ã  ==>  ¦Á   x
	// 	 / \             / \
	//  ¦Á   ¦Â           ¦Â   ¦Ã
	private void rightRotate(RBTNode node) {
		
		// move the leftChild's right child to currentNode's left
		RBTNode leftChild= node.left;
		node.left = leftChild.right;
		leftChild.right.parent = node;
		
		// check if currentNode has parent node
		// if currentNode is the root node
		if (null == node.parent) {
			this.root = leftChild;
		} else {
			if (node.parent.left == node) {
				node.parent.left = leftChild;
			} else {
				node.parent.right = leftChild;
			}
		}
		
		// move the currentNode to the leftChild's right
		leftChild.right = node;
		node.parent = leftChild;
	}

	private void insertFixup(RBTNode node) {
		
		while (RBColor.RED == node.parent.color) {
			// the node's parent is its grandpa's left child
			if (node.parent == node.parent.parent.left) {
				RBTNode uncleNode = node.parent.parent.right;
				if (RBColor.RED == uncleNode.color) {
					// Case-1
					// recolor parent/uncle to black
					node.parent.color = RBColor.BLACK;
					uncleNode.color = RBColor.BLACK;
					// recolor grandpa to red
					node.parent.parent.color = RBColor.RED;
					node = node.parent.parent;
				} else {
					if (node == node.parent.right) {
						// Case-2
						// left rotate to case 3
						//    x
						//   /
						//  y
						//   \
						//    z
						node = node.parent;
						leftRotate(node);
					}
					
					// Case-3
					//      x
					//     /
					//    y
					//   /
					//  z
					node.parent.color = RBColor.BLACK;
					node.parent.parent.color = RBColor.RED;
					rightRotate(node.parent.parent);
				}
			}
			
			// right child case is the mirror image of left child case
			else {
				RBTNode uncleNode = node.parent.parent.left;
				if (RBColor.RED == uncleNode.color) {
					// Case-4
					// recolor parent/uncle to black
					node.parent.color = RBColor.BLACK;
					uncleNode.color = RBColor.BLACK;
					// recolor grandpa to red
					node.parent.parent.color = RBColor.RED;
					node = node.parent.parent;
				} else {
					if (node == node.parent.left) {
						// Case-5
						// left rotate to case 6
						//  x
						//   \
						//    y
						//   /
						//  z
						node = node.parent;
						rightRotate(node);
					}
					
					// Case-3
					//  x
					//   \
					//    y
					//     \
					//      z
					node.parent.color = RBColor.BLACK;
					node.parent.parent.color = RBColor.RED;
					leftRotate(node.parent.parent);
				}
			}
		}
	}
}
