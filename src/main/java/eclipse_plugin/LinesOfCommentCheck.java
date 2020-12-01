package eclipse_plugin;
import com.puppycrawl.tools.checkstyle.api.*;

public class LinesOfCommentCheck extends AbstractCheck {

	int lineCount = 0;
	
	public void beginTree(DetailAST rootAST) {
		lineCount = 0;
	}
	
	@Override
	public void visitToken(DetailAST ast) {

		switch (ast.getType()) {
		case TokenTypes.SINGLE_LINE_COMMENT:
			lineCount++;
			break;

		case TokenTypes.BLOCK_COMMENT_BEGIN:
			lineCount++;
			lineCount += ast.getChildCount();
			break;
		}
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
		return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
	}

	public int getCount() {
		return this.lineCount;
	}
}
