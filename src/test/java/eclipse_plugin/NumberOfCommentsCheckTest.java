/*
 * Tests the check-styles "Number of Comments" in files
 * by testing the following components of the NumberOfCommets Class:
 * 	
 *   1. initial tree construction. 
 *   2. required and accepted tokens.
 *   3. No comments
 *   4. Single comments
 *   5. block comments
 *   
 * */

package eclipse_plugin;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class NumberOfCommentsCheckTest {
	
	int[] expectedTokens = { TokenTypes.COMMENT_CONTENT };
	
	/*  Tests the initialization of the NumberIfCommetsCheck class.
	 *  Verifies that count is set to zero.  */
	@Test
	public void BeginTreeTest() {
		NumberOfCommetsCheck test = new NumberOfCommetsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast);
		assertEquals(0, test.getCount());
	}
	
	/* Tests that NumberIfCommetsCheck class has only required tokens.  */
	@Test
	public void GetRequiredTokensTest() {
		NumberOfCommetsCheck test = new NumberOfCommetsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}
	
	// Test function for only accepting the desired tokens.
	@Test
	public void GetAcceptableTokensTest() {
		NumberOfCommetsCheck test = new NumberOfCommetsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}
	
	// Test for single comments 
	@Test
	public void SingleCommentTest() {
		NumberOfCommetsCheck test = new NumberOfCommetsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		test.beginTree(ast); 
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		test.visitToken(ast);
		test.visitToken(ast);
		test.visitToken(ast);
		assertEquals(3, test.getCount());
	}
	
	// Test for block comments
	// Test for no comments 
	
	
	
	

	
	
	

}
