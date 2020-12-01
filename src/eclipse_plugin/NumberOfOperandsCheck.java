package eclipse_plugin;

import java.util.HashSet;
import com.puppycrawl.tools.checkstyle.api.*;

public class NumberOfOperandsCheck extends AbstractCheck {

	int operandCount = 0;
	HashSet<String> uniqueOperand = new HashSet<String>(); 
	
	public int getOperandCount() {
		return operandCount;
	}

	public int getOperandUniqueCount() {
		return uniqueOperand.size();
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		operandCount = 0; 
		uniqueOperand = new HashSet<String>(); 
	}

	@Override
	public void visitToken(DetailAST aAST) {
		operandCount++;
		uniqueOperand.add(aAST.getText()); 
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		try {
			log(0, "{0} unique operands that appear {1} times.", uniqueOperand.size(), operandCount);
		} catch (NullPointerException e) {
			System.out.println("Error from treewalker!");
		}
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { 
				TokenTypes.IDENT, 
				TokenTypes.NUM_DOUBLE,
				TokenTypes.NUM_FLOAT,
				TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG 
		};
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
