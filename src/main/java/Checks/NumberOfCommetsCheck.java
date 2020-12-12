
package Checks;
import com.puppycrawl.tools.checkstyle.api.*;



public class NumberOfCommetsCheck extends AbstractCheck {
	
	int count;
	
	public int getCount() {
		return this.count;
	}
	
	  @Override
	    public void beginTree(DetailAST rootAST) {
	    	count = 0;
	    }

	  @Override
	    public int[] getDefaultTokens() {
	        return new int[] {TokenTypes.COMMENT_CONTENT};
	    }
	  
	  @Override
		public int[] getRequiredTokens() {
			
		  return getDefaultTokens();
		}
	  
	  	// public final void setTokens(String... strRep)
	  @Override
	    public void visitToken(DetailAST aAST) {
		  count++; 
	    }
	  
	  	// Make the compile check for commits.
	  @Override
	   public boolean isCommentNodesRequired() {
	  		return true;
	  	}
	  
	 public int  getResults() {
		return count;
	 }
	  
	  @Override
	    public void finishTree(DetailAST rootAST) {
	       log(rootAST.getLineNo(),count+"");
	    }
	  
		@Override
		public int[] getAcceptableTokens() {
		
			return new int[] {TokenTypes.COMMENT_CONTENT}; 
		}

	
		
	  
}
