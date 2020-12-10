package Test_Engine;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import Checks.NumberOfLoopsCheck;

public class TestEngine {
	
	
	@Test
	void test() throws IOException, CheckstyleException {
		
		
		String filePath = "c:/Users/anato/eclipse-workspace/eclipse_plugin/src/test/java/Test_Files/";
		File file = new File(filePath + "LoopTest.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		
		// Initialize Intended Check
		NumberOfLoopsCheck check = new NumberOfLoopsCheck();
		check.setFileContents(fc);
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		
		
		// Fill AST with FileContents
		DetailAST root;
		
		if (check.isCommentNodesRequired()) {
			root = JavaParser.parseFile(file, Options.WITH_COMMENTS);
		} else {
			root = JavaParser.parse(fc);
		}
		
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		Hashtable<String,Integer> results = new Hashtable<String,Integer>(); 
		results.put("loopTest", check.getLoopCount());
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		//for(LocalizedMessage lm : check.getMessages()) {
		//	System.out.println(lm.getMessage());
		//}
		
		
		
		// Verify Results
		assertTrue(results.get("loopTest") ==  3);
		System.out.println(results.get("loopTest"));
		
	}
	
	
	public void helper(AbstractCheck b, DetailAST a) {
		System.out.println(" in helper engine function (not loop)");
		
		while(a != null) {
			System.out.println(" in helper engine function (in loop)");
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}

}
