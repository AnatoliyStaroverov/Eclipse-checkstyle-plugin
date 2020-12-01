package eclipse_plugin;
import com.puppycrawl.tools.checkstyle.api.*;
import java.util.ArrayList;

public class HalsteadLengthCheck extends AbstractCheck {
	
		public int halsteadLength;

		private  NumberOfOperandsCheck operandCount = new NumberOfOperandsCheck();
		private NumberOfOperatorCheck operatorCount = new NumberOfOperatorCheck();
	
		@Override
		public void beginTree(DetailAST rootAST) {
			operandCount.beginTree(rootAST);
			operatorCount.beginTree(rootAST);
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

		// This is the function where the halstead volume gets calculated.
		@Override
		public void finishTree(DetailAST rootAST) {

			try {
				log(0, "Halstead Length: " + halsteadLength);
			} catch (NullPointerException e) {
				System.out.println("Can't run log unless called from treewalker!");
			}
		}

		//getter for Halstead length.
		public int getHalsteadLength() {
			return halsteadLength;
		}

		// getters operand count.
		public int getOperandCount() {
			
		}

		// getter for operator count.
		public int getOperatorCount() {
			
		}

		
	  
}