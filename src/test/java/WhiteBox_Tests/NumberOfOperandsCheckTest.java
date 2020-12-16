package WhiteBox_Tests;


import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.NumberOfOperandsCheck;



public class NumberOfOperandsCheckTest {

	int[] operatorTypes = { 
			TokenTypes.IDENT, 
			TokenTypes.NUM_DOUBLE,
			TokenTypes.NUM_FLOAT,
			TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG 
			};

	@Test
	public void testGetDefaultTokens() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();

		assertArrayEquals(operatorTypes, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();

		assertArrayEquals(operatorTypes, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();

		assertArrayEquals(operatorTypes, test.getRequiredTokens());
	}

	@Test // Test For a single token.
	public void testGetOperandCount_Single() { 
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		doReturn(operatorTypes[0]).when(ast).getType();
		doReturn("operand").when(ast).getText();
		test.visitToken(ast);

		assertEquals(1, test.getOperandCount());
		assertEquals(1, test.getOperandUniqueCount());
	}

	@Test  // Tests unique cases 
	public void testGetOperandCount_Unique() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		doReturn(operatorTypes[0]).when(ast).getType(); 
		doReturn("operand").when(ast).getText();
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		assertEquals(10, test.getOperandCount());
		assertEquals(1, test.getOperandUniqueCount());
	}

	@Test  // Tests unique cases with multi..
	public void testGetOperandCount_Mult() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		
		doReturn(operatorTypes[0]).when(ast).getType();
		doReturn("operand1").when(ast).getText();
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		doReturn(operatorTypes[1]).when(ast).getType();
		doReturn("operand3").when(ast).getText();
		test.visitToken(ast);

		assertEquals(11, test.getOperandCount());
		assertEquals(2, test.getOperandUniqueCount());
	}
	
	@Test // Test for no Operands  and init value.
	public void NoOperandTest() {
		NumberOfOperandsCheck test = new NumberOfOperandsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		
		//test init value.
		assertEquals(0, test.getOperandCount());
		assertEquals(0, test.getOperandUniqueCount());
		
	}
	
	@Test // Test Exception and exception message.
	public void OperandTest1() {
		
		NumberOfOperandsCheck test = spy(NumberOfOperandsCheck.class);
		DetailAST ast = mock(DetailAST.class);
		
	     final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outputStreamCaptor));
		
		test.beginTree(ast); 
		test.finishTree(ast);
			
		doThrow(NullPointerException.class).when(test).finishTree(null);
		assertEquals("Error from treewalker!",outputStreamCaptor.toString().trim());
	
	}
}
