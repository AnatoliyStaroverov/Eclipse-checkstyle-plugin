package eclipse_plugin;

// import testing class
import eclipse_plugin.NumberOfCommetsCheck;

// Importing testing frameworks
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit5.PowerMockRunner;

// Importing check-style dependencies. 
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class NumberOfCommentsCheckTest {
	
	int[] expectedTokens = { TokenTypes.COMMENT_CONTENT };
	
	@Test
	public void testBeginTree() {
		NumberOfCommetsCheck test = new NumberOfCommetsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast);
		assertEquals(0, test.getCount());
	}
	

}
