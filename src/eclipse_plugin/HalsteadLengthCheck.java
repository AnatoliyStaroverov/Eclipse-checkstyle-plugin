package eclipse_plugin;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {
	
	int count = 0;
	
	  @Override
	    public void beginTree(DetailAST rootAST) {
	        // No code by default, should be overridden only by demand at subclasses
	    	count = 0;
	    }

	
	 @Override
	    public int[] getDefaultTokens() {
	        return new int[] {TokenTypes.COMMENT_CONTENT};
	    }
	  
	 @Override
		public int[] getRequiredTokens() {
			// TODO Auto-generated method stub
			return new int[0];
		}
	 
	 @Override
	    public void visitToken(DetailAST aAST) {
	      DetailAST objBlock = aAST.findFirstToken(TokenTypes.COMMENT_CONTENT);
		  count++;
		  //ReportCommentDetection( objBlock,"found comments");
	       
	    }
	 
	 @Override
		public int[] getAcceptableTokens() {
			// TODO Auto-generated method stub
			return new int[] {TokenTypes.COMMENT_CONTENT}; 
		}
	 
	 @Override
	    public void finishTree(DetailAST rootAST) {
	       log(rootAST.getLineNo(),count+"");
	    }

	

}
