public class TreeNode {
    
    private String item;
    private TreeNode left;
    private TreeNode right;
    
    // leaf
    public TreeNode(String item) {
	this.item = item;
	left = null;
	right = null;
    }

    // unary tree, left gets the child tree
    public TreeNode(String item, TreeNode child) {
	this.item = item;
	this.left = child;
	this.right = null;
    }
    
    // binary tree
    public TreeNode(String item, TreeNode left, TreeNode right) {
	this.item = item;
	this.left = left;
	this.right = right;
    }
    
    String getItem() {
	return item;
    }
    
    TreeNode getLeft() {
	return left;
    }
    
    TreeNode getRight() {
	return right;
    }
    
    public String toString() {
	if (right == null)
	    if (left == null)
		return "leaf " + item;
	    else
		return "unary " + item;
	else
	    return "binary " + item;
	    
    }
    
    public static void main(String[] args) {
	TreeNode tn1 = new TreeNode("1.2");
	TreeNode tn2 = new TreeNode("3.4");
	TreeNode atree = new TreeNode("+", tn1, tn2);
	
	System.out.println("plus tree: " + atree);
	System.out.println("  left:  " + atree.getLeft());
	System.out.println("  right: " + atree.getRight());
	
	TreeNode tn3 = new TreeNode("6.022e+23");
	TreeNode ntree = new TreeNode("abs",tn3);
	System.out.println("abs tree: " + ntree);
	System.out.println("  left:  " + ntree.getLeft());		
    }
    
}
