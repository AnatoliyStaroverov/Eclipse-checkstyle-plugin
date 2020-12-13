package WhiteBox_Tests;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.NumberOfExpressionsCheck;


public class NumberOfExpressionsCheckTest {
	
	NumberOfExpressionsCheck expressioncheck = new NumberOfExpressionsCheck();
	DetailAST ast = mock(DetailAST.class);

	@Test
	public void DefaultTokensTest() {
		assertArrayEquals(new int[] { TokenTypes.EXPR }, expressioncheck.getDefaultTokens());
	}

	@Test
	public void AcceptableTokensTest() {
		assertArrayEquals(new int[] { TokenTypes.EXPR }, expressioncheck.getAcceptableTokens());
	}

	@Test
	public void RequiredTokensTest() {
		assertArrayEquals(new int[0], expressioncheck.getRequiredTokens());
	}

	@Test // Test for single expression
	public void SingleExpressionTest() {

		expressioncheck.beginTree(ast);
		doReturn(TokenTypes.EXPR).when(ast).getType();

		expressioncheck.visitToken(ast);

		assertEquals(0, expressioncheck.getCount());
	}
	
	@Test // Test for multiple expressions.
	public void NestedExpressionTest() {

		expressioncheck.beginTree(ast);
		doReturn(1).when(ast).getChildCount();
		doReturn(1).when(ast).getChildCount(TokenTypes.EXPR);
		
		for (int i = 0; i < 10; i++) { 
			expressioncheck.visitToken(ast);  // 10 
			
			for (int J = 0; J < 10; J++) { 
				expressioncheck.visitToken(ast); // 100
			}
		}

		assertEquals(110, expressioncheck.getCount());
	}
	
	@Test // Test for no expressions.
	public void NoExpressionTest() {
		expressioncheck.beginTree(ast);

		assertEquals(0, expressioncheck.getCount());
	}

}
