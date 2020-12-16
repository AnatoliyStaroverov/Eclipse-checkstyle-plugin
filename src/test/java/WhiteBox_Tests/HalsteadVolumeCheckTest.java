package WhiteBox_Tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.HalsteadVolumeCheck;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/* Halstead Volume is the program length (N) times the log2 of the 
 * program vocabulary (n) [1,2] : 
 * Volume = N log2 n 
 */

	

public class HalsteadVolumeCheckTest {
	 
	Integer[] tokens = { /* Unary Operator Type*/	
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
			TokenTypes.NUM_LONG };
	
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
	public void HalsteadVolumeTest() { 
		
		HalsteadVolumeCheck test = spy(new HalsteadVolumeCheck());
		DetailAST ast = mock(DetailAST.class);

		doReturn(45).when(test).getHalsteadLength();
		doReturn(19).when(test).getHalsteadVocabulary();
		
		test.beginTree(ast);
		test.finishTree(ast);

		//  volume = length * log2(vocabulary)
		//  volume = 45 * log2(19) = 191.15
		assertEquals(191.15, test.getHalsteadVolume(), 0.1);
	}

	@Test // Test visit tokens functions to makes sure wrong tokens dont count.
	public void TestVisitTokens() {
		
		HalsteadVolumeCheck test = spy(new HalsteadVolumeCheck());
		DetailAST ast = mock(DetailAST.class);
		test.beginTree(ast);
		
		doReturn(TokenTypes.LITERAL_FOR).when(ast).getType(); 
		doReturn("forloop").when(ast).getText();
		
		assertEquals(0.0, test.getHalsteadVolume(), 0.1);
		assertEquals(0, test.getHalsteadLength());
		assertEquals(0, test.getHalsteadVocabulary());
	}
	
	/*@Test // Test Exception and exception message.
	public void NumCommentsTest1() {
		
		HalsteadVolumeCheck test = spy(HalsteadVolumeCheck.class);
		DetailAST ast = mock(DetailAST.class);
		
	     final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outputStreamCaptor));
		
		test.beginTree(ast); 
		test.finishTree(ast);
			
		doThrow(NullPointerException.class).when(test).finishTree(null);
		assertEquals("Error from treewalker!\n(finish tree) Error from treewalker!\nError from treewalker!",outputStreamCaptor.toString().trim());
		
	}*/
	
	@Test // Test halstead voc and length.
	public void NumCommentsTest2() {
		
		HalsteadVolumeCheck test = spy(HalsteadVolumeCheck.class);
		DetailAST ast = mock(DetailAST.class);
		
		test.beginTree(ast); 
		
		doReturn(0).when(test).getHalsteadLength();
		doReturn(0).when(test).getHalsteadVocabulary();
		
		test.finishTree(ast);
			
		assertEquals(0,test.getHalsteadLength());
		assertEquals(0,test.getHalsteadVocabulary());
		
	}
}
