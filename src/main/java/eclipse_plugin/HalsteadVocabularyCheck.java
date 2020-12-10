package eclipse_plugin;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVocabularyCheck extends AbstractCheck {
	
	private int halsteadVocabulary;

	private NumberOfOperandsCheck operandCount = new NumberOfOperandsCheck();
	private NumberOfOperatorCheck operatorCount = new NumberOfOperatorCheck();

	//  Keep tokens in List for search ability.
	private ArrayList<Integer> operandTokens = arrayToList(operandCount.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(operatorCount.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		
		operandCount.beginTree(rootAST);
		operatorCount.beginTree(rootAST);
	}

	@Override
	public void visitToken(DetailAST ast) {
		
		// Condition for operand type
		if (operandTokens.contains(ast.getType())) {
			operandCount.visitToken(ast);
		}
		
		// Condition for operator type
		if (operatorTokens.contains(ast.getType())) {
			operatorCount.visitToken(ast);
		}
	}

	// function  Calculate the Vocabulary
	// which is defined as sum of unique
	// Operands and operators.
	void CalcHalsteadVocabulary() {
		int uniqueOperands = getUniqueOperandCount();
		int uniqueOperators = getUniqueOperatorCount();
		halsteadVocabulary = uniqueOperands + uniqueOperators;
	}
	
	
	@Override
	public void finishTree(DetailAST rootAST) {
		
		CalcHalsteadVocabulary();
		
		try {
			log(0, "Halstead Vocabulary: " + halsteadVocabulary);
		} catch (NullPointerException e) {
			System.out.println("Error from treewalker!");
		}
	}

	// Public getter for the halstead vocab.
	public int getHalsteadVocabulary() {
		return halsteadVocabulary;
	}

	// getter for operand 
	public int getUniqueOperandCount() {
		return operandCount.getOperandUniqueCount();
	}

	// getter for operator
	public int getUniqueOperatorCount() {
		return operatorCount.getOperatorUniqueCount();
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

	//  function to create an ArrayList from an integer array
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}
}
