package WhiteBox_Tests;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

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
		NumberOfOperatorCheck test = new NumberOfOperatorCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		doReturn(operatorTokens[0]).when(ast).getType();
		doReturn("operand").when(ast).getText();
		test.visitToken(ast);

		assertEquals(1, test.getOperatorCount());
		assertEquals(1, test.getOperatorUniqueCount());
		assertEquals(1, test.getResults());
	}

	@Test // Test for unique operators.
	public void testGetOperatorCount2() {
		NumberOfOperatorCheck test = new NumberOfOperatorCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		doReturn(operatorTokens[1]).when(ast).getType();
		doReturn("operator").when(ast).getText();
		
		for (int i = 0; i < 20; i++) { 
			test.visitToken(ast);
		}

		assertEquals(20, test.getOperatorCount());
		assertEquals(1, test.getOperatorUniqueCount());
		
	}

	@Test // Test for nested unique operators and many operands.
	public void testGetOperatorCount3() { 
		NumberOfOperatorCheck test = spy(new NumberOfOperatorCheck());
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

		assertEquals(finalNumber, test.getOperatorCount());
		assertEquals(count, test.getOperatorUniqueCount());
	}

	@Test // Test for No operators and init value.
	public void NoOperatorTest() {
		
		NumberOfOperatorCheck test = new NumberOfOperatorCheck();
		DetailAST ast = mock(DetailAST.class);
		test.beginTree(ast); 
		
		
		assertEquals(0, test.getOperatorCount());
		assertEquals(0, test.getOperatorUniqueCount());
		
	}
	
	
	@Test // Test Exception and exception message.
	public void OperatorTest1() {
		
		NumberOfOperatorCheck test = spy(NumberOfOperatorCheck.class);
		DetailAST ast = mock(DetailAST.class);
		
	     final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outputStreamCaptor));
		
		test.beginTree(ast); 
		test.finishTree(ast);
			
		doThrow(NullPointerException.class).when(test).finishTree(null);
		assertEquals("Error from treewalker!",outputStreamCaptor.toString().trim());
		
	}
  }

