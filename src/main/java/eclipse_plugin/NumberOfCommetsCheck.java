//
package eclipse_plugin;

import com.puppycrawl.tools.checkstyle.api.*;


// hello world 
public class NumberOfCommetsCheck extends AbstractCheck {
	
	//private static final String CATCH_MSG = "Number of comments in file. ";
	
	int count = 0;
	
	public int getCount() {
		return this.count;
	}
	
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
	  
	  	// public final void setTokens(String... strRep)
	  @Override
	    public void visitToken(DetailAST aAST) {
	      //DetailAST objBlock = aAST.findFirstToken(TokenTypes.COMMENT_CONTENT);
		  count++;
		  //ReportCommentDetection( objBlock,"found comments");
	       
	    }
	 
	  //private void ReportCommentDetection(DetailAST aAST, String comment) {
		  //log(aAST.getLineNo(),comment);
	 // }
	  
	  	// Make the compile check for commits.
	  @Override
	   public boolean isCommentNodesRequired() {
	  		return true;
	  	}
	  
	 
	  
	  @Override
	    public void finishTree(DetailAST rootAST) {
	       log(rootAST.getLineNo(),count+"");
	    }
	  
		@Override
		public int[] getAcceptableTokens() {
			// TODO Auto-generated method stub
			return new int[] {TokenTypes.COMMENT_CONTENT}; 
		}

	
		
	  
}
