package Checks;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVocabularyCheck extends AbstractCheck {
	
	private int halsteadVocabulary;
	

  int[] halsteadVocabTokens = { 
			
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
			System.out.println("(finish tree) Error from treewalker!");
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
		
		return halsteadVocabTokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		
		return halsteadVocabTokens;
	}

	@Override
	public final int[] getRequiredTokens() {
		
		return halsteadVocabTokens;
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
