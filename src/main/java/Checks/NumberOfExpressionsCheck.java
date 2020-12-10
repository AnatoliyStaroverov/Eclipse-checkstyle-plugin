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
		return new int[] { TokenTypes.EXPR };
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.EXPR };
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
		} 
		catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// See if ast is empty, if not then increment count
	// Find the first child and loop through all methods
	// until found.
	private void countExpressionTokens(DetailAST ast) {
		
		
		if (ast.getChildCount() > 0) {

			expCount += ast.getChildCount(TokenTypes.EXPR);

			DetailAST child = ast.getFirstChild();

			while (child != null) {
				countExpressionTokens(child);
				child = child.getNextSibling();
			}
		}
	}
	
	
}
