public class Equation {
 
    // you can choose to use debug or not
    // we will not test debug mode
    private boolean debug;
 
    // contains next Token or null at end of line
    private String nextToken;
 
    // Token Iterator, set in constructor
    private TokenIter itTokens;
 
    // IMPLEMENT
 
    public Equation(TokenIter iter, boolean debug) {
        //System.out.println("Calling constructor");
        itTokens = iter;
        this.debug = debug;
        if(itTokens.hasNext()) {
            nextToken = itTokens.next();
        }
    }
 
    // provided
    private void error(String errMess) throws ParseException {
    throw new ParseException(errMess);
    }
 
 
    // TODO
    public IdVal line(BST symbolTable) throws BSTException, ParseException {
        //System.out.println("Calling line");
    	String id=null;
    	Boolean val;
    	IdVal res=null;
    	if(nextToken!=null) {
    		id = nextToken;
    		//System.out.println("id:"+id);
    		if(itTokens.hasNext()) {
    			nextToken = itTokens.next();
    			if(nextToken.equals("=")) {
    				if(itTokens.hasNext()) {
    					nextToken = itTokens.next();
    				}
    				else {
    					nextToken = null;
    				}
    			}
    			else {
    				nextToken = null;
    			}
    		}
    	}
        TreeNode root;
        Tree tree = new Tree();
        if (nextToken!=null) {
            root = expr("");
            tree = new Tree(root,symbolTable);
            val = tree.postorderEval();
            res = new IdVal(id,val);
            symbolTable.insertItem(res);
        }
        else {
        	res = null;
        }
        if (nextToken!=null)
            error("end of line expected");
        return res;
    }
 
 
    // IMPLEMENT
    
    private TreeNode expr(String indent) throws ParseException {
    	if(nextToken.equals(")")) {
    		return null;
    	}
    	TreeNode root = null;
    	TreeNode left = term(" ");

    	if(nextToken!=null) {
    		if(nextToken.equals("|")) {
    			
    			String sign = nextToken;
    			if(!(itTokens.hasNext())) {
    				error("invalid expression");
    			}
    			else {
    				nextToken = itTokens.next();
    				if(nextToken.equals(")")) {
    					error("invalid expression");
    				}
    				else if (nextToken.equals("(")){
    					if(!(itTokens.hasNext())) {
    						error("invalid expression");
    					}
    					else {
    						nextToken = itTokens.next();
    						TreeNode right = expr(" ");
    						root = new TreeNode(sign,left,right);
    						if(!(nextToken == null)) {
    							if(nextToken.equals(")")) {
    								if(itTokens.hasNext()) {
    									nextToken = itTokens.next();
    								}
    								else {
    									nextToken = null;
    								}
    							}
    						}

    						//break;
    					}
    				}
    				else {
    					TreeNode right = term(" ");
    					root = new TreeNode(sign,left,right);
    					//break;
    				}
    			}
    		}

    	}

    	else {
    		root = left;
    	}

    	if(nextToken != null) {
    		while(nextToken.equals("|")) {
    			left = root;
    			String sign = nextToken;
    			if(!(itTokens.hasNext())) {
    				error("invalid expression");
    			}
    			else {
    				nextToken = itTokens.next();
    				TreeNode right = term("  ");
    				root = new TreeNode(sign,left,right);
    				if(nextToken == null) {
    					break;
    				}
    			}

    		}
    	}

    	if(nextToken != null) {
    		if(nextToken.equals(")")) {
    			if(!(itTokens.hasNext())) {
    				nextToken = null;
    			}
    			else {
    				nextToken = itTokens.next();
    			}
    		}
    	}
    	return root;
    }
  
 
    // term
    private TreeNode term(String indent) throws ParseException {
    	TreeNode root = null;
    	TreeNode left = factor("  ");
    	if(nextToken!=null) {
    		if(nextToken.equals("&")) {
    			String sign = nextToken;
    			if(!(itTokens.hasNext())) {
    				error("invalid expression");
    			}
    			else {
    				nextToken = itTokens.next();
    				if(nextToken.equals(")")) {
    					error("invalid expression");
    				}
    				else if (nextToken.equals("(")){
    					if(!(itTokens.hasNext())) {
    						error("invalid expression");
    					}
    					else {
    						nextToken = itTokens.next();
    						TreeNode right = expr("   ");
    						root = new TreeNode(sign,left,right);
    						if(!(nextToken == null)) {
    							if(nextToken.equals(")")) {
    								if(itTokens.hasNext()) {
    									nextToken = itTokens.next();
    								}
    							}
    						}
    						//break;
    					}
    				}
    				else {
    					TreeNode right = factor("  ");
    					root = new TreeNode(sign,left,right);
    					//break;
    				}
    			}
    		}
    		else {
    			root = left;
    		}
    	}
    	else {
    		root = left;
    	}
        
        
        if(nextToken != null) {
        while(nextToken.equals("&") ) {
            left = root;
            String sign = nextToken;
            if(!(itTokens.hasNext())) {
                error("invalid expression");
            }
            else {
                nextToken = itTokens.next();
                TreeNode right = factor("   ");
                root = new TreeNode(sign,left,right);
                if(nextToken == null) {
    				break;
    			}
            }
           
        }
        }
         
        return root;
    }
    
 
   //factor
    private TreeNode factor(String indent) throws ParseException {
        TreeNode root;
        switch(nextToken) {
        case "!": {
            if(!(itTokens.hasNext())) {
                error("invalid expression");
            }
            else {
                nextToken = itTokens.next();
                TreeNode child = factor("   ");
                root = new TreeNode("!",child);
                break;
            }
        }
         
        case "(": {
        	if(!(itTokens.hasNext())) {
        		error("invalid expression");
        	}
        	else {
        		nextToken = itTokens.next();
        		root = expr("   ");
        		if(nextToken != null) {
					if(nextToken.equals(")")) {
						if(itTokens.hasNext()) {
    						nextToken = itTokens.next();
    					}
					}
				}
        		
        		break;
        	}
        }

        case ")": {
        	error("invalid expression");
        }
        
        default: {
        	root = new TreeNode(nextToken);
        	if(itTokens.hasNext()) {
                nextToken = itTokens.next();
            }
            else {
                nextToken = null;
            }
        	break;
        }
         
        }
        return root;
    }
 
}
