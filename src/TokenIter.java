import java.util.Iterator;
 
public class TokenIter implements Iterator<String> {
 
    //input line to be tokenized
    private String line;
    private String newLine;
 
    // the next Token, null if no next Token
    private String nextToken;
    private int index;
     
    // implement
    public TokenIter(String line) {
        this.line = line;
        index = 0;
        newLine = line.replaceAll("\\s+", "");
        //System.out.println(newLine);
    }
        
    public String getNextToken(String newLine) {
 
        mainloop:
    	while (nextToken == null && index < newLine.length()) {
             
            if(Character.isLetter(newLine.charAt(index))) {
            	while(Character.isLetterOrDigit(newLine.charAt(index))) {
            		if(nextToken==null) {
            			nextToken = Character.toString(newLine.charAt(index));
            		}
            		else {
            			nextToken = nextToken + Character.toString(newLine.charAt(index));
            		}
            		if(index < newLine.length()-1) {
            			index++;
            		}
            		else {
            			index++;
            			break mainloop;
            		}
            	}
            	break;
            }
            
            switch(newLine.charAt(index)) {
            
            case '=':
            case '!':
            case '|':
            case '&':
            case '(':
            case ')':
            case '0':
            case '1': {
            	nextToken = Character.toString(newLine.charAt(index));
            	index++;
            	break;
            }
            
            default: {
            	index++;
            }
            
            }
    	}
 
        return nextToken;
    }
 
 
    @Override
    // implement
    public boolean hasNext() {
        nextToken = null;
        nextToken = getNextToken(newLine);//helper method
        //System.out.println(nextToken);
        return (nextToken != null);
    }
 
    @Override
    //implement
    public String next() {
        if(nextToken != null) {
            return nextToken;
        }
         
        else {
            return getNextToken(newLine);
        }
     
    }
 
    @Override
    // provided, do not change
    public void remove() {
    throw new UnsupportedOperationException();
    }
 
    // provided
    public static void main(String[] args) {
    String line;
    // you can play with other inputs on the command line
    if(args.length>0)
        line = args[0];
    // or do the standard test
    else
        line = "3.";
    System.out.println("line: [" + line + "]");
    TokenIter tokIt = new TokenIter(line);
    while (tokIt.hasNext())
        System.out.println("next token: [" + tokIt.next() + "]");
    }
}