
public class BST{
	// this Binary Search Tree is used for the implementation of the 
	// Symbol Table containing Symbols: (id,value) pairs
	// A Symbol is a Comparable object containing a String Identifier  
	// and a Boolean value 
	private BSTNode<IdVal> root;

	//empty tree
	public BST(){
		this.root = null;
	}

	public boolean isEmpty(){
		return root==null;
	}

	public void insertItem(IdVal item) throws BSTException{
		root = insertItem(root, item);
	}

	private BSTNode<IdVal> insertItem(BSTNode<IdVal> node, IdVal item) throws BSTException{
		BSTNode<IdVal> newSubTree;
		if(node==null){
			newSubTree = new BSTNode<IdVal>(item);
			return newSubTree;
		}
		
		IdVal tok = node.getItem();
		if(item.getKey().compareTo(tok.getKey())<0){
			newSubTree = insertItem(node.getLeft(), item);
			node.setLeft(newSubTree);
			return node;
		}
		if(item.getKey().compareTo(tok.getKey())>0){
			newSubTree = insertItem(node.getRight(), item);
			node.setRight(newSubTree);
			return node;
		}
		// ERROR: inserting existing item
		else 
			throw new BSTException("Inserting item with existing key!");
	}

	public IdVal retrieveItem(String key){
		return retrieveItem(root,key);
	}
	
	private IdVal retrieveItem(BSTNode<IdVal> node, String key){
		IdVal treeItem;
		
		if(node==null)
			treeItem = null;
		else{
			IdVal nodeItem = node.getItem();
			if(key.compareTo(nodeItem.getKey()) == 0)
				//found
				treeItem = nodeItem;
			else if(key.compareTo(nodeItem.getKey()) < 0)
				//search left
				treeItem = retrieveItem(node.getLeft(), key);
			else
				// search right
				treeItem = retrieveItem(node.getRight(), key);
		}
		return treeItem;

	}
	
	/** DELETE
deleteItem(in rootNode:TreeNode, in searchKey:KeyType): TreeNode
	if (rootNode is null){ throw TreeException}
	else if (searchKey equals key in rootNode item) { //found it
		newRoot = deleteNode(rootNode)
		return newRoot }
	else if (searchKey < key in rootNode item) { //search left
		newLeft = deleteItem(rootNode.getLeft(), searchKey)
		rootNode.setLeft(newLeft)
		return rootNode }
	else { // search right
		newRight = deleteItem(rootNode.getRight(), searchKey)
		rootNode.setRight(newRight)
		return rootNode }
	*/

	public void deleteItem(String key)throws BSTException{
		root = deleteItem(root,key);
	}
	
	private BSTNode<IdVal> deleteItem(BSTNode<IdVal> node, String key) throws BSTException{
		if(node==null){
			throw new BSTException("No node found with key: \""+key+"\"");
		}
		//found it

		//System.out.println("key="+key+"\titem="+node.getItem().getKey()+"\tcompareTo="+key.compareTo(node.getItem().getKey()));
		else if(key==node.getItem().getKey()){
		//else if(key.compareTo(node.getItem().getKey())==0){
			BSTNode<IdVal> newRoot = deleteNode(node);
			return newRoot;
		}
		//search left
		else if(key.compareTo(node.getItem().getKey())<0){
			BSTNode<IdVal> newLeft=deleteItem(node.getLeft(),key);
			node.setLeft(newLeft);
			return node;
		}
		//search right
		BSTNode<IdVal> newRight=deleteItem(node.getRight(),key);
		node.setRight(newRight);
		return node;
	}
	
	private BSTNode<IdVal> deleteNode(BSTNode<IdVal> node) {
		//node is leaf
		if(node.getLeft()==null && node.getRight()==null){
			return null;
		}
		
		//node has only once child
		else if(node.getLeft()!=null && node.getRight()==null){
			return node.getLeft();
		}
		
		else if(node.getLeft()==null && node.getRight()!=null){
			return node.getRight();
		}
		
		//else find and delete leftmost child on right
		else{
			node.setItem((findLeftMostItem(node.getRight())));
			node.setRight(deleteLeftMostNode(node.getRight()));
			return node;
		}
	}

	private BSTNode<IdVal> deleteLeftMostNode(BSTNode<IdVal> node) {
		if(node.getLeft()==null){//found the node to delete
			return node.getRight();
		}
		node.setLeft(deleteLeftMostNode(node.getLeft()));
		return node;
	}

	private IdVal findLeftMostItem(BSTNode<IdVal> node) {
		if(node.getLeft()!=null){//didn't find the node to delete yet
			findLeftMostItem(node.getLeft());
		}
		return node.getItem();
	}

	public void preorderTraverse(){
		if (!isEmpty())
			preorderTraverse(root,"");
		else
			System.out.println("root is null");
	}

	public void preorderTraverse(BSTNode<IdVal> node, String indent){
		System.out.println(indent+node.getItem());		
		if(node.getLeft()!=null) {
			System.out.println(indent+"left");
			preorderTraverse(node.getLeft(),indent+" ");
		}

		if(node.getRight()!=null) {
			System.out.println(indent+"right");
			preorderTraverse(node.getRight(),indent+" ");
		}
	}

}

