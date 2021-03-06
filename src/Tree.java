public class Tree {

    // Provided, DON'T CHANGE
    // the root of the tree
    private TreeNode root;
    private BST symbolTable;

    //empty tree
    public Tree(){
	this.root = null;
    }

    // rootItem, empty children
    public Tree(TreeNode root,BST symbolTable){
	this.root = root;
	this.symbolTable = symbolTable;
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

 
    public void preorderTraverse(TreeNode node, String indent){
	System.out.println(indent+node.getItem());
	if (node.getLeft()!=null) preorderTraverse(node.getLeft(),indent+" ");
	if (node.getRight()!=null) preorderTraverse(node.getRight(),indent+" ");

    }

    public Boolean postorderEval() throws BSTException{
	Boolean res = null;
	if (!isEmpty())
	    res = postorderEval(root,symbolTable);
	return res;
    }



    public Boolean postorderEval(TreeNode node, BST symTab) throws BSTException{
    	Boolean res = null;
    	Boolean left = null;
    	Boolean right = null;
    	
    	if(node.getLeft()!=null) {
    		left = postorderEval(node.getLeft(),symTab);
    	}
    	else {
    		if(node.getItem().equals("0")) {
    			return false;
    		}
    		else if(node.getItem().equals("1")) {
    			return true;
    		}
    		else {
    			return symTab.retrieveItem(node.getItem()).val;
    		}
    		
    	}
    	
    	if(node.getRight()!=null) {
    		right = postorderEval(node.getRight(),symTab);
    	}
    	
    	
    	switch(node.getItem()) {
    	
    	case "|": {
    		if (left.equals(true) || right.equals(true)) {
    			res = true;
    		}
    		else {
    			res = false;
    		}
    		break;
    	}
    	
    	case "&": {
    		if (left.equals(true) && right.equals(true)) {
    			res = true;
    		}
    		else {
    			res = false;
    		}
    		break;
    	}
    	
    	case "!": {
    		res = !(left);
    	}
   
    	}
    	
    	return res;
    }	


    // EXERCISE
    /*public static void main(String[] args){
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
    }*/
}