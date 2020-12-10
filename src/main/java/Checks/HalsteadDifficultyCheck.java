package Checks;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadDifficultyCheck extends AbstractCheck {
	
	private double halsteadDifficulty;

	private NumberOfOperandsCheck operandCount = new NumberOfOperandsCheck(); 
	private NumberOfOperatorCheck operatorCount = new NumberOfOperatorCheck();

	private ArrayList<Integer> operandTokens = arrayToList(operandCount.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(operatorCount.getDefaultTokens());

	@Override // initialized other checks.
	public void beginTree(DetailAST rootAST) {
		operandCount.beginTree(rootAST); 
		operatorCount.beginTree(rootAST);
	}

	@Override // Visit tokens based on type.
	public void visitToken(DetailAST ast) {
		if (operandTokens.contains(ast.getType())) { 
			operandCount.visitToken(ast);
		}
		if (operatorTokens.contains(ast.getType())) { 
			operatorCount.visitToken(ast);
		}
	}

	// 	Halstead Difficulty is half of the unique operators 
	// multiplied by the total number of operands, 
	// divided by the number of unique operands 
	public double CalcHalsteadDifficulty() {
		
		double diff = (getUniqueOperators() / 2);  
		double iculty = (getOperands() / getUniqueOperands());
		halsteadDifficulty = diff * iculty;
		
		return halsteadDifficulty ;
	}
	
	@Override // log out results.
	public void finishTree(DetailAST rootAST) {
	
		double difficultyResults = CalcHalsteadDifficulty();
		try { 
			log(0, "Halstead Difficulty: " + difficultyResults);
		} catch (NullPointerException e) {
			System.out.println("Error from treewalker!");
		}
	}

	// Public getter for the halstead length.
	public double getHalsteadDifficulty() {
		return halsteadDifficulty;
	}

	// getter for getUniqueOperators
	public double getUniqueOperators() {
		return (double) operatorCount.getOperatorUniqueCount();
	}

	// getter for getUniqueOperands
	public double getUniqueOperands() {
		return (double) operandCount.getOperandUniqueCount();
	}

	// getter for getOperands
	public double getOperands() {
		return (double) operandCount.getOperandCount();
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

	
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}

}
