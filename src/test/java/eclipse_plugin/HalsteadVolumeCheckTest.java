package eclipse_plugin;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class HalsteadVolumeCheckTest {
	
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
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();

		List<Integer> toks = Arrays.stream(test.getDefaultTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		
		List<Integer> toks = Arrays.stream(test.getAcceptableTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		List<Integer> toks = Arrays.stream(test.getRequiredTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test //test with known values
	public void testGetHalsteadVolume() { 
		
		HalsteadVolumeCheck test = spy(new HalsteadVolumeCheck());
		DetailAST ast = mock(DetailAST.class);

		doReturn(37).when(test).getHalsteadLength();
		doReturn(16).when(test).getHalsteadVocabulary();
		
		test.beginTree(ast);
		test.finishTree(ast);

		//  volume = length * log2(vocabulary)
		//  volume = 37 * log2(16) = 148
		assertEquals(148, test.getHalsteadVolume(), 0.1);
	}

	@Test // Test to make sure you can initialized both operands and operator.
	public void testGetHalsteadVolume1() {
		
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		 // operand initialized
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType();
		test.visitToken(ast);

		// operator initialized
		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		test.visitToken(ast);

		test.finishTree(ast);

		// (operators +operands) * log2(unique operators + unique operands)
		assertEquals(2, test.getHalsteadVolume(), 0.1);
	}

	@Test // multiple operands and simple operator.
	public void testGetHalsteadVolume2() { 
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		// operand
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); 
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		// operator
		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(11, test.getHalsteadVolume(), 0.1);
	}

	@Test // single operands and  multiple operators.
	public void testGetHalsteadVolume3() { 
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);

		// operand
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); 
		doReturn("operand").when(ast).getText();
		test.visitToken(ast);

		// operator
		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		doReturn("operator").when(ast).getText();
		for (int i = 0; i < 10; i++) {
			test.visitToken(ast);
		}

		test.finishTree(ast);

		assertEquals(11, test.getHalsteadVolume(), 0.1);
	}

	@Test // Test multiple operands and operators.
	public void testGetHalsteadVolume4() { 
		
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		//  operands
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType();
		doReturn("operand1").when(ast).getText();
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		// operators
		doReturn(TokenTypes.LNOT).when(ast).getType(); 
		doReturn("operator1").when(ast).getText();
		for (int i = 0; i < 10; i++) {
			test.visitToken(ast);
		}
		
		test.finishTree(ast);

		assertEquals(20, test.getHalsteadVolume(), 0.2);
	}

	@Test // Test unique instances of operand and operators.
	public void testGetHalsteadVolume5() { 
		
		HalsteadVolumeCheck test = new HalsteadVolumeCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		// operand 1
		doReturn(TokenTypes.IDENT).when(ast).getType();
		doReturn("operand1").when(ast).getText();
		test.visitToken(ast);

		// operand 2
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		doReturn("operand2").when(ast).getText();
		test.visitToken(ast);

		// operator 1
		doReturn(TokenTypes.DEC).when(ast).getType(); 
		doReturn("operator1").when(ast).getText();
		test.visitToken(ast);

		 // operator 2
		doReturn(TokenTypes.LOR).when(ast).getType();
		doReturn("operator2").when(ast).getText();
		test.visitToken(ast);

		// operator 3
		doReturn(TokenTypes.PLUS).when(ast).getType(); 
		doReturn("operator3").when(ast).getText();
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(11.609, test.getHalsteadVolume(), 0.2);
	}

}
