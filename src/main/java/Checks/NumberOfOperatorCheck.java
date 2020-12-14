package Checks;


import java.util.Arrays;
import java.util.HashSet;
import com.puppycrawl.tools.checkstyle.api.*;

public class NumberOfOperatorCheck extends AbstractCheck {
	
	int[] operatorTokens = {
			
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
			
			
			
		   };	
	
	int operatorCount = 0;
	HashSet<String> uniqueOperators = new HashSet<String>(); 

	@Override
	public void beginTree(DetailAST rootAST) {
		operatorCount = 0; 
		uniqueOperators = new HashSet<String>();
	}

	public void visitToken(DetailAST aAST) {
		
		operatorCount++;
		uniqueOperators.add(aAST.getText()); 
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		try {
			log(0, " {0} unique operators that appear {1} times.", uniqueOperators.size(), operatorCount);
		} catch (NullPointerException e) {
			System.out.println(" Error from treewalker!");
		}
	}

	public int getResults() {
		return operatorCount;
	}
	
	public int getOperatorCount() {
		return operatorCount;
	}

	public int getOperatorUniqueCount() {
		return uniqueOperators.size();
	}

	@Override
	public int[] getDefaultTokens() {
		return operatorTokens;
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

}
