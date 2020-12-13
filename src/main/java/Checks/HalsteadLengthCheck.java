package Checks;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;


public class HalsteadLengthCheck extends AbstractCheck {
	
		public int halsteadLength;
		
		int[] halsteadTokens = { 
				
				/* Unary Operator Type*/	
				TokenTypes.POST_INC,TokenTypes.POST_DEC,TokenTypes.DEC,TokenTypes.INC,
				TokenTypes.LNOT,TokenTypes.BNOT,TokenTypes.UNARY_MINUS,TokenTypes.UNARY_PLUS,
				
				/* Arithmetic Operator type */
				TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,TokenTypes.PLUS,TokenTypes.MINUS,
				TokenTypes.BSR,TokenTypes.SR,TokenTypes.SL,
				
				/* Relational Operator type */
				TokenTypes.LT,TokenTypes.GT,TokenTypes.LE,TokenTypes.GE,
				TokenTypes.LITERAL_INSTANCEOF,TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,
				
				/* Bitwise */
				TokenTypes.BAND,TokenTypes.BXOR,TokenTypes.BOR,
				
				/* Logical Operator type */
				TokenTypes.LAND,TokenTypes.LOR,
				
				/* Ternary  Operator type */
				TokenTypes.QUESTION,TokenTypes.EOF,
				
				/* Assignment  Operator type  */
				TokenTypes.ASSIGN,TokenTypes.BAND_ASSIGN,TokenTypes.BOR_ASSIGN,
				TokenTypes.BSR_ASSIGN,TokenTypes.BXOR_ASSIGN,TokenTypes.DIV_ASSIGN,
				TokenTypes.MINUS_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.PLUS_ASSIGN,
				TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.STAR_ASSIGN,
				
				// operands 
				TokenTypes.IDENT, 
				TokenTypes.NUM_DOUBLE,
				TokenTypes.NUM_FLOAT,
				TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG 
				
		};
		
		

		// Create object instance of Operand and operators for calculation.
		private  NumberOfOperandsCheck operandCount = new NumberOfOperandsCheck();
		private NumberOfOperatorCheck operatorCount = new NumberOfOperatorCheck();
		
		private ArrayList<Integer> operandTokens = arrayToList(operandCount.getDefaultTokens());
		private ArrayList<Integer> operatorTokens = arrayToList(operatorCount.getDefaultTokens());

		//getter for Halstead length.
		public int getHalsteadLength() {
			return halsteadLength;
		}

		// getters operand count.
		public int getOperandCount() {
			return operandCount.getOperandCount();
		}

		// getter for operator count.
		public int getOperatorCount() {
			return operatorCount.getOperatorCount();
		}
		
		// Calculates Halstead length.
		public int calcHalsteadLength() {
			halsteadLength = getOperandCount() +  getOperatorCount();
			return halsteadLength;
		}
		
		// Creates array list from int array.
		private ArrayList<Integer> arrayToList(int[] array) {
			ArrayList<Integer> newList = new ArrayList<Integer>();
			for (int i : array) {
				newList.add(i);
			}
			return newList;
		}

		@Override
		public void beginTree(DetailAST rootAST) {
			operandCount.beginTree(rootAST);
			operatorCount.beginTree(rootAST);
		}
		
		@Override
		public int[] getDefaultTokens() {
			return halsteadTokens;
		}

		@Override
		public int[] getAcceptableTokens() {
			return halsteadTokens;		
		}

		@Override
		public final int[] getRequiredTokens() {
			return halsteadTokens;
		}


		@Override
		public void visitToken(DetailAST ast) {
			
			if (operatorTokens.contains(ast.getType())) {
				operatorCount.visitToken(ast);
			}
			if (operandTokens.contains(ast.getType())) {
				operandCount.visitToken(ast);
			}
		}

		@Override
		public void finishTree(DetailAST rootAST) {

			try {
				log(0, "Halstead Length: " + calcHalsteadLength());
			} catch (NullPointerException e) {
				System.out.println("Error from treewalker!");
			}
		}
	  
}