package eclipse_plugin;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;



public class HalsteadLengthCheckTest {
	
	Integer[] tokens = { TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR,
			TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,
			TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT,
			TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
			TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL,
			TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
			TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.IDENT,
			TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };

	HashSet<Integer> expectedTokens = new HashSet<Integer>(Arrays.asList(tokens));

	@Test
	public void testGetDefaultTokens() {
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

	@Test // Test Operand and operator .
	public void testVisit() {
		HalsteadLengthCheck test = new HalsteadLengthCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); 
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(1, test.getOperandCount());
		assertEquals(1, test.getOperatorCount());
	}


	@Test // Test single operand and operator.
	public void testGetHalsteadLength01() {
		HalsteadLengthCheck test = spy(new HalsteadLengthCheck());
		DetailAST ast = mock(DetailAST.class);

		doReturn(1).when(test).getOperandCount(); 
		doReturn(1).when(test).getOperatorCount(); 
		test.beginTree(ast); 

		test.finishTree(ast);

		assertEquals(2, test.getHalsteadLength());
	}

	
	@Test // Test for multiple operands and single operator.
	public void testGetHalsteadLength2() { 
		HalsteadLengthCheck test = new HalsteadLengthCheck();
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

		assertEquals(11, test.getHalsteadLength());
	}

	@Test // Test single operand and multiple operators.
	public void testGetHalsteadLength3() { 
		HalsteadLengthCheck test = new HalsteadLengthCheck();
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

		assertEquals(11, test.getHalsteadLength());
	}

}
