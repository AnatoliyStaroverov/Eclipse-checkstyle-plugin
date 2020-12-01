package eclipse_plugin;


import com.puppycrawl.tools.checkstyle.api.*;
public class NumberOfLoopsCheck extends AbstractCheck {

	
	public int loopMax = 0;
	public int loopCount;
	
	public void beginTree(DetailAST rootAST) {
		loopCount = 0;
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return getRequiredTokens();
	}

	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO };
	}

	public void setMax(int max) {
		loopMax = max;
	}

	@Override
	public void visitToken(DetailAST ast) {

		switch (ast.getType()) {
		case TokenTypes.LITERAL_FOR:
			loopCount++;
			break;

		case TokenTypes.LITERAL_WHILE:
			loopCount++;
			break;

		case TokenTypes.LITERAL_DO:
			loopCount++;
			break;
		}
	}

	public int getLoopCount() {
		
		return loopCount;
	}
	
	public void finishTree(DetailAST rootAST) {
		try {
			log(0, " loop count: {0}. You haveexceeded loop max", loopCount);
		} catch (NullPointerException e) {
			System.out.println("Error in  treewalker!");
		}
	}

	
}
