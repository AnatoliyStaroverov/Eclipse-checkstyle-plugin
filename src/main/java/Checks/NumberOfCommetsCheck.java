
package Checks;
import com.puppycrawl.tools.checkstyle.api.*;



public class NumberOfCommetsCheck extends AbstractCheck {
	
	int count;
	
	
	
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
	  
	  	
	  @Override // add to count if found.
	    public void visitToken(DetailAST aAST) {
		  count++; 
	    }
	  
	  	
	  @Override // Make the compile check for commits.
	   public boolean isCommentNodesRequired() {
	  		return true;
	  	}
	  
	 public int  getResults() {
		return count;
	 }
	  
	  @Override
	    public void finishTree(DetailAST rootAST) {
		  try {
	       log(rootAST.getLineNo(),count+"");
		  } catch (NullPointerException e) {
				System.out.println("Error from treewalker!");
			}
	    }
	  
		@Override
		public int[] getAcceptableTokens() {
		
			return new int[] {TokenTypes.COMMENT_CONTENT}; 
		}

	
		
	  
}
