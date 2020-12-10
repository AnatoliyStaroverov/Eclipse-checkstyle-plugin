package Checks;


import com.puppycrawl.tools.checkstyle.api.*;
public class NumberOfLoopsCheck extends AbstractCheck {

	
	public int loopMax = 0;
	public int loopCount;
	
	public int for_count;
	public int do_count;
	public int while_count;
	
	public void beginTree(DetailAST rootAST) {
		loopCount = 0;
		while_count = 0;
		do_count = 0;
		for_count = 0;
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] { 
				TokenTypes.LITERAL_FOR, 
				TokenTypes.LITERAL_WHILE, 
				TokenTypes.LITERAL_DO, 
				};
	}

	//@Override
	//public boolean isCommentNodesRequired() {
	//	return true;
	//}

	@Override
	public int[] getRequiredTokens() {
		return new int[] { 
				TokenTypes.LITERAL_FOR, 
				TokenTypes.LITERAL_WHILE, 
				TokenTypes.LITERAL_DO, 
				};
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
				TokenTypes.LITERAL_FOR, 
				TokenTypes.LITERAL_WHILE, 
				TokenTypes.LITERAL_DO 
				};
	}

	public void setMax(int max) {
		loopMax = max;
	}

	@Override
	public void visitToken(DetailAST ast) {

		
		switch (ast.getType()) {
		case TokenTypes.LITERAL_FOR:
			loopCount++;
			for_count++;
			break;

		case TokenTypes.LITERAL_WHILE:
			loopCount++;
			while_count++;
			break;

		case TokenTypes.LITERAL_DO:
			loopCount++;
			do_count++;
			break;
		}
		System.out.println("loop visit token "+ loopCount);
	}

	public int getLoopCount() {
		
		return loopCount;
	}
	
	public void finishTree(DetailAST rootAST) {
		try {
			log(0, " loop count: {0}. You have exceeded loop max", loopCount);
			System.out.println("loop debug"+ loopCount);
			
		} catch (NullPointerException e) {
			System.out.println("Error in  treewalker!");
		}
	}

	
}
