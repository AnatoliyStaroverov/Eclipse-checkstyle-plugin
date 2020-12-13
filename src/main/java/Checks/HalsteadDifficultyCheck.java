package Checks;
import java.util.ArrayList;


import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadDifficultyCheck extends AbstractCheck {
	
	private double halsteadDifficulty;
 int[] HalsteadDifficultytokens = { 
			
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
		
		return HalsteadDifficultytokens;
	}

	@Override
	public int[] getAcceptableTokens() {
	
		return HalsteadDifficultytokens;
	}

	@Override
	public final int[] getRequiredTokens() {
	
		return HalsteadDifficultytokens;
	}

	
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}

}
