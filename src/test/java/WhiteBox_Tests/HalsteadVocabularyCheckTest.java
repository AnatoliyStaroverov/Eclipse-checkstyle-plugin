package WhiteBox_Tests;


import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.HalsteadVocabularyCheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//Halstead Vocabulary is the sum of the number of unique operators 
// and unique operands 
public class HalsteadVocabularyCheckTest {

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
		
		HalsteadVocabularyCheck test = new HalsteadVocabularyCheck();
		List<Integer> toks = Arrays.stream(test.getDefaultTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadVocabularyCheck test = new HalsteadVocabularyCheck();
		
		List<Integer> toks = Arrays.stream(test.getAcceptableTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadVocabularyCheck test = new HalsteadVocabularyCheck();
		List<Integer> toks = Arrays.stream(test.getRequiredTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test // Test Uniqueness of  Operand and operator .
	public void UniquenessTest() {
		
		HalsteadVocabularyCheck test = new HalsteadVocabularyCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);

		//operand
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		test.visitToken(ast);
		
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		test.visitToken(ast);
		
		doReturn(TokenTypes.NUM_INT).when(ast).getType(); 
		test.visitToken(ast);
		
		//Operator
		doReturn(TokenTypes.ASSIGN).when(ast).getType(); 
		test.visitToken(ast);
		
		doReturn(TokenTypes.ASSIGN).when(ast).getType(); 
		test.visitToken(ast);
		
		doReturn(TokenTypes.ASSIGN).when(ast).getType(); 
		test.visitToken(ast);
		
		test.finishTree(ast);

		// Test operand count
		assertEquals(1, test.getUniqueOperandCount());
		// Test Operator count
		assertEquals(1, test.getUniqueOperatorCount() );
		
	}
	
	@Test  // Test multiple operands and operators to calculate Vocabulary.
	public void HalsteadVocabularyTest1() {
		
		HalsteadVocabularyCheck test = spy(new HalsteadVocabularyCheck());
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);
		
		doReturn(15).when(test).getUniqueOperandCount();
		doReturn(25).when(test).getUniqueOperatorCount();
		
		test.finishTree(ast);
		
		assertEquals(40, test.getHalsteadVocabulary());
		
	}


	@Test // Perform vocab test with a different approch. 
	public void HalsteadVocabularyTest2() {
		
		HalsteadVocabularyCheck test = new HalsteadVocabularyCheck();
		DetailAST ast = mock(DetailAST.class);
		
		test.beginTree(ast);
		
		
		
		// operator 1
		doReturn(TokenTypes.ASSIGN).when(ast).getType(); 
		doReturn("op-1").when(ast).getText();
		test.visitToken(ast);

		// operator 2
		doReturn(TokenTypes.BAND_ASSIGN).when(ast).getType(); 
		doReturn("ope-2").when(ast).getText();
		test.visitToken(ast);

		// operand 1 
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); 
		doReturn("oper-1").when(ast).getText();
		test.visitToken(ast);
		
		// operand 2
		doReturn(TokenTypes.NUM_INT).when(ast).getType();
		doReturn("oper-2").when(ast).getText();
		test.visitToken(ast);
				
		test.finishTree(ast);
		
		assertEquals(4, test.getHalsteadVocabulary());
		
	}
	
	

}
