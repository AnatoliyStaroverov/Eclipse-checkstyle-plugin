package WhiteBox_Tests;


import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.NumberOfLoopsCheck;

public class NumberOfLoopsCheckTest {

	int[] expectedTokens = { 
			TokenTypes.LITERAL_FOR, 
			TokenTypes.LITERAL_WHILE, 
			TokenTypes.LITERAL_DO 
			};

	
	
	@Test
	public void DefaultTokenTest() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void AcceptableTokensTest() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void RequiredTokensTest() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test // Test for no loops.
	public void NoloopTest() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		test.finishTree(ast);

		assertEquals(0, test.getLoopCount());
	}

	@Test // Test for for loops
	public void ForLoopTest() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast =mock(DetailAST.class);

		when(ast.getType()).thenReturn(TokenTypes.LITERAL_FOR);
		
		test.visitToken(ast);
		test.finishTree(ast);

		verify(ast).getType();
		
	}

	@Test // Test for while loops.
	public void WhileLoopTest() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_WHILE).when(ast).getType();
		test.visitToken(ast);
		
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}
	
	@Test // Test for do loops.
	public void DoWhileLoopTest() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_DO).when(ast).getType();
		test.visitToken(ast);
		
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}
	
	@Test // Test for all loops.
	public void GeneralLoopTest() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);
		
		doReturn(TokenTypes.LITERAL_FOR).when(ast).getType();
		for (int i = 0; i < 7; i++) { 
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LITERAL_DO).when(ast).getType();
		for (int i = 0; i < 2; i++) {
			test.visitToken(ast);
		}
		
		
		doReturn(TokenTypes.LITERAL_WHILE).when(ast).getType();
		for (int i = 0; i < 6; i++) { 
			test.visitToken(ast);
		}

		test.finishTree(ast);
		
		assertEquals(15, test.getLoopCount());
	}
	
	@Test // Test Exception and exception message.
	public void OperatorTest1() {
		
		NumberOfLoopsCheck test = spy(NumberOfLoopsCheck.class);
		DetailAST ast = mock(DetailAST.class);
		
	     final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outputStreamCaptor));
		
		test.beginTree(ast); 
		test.finishTree(ast);
			
		doThrow(NullPointerException.class).when(test).finishTree(null);
		assertEquals("Error in treewalker!",outputStreamCaptor.toString().trim());
		
	}
}
