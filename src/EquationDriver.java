import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EquationDriver {
    public static void main(String[] args) throws FileNotFoundException,
						  BSTException,
						  ParseException {
	Scanner scan = new Scanner(new File(args[0]));
	boolean debug = (args.length > 1);
	// initialize symTab
	BST symbolTable = new BST();
	// loop through lines in input file
	while (scan.hasNextLine()) {
	    String line = scan.nextLine();
	    System.out.println("next line: " + line);
	    try {
		TokenIter tokIt = new TokenIter(line);
		Equation nextEq = new Equation(tokIt, debug);
		IdVal res = nextEq.line(symbolTable);
		System.out.println("result: " + res);
	    }
	    catch (ParseException PE) {
		System.out.println("Parse Exception caught");
		if (debug)
		    System.out.println("Message: " + PE.getMessage());
	    }
	    catch (BSTException BE) {
		System.out.println("BST Exception caught");
		if (debug)
		    System.out.println("Message: " + BE.getMessage());
	    } 

	    if (debug) {
		System.out.println("SYMBOL TABLE");
		symbolTable.preorderTraverse();
		System.out.println("END SYMBOL TABLE");
	    }
	}
    }
}
