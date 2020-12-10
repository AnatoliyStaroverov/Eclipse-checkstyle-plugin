package WhiteBox_Tests;


import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
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
	public void testGetDefaultTokens() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test // Test for no loops.
	public void testLoop_No() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 
		test.finishTree(ast);

		assertEquals(0, test.getLoopCount());
	}

	@Test // Test for for loops
	public void testLoop_For() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast =mock(DetailAST.class);

		when(ast.getType()).thenReturn(TokenTypes.LITERAL_FOR);
		
		test.visitToken(ast);
		test.finishTree(ast);

		verify(ast).getType();
		
	}

	@Test // Test for while loops.
	public void testLoop_While() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_WHILE).when(ast).getType();
		test.visitToken(ast);
		
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}
	
	@Test // Test for do loops.
	public void testLoop_do() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_DO).when(ast).getType();
		test.visitToken(ast);
		
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}
	
	@Test // Test for all loops.
	public void testLoop5() { 
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = mock(DetailAST.class);
		
		doReturn(TokenTypes.LITERAL_FOR).when(ast).getType();
		for (int i = 0; i < 5; i++) { 
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LITERAL_DO).when(ast).getType();
		for (int i = 0; i < 5; i++) {
			test.visitToken(ast);
		}
		
		
		doReturn(TokenTypes.LITERAL_WHILE).when(ast).getType();
		for (int i = 0; i < 5; i++) { 
			test.visitToken(ast);
		}

		test.finishTree(ast);
		
		assertEquals(15, test.getLoopCount());
	}
}
