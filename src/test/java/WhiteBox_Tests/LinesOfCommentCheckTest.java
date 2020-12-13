package WhiteBox_Tests;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Checks.LinesOfCommentCheck;


public class LinesOfCommentCheckTest {

	int[] expectedTokens = {
			TokenTypes.SINGLE_LINE_COMMENT, 
			TokenTypes.BLOCK_COMMENT_BEGIN 
			};

	@Test
	public void testGetDefaultTokens() {
		LinesOfCommentCheck test = new LinesOfCommentCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		LinesOfCommentCheck test = new LinesOfCommentCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		LinesOfCommentCheck test = new LinesOfCommentCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test  // Test for no comments.
	public void NoLineCommentTest() { 
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		assertEquals(0, test.getCount());
	}
	
	@Test // Test for single comments.
	public void SingleLineCommentTest() {
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);

		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		test.visitToken(ast);

		assertEquals(1, test.getCount());
	}

	
	@Test // Test for comments blocks
	public void BlockLineCommentTest() { 

	/*  Block comments are calculated  by  an incrementer  to account for the (/*)
	 *  plus  (BLOCK_COMMENT_END current  line - BLOCK_COMMENT_BEGIN curent line). Thus,
	 *   Block comment line Count (BCLC) = 1 + (BLOCK_COMMENT_END - BLOCK_COMMENT_BEGIN)
	 */
		
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		
		DetailAST blockBegin = mock(DetailAST.class); // TokenTypes.BLOCK_COMMENT_BEGIN
		DetailAST blockEnd = mock(DetailAST.class); // TokenTypes.BLOCK_COMMENT_END
		
		test.beginTree(blockBegin); 
		
		//  The Tested Example  so, BCLC = 1 + (4-1) = 4
		
		/*  This isn't counted for in the BLOCK_COMMENT_BEGIN, so add manually.
		 * (1) BLOCK_COMMENT_BEGIN
		 * (2)
		 * (3)
		 * (4) BLOCK_COMMENT_END  */

		
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(blockBegin).getType(); 
		doReturn(TokenTypes.BLOCK_COMMENT_END).when(blockEnd).getType();
		
		// link blockBegin and blockEnd together
		doReturn(blockEnd).when(blockBegin).findFirstToken(TokenTypes.BLOCK_COMMENT_END);
		
		// set lines for starting and ending point 
		doReturn(1).when(blockBegin).getLineNo();
		doReturn(4).when(blockEnd).getLineNo();
		
		test.visitToken(blockBegin);
		
		assertEquals(4, test.getCount());
	}
	
	@Test // single and block comments lines.
	public void MultiTypeCommentTest() {
		
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		
		DetailAST ast = mock(DetailAST.class); // for TokenTypes.BLOCK_COMMENT_BEGIN
		DetailAST asts = mock(DetailAST.class); // TokenTypes.BLOCK_COMMENT_END

		test.beginTree(ast); 
		
		// loop through single comments 10 times.
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType(); // block begin
		doReturn(TokenTypes.BLOCK_COMMENT_END).when(asts).getType(); // block end 
		
		// link blockBegin and blockEnd together
		doReturn(asts).when(ast).findFirstToken(TokenTypes.BLOCK_COMMENT_END);
		// Set size 
		doReturn(1).when(ast).getLineNo();
		doReturn(4).when(asts).getLineNo();
		
		// loop through block comment 10 times of size 4 so, return 40.
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		assertEquals(50, test.getCount());
	}

}
