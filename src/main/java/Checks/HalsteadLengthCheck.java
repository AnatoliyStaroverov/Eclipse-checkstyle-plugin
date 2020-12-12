package Checks;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;


public class HalsteadLengthCheck extends AbstractCheck {
	
		public int halsteadLength;

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
			return getOperandCount() +  getOperatorCount();
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
			
			// Get operator and operand length 
			int operandLength = operandCount.getDefaultTokens().length;
			int operatorLength = operatorCount.getDefaultTokens().length;
			
			// create new list to return 
			int[] newDefaultTokens = new int[operandLength+operatorLength];
			
			// assign local variables to operand and operator arrays for readability purpose.
			int[] operandTokens = operandCount.getDefaultTokens();
			int[] operatorTokens = operatorCount.getDefaultTokens();
			
			// Merge the two arrays into new array.
			System.arraycopy(operandTokens, 0, newDefaultTokens , 0, operandLength);
			System.arraycopy(operatorTokens, 0, newDefaultTokens, 0, operatorLength);
			
			return newDefaultTokens;
		}

		@Override
		public int[] getAcceptableTokens() {
			
			// Get operator and operand length 
			int operandLength = operandCount.getAcceptableTokens().length;
			int operatorLength = operatorCount.getAcceptableTokens().length;
			
			// create new list to return 
			int[] newAcceptableTokens = new int[operandLength+operatorLength];
			
			// assign local variables to operand and operator arrays for readability purpose.
			int[] operandTokens = operandCount.getAcceptableTokens();
			int[] operatorTokens = operatorCount.getAcceptableTokens();
			
			// Merge the two arrays into new array.
			System.arraycopy(operandTokens, 0, newAcceptableTokens , 0, operandLength);
			System.arraycopy(operatorTokens, 0, newAcceptableTokens, 0, operatorLength);
			
			return newAcceptableTokens;
						
		}

		@Override
		public final int[] getRequiredTokens() {
			
			// Get operator and operand length 
			int operandLength = operandCount.getRequiredTokens().length;
			int operatorLength = operatorCount.getRequiredTokens().length;
			
			// create new list to return 
			int[] newRequiredTokens = new int[operandLength+operatorLength];
			
			// assign local variables to operand and operator arrays for readability purpose.
			int[] operandTokens = operandCount.getRequiredTokens();
			int[] operatorTokens = operatorCount.getRequiredTokens();
			
			// Merge the two arrays into new array.
			System.arraycopy(operandTokens, 0, newRequiredTokens , 0, operandLength);
			System.arraycopy(operatorTokens, 0, newRequiredTokens, 0, operatorLength);
			
			return newRequiredTokens;
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