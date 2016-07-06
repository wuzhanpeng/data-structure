package tree.redblacktree;

/**
 * 
 * @author Zhanpeng Wu
 * 
 * @version 2016/01/17
 *
 */
public class RBTNode {

	RBColor color;
	
	/** test */
	int key;
	RBTNode parent;
	RBTNode left;
	RBTNode right;
	
	public RBTNode(int key) {
		
		this.key = key;
		this.color = RBColor.RED;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}
