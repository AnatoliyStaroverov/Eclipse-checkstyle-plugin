package eclipse_plugin;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


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
	public void testCountCommentsCheck_No() { 
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		assertEquals(0, test.getCount());
	}
	
	@Test // Test for single comments.
	public void testCountCommentsCheck_Single() {
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast);

		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		test.visitToken(ast);

		assertEquals(1, test.getCount());
	}

	@Test // Test for comments blocks
	public void testCountCommentsCheck_Block() { 
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		DetailAST ast = mock(DetailAST.class);

		test.beginTree(ast); 

		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType();
		test.visitToken(ast);

		assertEquals(1, test.getCount());
	}
	
	@Test // Tests a combinations of loop conditions.
	public void testCountCommentsCheck4() {
		
		LinesOfCommentCheck test = new LinesOfCommentCheck();
		DetailAST ast =mock(DetailAST.class);

		test.beginTree(ast); 
		
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType();
		for (int i = 0; i < 10; i++) { 
			test.visitToken(ast);
		}

		assertEquals(20, test.getCount());
	}

}
