package WhiteBox_Tests;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.NumberOfOperandsCheck;
import Checks.NumberOfOperatorCheck;


public class NumberOfOperatorsCheckTest {
	
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

	
	@Test
	public void testGetDefaultTokens() {
		NumberOfOperatorCheck test = new NumberOfOperatorCheck();

		assertArrayEquals(operatorTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		NumberOfOperatorCheck test = new NumberOfOperatorCheck();

		assertArrayEquals(operatorTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		NumberOfOperatorCheck test = new NumberOfOperatorCheck();

		assertArrayEquals(operatorTokens, test.getRequiredTokens());
	}

	@Test // Tests for a single operator count and unite count.
	public void testGetOperatorCount1() { 
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		doReturn(operatorTokens[0]).when(ast).getType();
		doReturn("operand").when(ast).getText();
		test.visitToken(ast);

		assertEquals(1, test.getOperandCount());
		assertEquals(1, test.getOperandUniqueCount());
	}

	@Test // Test for unique operators.
	public void testGetOperatorCount2() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		doReturn(operatorTokens[1]).when(ast).getType();
		doReturn("operator").when(ast).getText();
		
		for (int i = 0; i < 20; i++) { 
			test.visitToken(ast);
		}

		assertEquals(20, test.getOperandCount());
		assertEquals(1, test.getOperandUniqueCount());
	}

	@Test // Test for nested unique operators and many operands.
	public void testGetOperatorCount3() { 
		NumberOfOperandsCheck test = spy(new NumberOfOperandsCheck());
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		int count = 0;
		for (int operator : operatorTokens) {
			doReturn(operator).when(ast).getType();
			doReturn("operator" + operator).when(ast).getText();

			for (int i = 0; i < 10; i++) { 
				test.visitToken(ast);
			}

			count++;
		}

		int finalNumber = count * 10;

		assertEquals(finalNumber, test.getOperandCount());
		assertEquals(count, test.getOperandUniqueCount());
	}

	@Test // Test for No operators and init value.
	public void NoOperatorTest() {
		
		NumberOfOperandsCheck test = spy(new NumberOfOperandsCheck());
		DetailAST ast = mock(DetailAST.class);
		test.beginTree(ast); 
		
		assertEquals(0, test.getOperandCount());
		assertEquals(0, test.getOperandUniqueCount());
	}
	
	
	
	
  }

