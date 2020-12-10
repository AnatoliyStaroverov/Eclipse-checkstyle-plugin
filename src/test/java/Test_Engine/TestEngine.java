package Test_Engine;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import eclipse_plugin.NumberOfLoopsCheck;

public class TestEngine {
	
	@Test
	void test() throws IOException, CheckstyleException {
		// Build File
		String filePath = "CheckStyleTests/src/MyTests/";
		File file = new File(filePath + "LoopingTest.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		NumberOfLoopsCheck check = new NumberOfLoopsCheck();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		//for(LocalizedMessage lm : check.getMessages()) {
		//	System.out.println(lm.getMessage());
		//}
		
		Hashtable<String,Integer> results = check.getLoopCount();
		
		// Verify Results
		assertTrue(results.get("loopingStatements") == 3);
		System.out.println("LoopingCheck Done!");
		
	}
	
	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}

}
