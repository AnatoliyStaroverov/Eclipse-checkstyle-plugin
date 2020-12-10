package eclipse_plugin;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class NumberOfExpressionsCheckTest {
	
	NumberOfExpressionsCheck expressioncheck = new NumberOfExpressionsCheck();
	DetailAST ast = mock(DetailAST.class);

	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(new int[] { TokenTypes.EXPR }, expressioncheck.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(new int[] { TokenTypes.EXPR }, expressioncheck.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], expressioncheck.getRequiredTokens());
	}

	@Test // Test for single expression
	public void testVisitTokenDetailAST1() {

		expressioncheck.beginTree(ast);
		doReturn(TokenTypes.EXPR).when(ast).getType();

		expressioncheck.visitToken(ast);

		assertEquals(1, expressioncheck.getCount());
	}
	
	@Test // Test for multiple expressions.
	public void testVisitTokenDetailAST2() {

		expressioncheck.beginTree(ast);
		
		doReturn(TokenTypes.EXPR).when(ast).getType();
		for (int i = 0; i < 10; i++) { 
			expressioncheck.visitToken(ast);
		}

		assertEquals(10, expressioncheck.getCount());
	}
	
	@Test // Test for no expressions.
	public void testVisitTokenDetailAST3() {
		expressioncheck.beginTree(ast);

		assertEquals(0, expressioncheck.getCount());
	}

}
