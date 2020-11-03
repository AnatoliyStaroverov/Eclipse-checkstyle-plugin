package eclipse_plugin;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {
	
	  int count = 0;
	
	
	 /*  Array for importing all the operator and operand types in Java.
	  *  Used "https://www.javatpoint.com/operators-in-java" 
	  *  as a reference.
	  * */
	int[] operatorTokens = {
			
		 /* Unary Operator Type*/	
		TokenTypes.POST_INC,TokenTypes.POST_DEC,TokenTypes.DEC,TokenTypes.INC,
		TokenTypes.LNOT,TokenTypes.LNOT,
		
		/* Arithmetic Operator type */
		TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,TokenTypes.PLUS,TokenTypes.MINUS,
		TokenTypes.BSR,TokenTypes.SR,TokenTypes.SL,
		
		/* Relational Operator type */
		TokenTypes.LT,TokenTypes.GT,TokenTypes.LE,TokenTypes.GE,
		TokenTypes.LITERAL_INSTANCEOF,TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,
		
		/* Bitwise */
		TokenTypes.BAND,TokenTypes.BXOR,TokenTypes.LOR,
		
		/* Logical Operator type */
		TokenTypes.LAND,TokenTypes.LOR,
		
		/* Ternary  Operator type */
		TokenTypes.QUESTION,TokenTypes.EOF,
		
		/* Assignment  Operator type  */
		TokenTypes.ASSIGN,TokenTypes.BAND_ASSIGN,TokenTypes.BOR_ASSIGN,
		TokenTypes.BSR_ASSIGN,TokenTypes.BXOR_ASSIGN,TokenTypes.DIV_ASSIGN,
		TokenTypes.MINUS_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.PLUS_ASSIGN,
		TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.STAR_ASSIGN,
		
		/* operand */
		TokenTypes.NUM_FLOAT,TokenTypes.NUM_LONG,TokenTypes.NUM_DOUBLE,TokenTypes.IDENT,
		TokenTypes.NUM_INT
		
	   };	
	
		
	   public int getHalsteadLength() {
		   return this.count;
	   }
	
	  @Override
	    public void beginTree(DetailAST rootAST) {
	        // No code by default, should be overridden only by demand at subclasses
	    	count = 0;
	    }

	
	 @Override
	    public int[] getDefaultTokens() {
	        return operatorTokens;
	    }
	  
	 @Override
		public int[] getRequiredTokens() {
			// TODO Auto-generated method stub
			return  operatorTokens;
		}
	 
	 @Override
	    public void visitToken(DetailAST aAST) {
		  count++;
	    }
	 
	 @Override
		public int[] getAcceptableTokens() {
			
			return  operatorTokens; 
		}
	 
	 @Override
	    public void finishTree(DetailAST rootAST) {
		 log(rootAST.getLineNo(),count+"");
	    }

	

}
