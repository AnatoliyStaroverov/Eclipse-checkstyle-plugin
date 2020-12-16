package Checks;

import com.puppycrawl.tools.checkstyle.api.*;

public class NumberOfExpressionsCheck extends AbstractCheck {

	private int expCount;
	
	// Get expressions counts 
		public int getCount() {
			return expCount;
		}


	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF 
							
							};
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF  };
	}


	@Override
	public void visitToken(DetailAST aAST) {
		countExpressionTokens(aAST);
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	// initalized 
	public void beginTree(DetailAST rootAST) {
		expCount = 0;
	}

	// log out the results.
	public void finishTree(DetailAST rootAST) {
		try {
			log(rootAST, "Number of Expressions :" + expCount);
		} catch (NullPointerException e) {
			System.out.println("Error from treewalker!");
		}
	}

	// See if ast is empty, if not then increment count
	// Find the first child and loop through all methods
	// until found.
	private void countExpressionTokens(DetailAST ast) {
		
		// If accepted token is found in tree.
		if (ast.getChildCount() > 0) {

			// Returns the number of direct child tokens that have the specified type.
			expCount += ast.getChildCount(TokenTypes.EXPR);

			//Get the first child of this AST.
			DetailAST child = ast.getFirstChild();

			// recursively call till child is empty
			while (child != null) {
				
				countExpressionTokens(child);
				
				//Get the next sibling in line after this one.
				child = child.getNextSibling();
			}
		}
	}
	
	
}
