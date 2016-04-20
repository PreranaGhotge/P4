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
        TreeNode root;
        Tree tree = new Tree();
        if (nextToken!=null) {
            root = expr("");
            tree = new Tree(root);
        }
        if (nextToken!=null)
            error("end of line expected");
        return tree;
    }
 
 
    // IMPLEMENT
    
    private TreeNode expr(String indent) throws ParseException {
    	if(nextToken.equals(")")) {
    		return null;
    	}
        TreeNode root = null;
        TreeNode left = term(" ");
        
        if(nextToken!=null) {
        	switch(nextToken) {
        	case "+":
        	case "-": {
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
        					
        					break;
        				}
        			}
        			else {
        				TreeNode right = term(" ");
        				root = new TreeNode(sign,left,right);
        				break;
        			}
        		}
        	}

        	case ")": {
        		root = left;//just one term in parentheses
        	}
        	}
        }
        else {
        	root = left;
        }

        if(nextToken != null) {
        	while(nextToken.equals("+") || nextToken.equals("-")) {
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

        if(!(nextToken == null)) {
			if(nextToken.equals(")")) {
				if(!(itTokens.hasNext())) {
					nextToken = null;
				}
			}
		}
        return root;
    }
         
        
    
             
             
         
 
    // term = factor ( ("/\" | "\/") factor )*
    private TreeNode term(String indent) throws ParseException {
        TreeNode root = null;
        TreeNode left = factor("  ");
        if(nextToken!=null) {
            switch(nextToken) {
            case "/\\":
            case "\\/": {
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
        					break;
        				}
        			}
                    else {
                        TreeNode right = factor("  ");
                        root = new TreeNode(sign,left,right);
                        break;
                    }
                }
            }
             
            case ")": {
                root = left;//just one term in parentheses
            }
             
            default: {
                root = left;
            }
            }
        }
        else {
            root = left;
        }
        
        if(nextToken != null) {
        while(nextToken.equals("/\\") || nextToken.equals("\\/")) {
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
    
 
    // factor = "abs" factor | "(" expr ")" | flopon
    private TreeNode factor(String indent) throws ParseException {
        TreeNode root;
        switch(nextToken) {
        case "abs": {
            if(!(itTokens.hasNext())) {
                error("invalid expression");
            }
            else {
                nextToken = itTokens.next();
                TreeNode child = factor("   ");
                root = new TreeNode("abs",child);
                break;
            }
        }
         
        case "(": {
        	if(!(itTokens.hasNext())) {
        		error("invalid expression");
        	}
        	else {
        		//chk = true;
        		nextToken = itTokens.next();
        		//System.out.println("expr called");
        		root = expr("   ");
        		if(!(nextToken == null)) {
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
            root = flopon("   ");
        }
        }         
        return root;
    }
 
    // flopon = digits ("." digits)? ("e" ("+"|"-") digs)?
    private TreeNode flopon(String indent) throws ParseException {
        //precondition: No bad tokens
        TreeNode leaf;
        leaf = new TreeNode(nextToken);
        if(itTokens.hasNext()) {
            nextToken = itTokens.next();
        }
        else {
            nextToken = null;
        }
        return leaf;
    }
     
    public static void main(String[] args) throws ParseException {
        System.out.println("Testing ParseTreeExpr");
        String line = args[0];
        System.out.println("next line: [" + line +"]");
        TokenIter tokIt = new TokenIter(line);
        ParseTreeExpr buildTree = new ParseTreeExpr(tokIt, false);
        Tree pTree = buildTree.line();
         
        System.out.println("expression tree:"); 
        pTree.preorderTraverse();
         
        System.out.println("result: " + pTree.postorderEval());
    }
}