package WhiteBox_Tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.HalsteadLengthCheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

// Halstead Length is the sum of the total number of operators and operand.

public class HalsteadLengthCheckTest {
	
	Integer[] tokens = { 
			
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

	HashSet<Integer> expectedTokens = new HashSet<Integer>(Arrays.asList(tokens));

	@Test
	public void DefaultTokensTest() {
		
		HalsteadLengthCheck test = new HalsteadLengthCheck();
		List<Integer> toks = Arrays.stream(test.getDefaultTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadLengthCheck test = new HalsteadLengthCheck();
		
		List<Integer> toks = Arrays.stream(test.getAcceptableTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadLengthCheck test = new HalsteadLengthCheck();
		List<Integer> toks = Arrays.stream(test.getRequiredTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test // Test Multiple Operand and operator .
	public void MultipleTest() {
		
		HalsteadLengthCheck test = new HalsteadLengthCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);

		//operand
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		test.visitToken(ast);
		
		//operand
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		test.visitToken(ast);
		
		//operand
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		test.visitToken(ast);
		
		//Operator
		doReturn(TokenTypes.ASSIGN).when(ast).getType(); 
		test.visitToken(ast);
		
		//Operator
		doReturn(TokenTypes.ASSIGN).when(ast).getType(); 
		test.visitToken(ast);

		test.finishTree(ast);

		// Test operand count
		assertEquals(3, test.getOperandCount());
		// Test Operator count
		assertEquals(2, test.getOperatorCount());
		
		
	}


	@Test // Test Calculation of Halstead Length.
	public void HalsteadLengthTest1() {
		
		HalsteadLengthCheck test = spy(new HalsteadLengthCheck());
		DetailAST ast = mock(DetailAST.class);
		
		doReturn(3).when(test).getOperandCount(); 
		doReturn(2).when(test).getOperatorCount(); 
		
		test.beginTree(ast); 
		test.finishTree(ast);

		assertEquals(5, test.calcHalsteadLength());
	}

	
	@Test // Test for multiple operands and single operator.
	public void HalsteadLengthTest2() { 
		HalsteadLengthCheck test = spy(new HalsteadLengthCheck());
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		
		// Test 10 operands 
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); 
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		// Test single operator.
		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(11, test.calcHalsteadLength());
	}

	@Test // Test single operand and multiple operators.
	public void HalsteadLengthTest3() { 
		HalsteadLengthCheck test = spy(new HalsteadLengthCheck());
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		
		// Test single operand
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); 
		test.visitToken(ast);

		// testing multiple operators.
		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		for (int i = 0; i < 10; i++) { 
		 test.visitToken(ast);
		}

		test.finishTree(ast);

		assertEquals(11, test.calcHalsteadLength());
	}

}
