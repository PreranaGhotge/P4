public class Tree {

    // Provided, DON'T CHANGE
    // the root of the tree
    private TreeNode root;

    //empty tree
    public Tree(){
	this.root = null;
    }

    // rootItem, empty children
    public Tree(TreeNode root){
	this.root = root;
    }

    public boolean isEmpty(){
	return root==null;
    }

    public void preorderTraverse(){
	if (!isEmpty())
	    preorderTraverse(root,"");
	else
	    System.out.println("null");
    }

    // print root item
    // print left tree
    // print right tree
    public void preorderTraverse(TreeNode node, String indent){
	System.out.println(indent+node.getItem());
	if (node.getLeft()!=null) preorderTraverse(node.getLeft(),indent+" ");
	if (node.getRight()!=null) preorderTraverse(node.getRight(),indent+" ");

    }

    // if tree empty return null
    // else evaluate the tree by postorder traversal 
    // and return its value
    public Double postorderEval(){
	Double res = null;
	if (!isEmpty())
	    res = postorderEval(root);
	return res;
    }


    // IMPLEMENT

    public Double postorderEval(TreeNode node){
	// evaluate left tree
    // evaluate right tree (if not null)
    // evaluate operator in node and return Double result
    	Double res = 0.0;
    	Double left = null;
    	Double right = null;
    	
    	if(node.getLeft()!=null) {
    		left = postorderEval(node.getLeft());
    	}
    	else {
    		return Double.parseDouble(node.getItem());
    	}
    	
    	if(node.getRight()!=null) {
    		right = postorderEval(node.getRight());
    	}
    	/*else {
    		System.out.println("token whn entering here:"+node.getItem());
    		return Double.parseDouble(node.getLeft().getItem());
    	}*/
    	
    	//System.out.println(left);
    	//System.out.println(right);
    	
    	switch(node.getItem()) {
    	
    	case "abs": {
    		res = Math.abs(left);
    		break;
    	}
    	
    	case "+": {
    		res = left + right;
    		//System.out.println(res);
    		break;
    	}
    	
    	case "-": {
    		res = left - right;
    		break;
    	}
    	
    	case "/\\": {
    		res = Math.max(left,right);
    		break;
    	}
    	
    	case "\\/": {
    		res = Math.min(left,right);
    		break;
    	}
    	
    	default: {
    	}
    	}
    	
    	return res;
    }	


    // EXERCISE
    public static void main(String[] args){
	System.out.println("Stepwise build infix expression: 5.6 + 7.8");
	
	Tree t = new Tree();
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
	
	TreeNode a = new TreeNode("5.6");
	t = new Tree(a);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
			
	TreeNode b = new TreeNode("7.8");
	TreeNode plus = new TreeNode("+", a, b);
	t = new Tree(plus);
	System.out.println("tree: "); t.preorderTraverse();	
	System.out.println("result:\n" + t.postorderEval()+"\n");
    }
}