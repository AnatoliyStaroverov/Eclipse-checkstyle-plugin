package Test_Engine;

import java.io.File;
import java.io.IOException;
//import java.util.Hashtable;
import java.util.*; 

//import org.junit.jupiter.api.Test;
//import static org.junit.Assert.assertTrue;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;



public class TestEngine {
	
	private String filePath;
	private String fileName;
	AbstractCheck check;
	
	public TestEngine(String pathname, String filename,AbstractCheck testingCheck ) {
		filePath = pathname;
		fileName = filename;
		check = testingCheck;
	}
	
	public void RunTestCase() throws IOException, CheckstyleException {
		
		File file = new File(filePath + fileName);
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		
		// Initialize Intended Check
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
		
		//Hashtable<String,Integer> results = new Hashtable<String,Integer>(); 
		//results.put("loopTest", check.getResults());
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		//for(LocalizedMessage lm : check.getMessages()) {
		//	System.out.println(lm.getMessage());
		//}
		
		
		// Verify Results
		//assertTrue(results.get("loopTest") ==  3);
		//System.out.println(results.get("loopTest"));
		
	}
	
	
	public void helper(AbstractCheck b, DetailAST a) {
		
		// get allowed tokens from check
		int[] allowedTokens = check.getAcceptableTokens();
		

		List<Integer> intList = new ArrayList<Integer>(allowedTokens.length);
		for (int i : allowedTokens)
		{
		    intList.add(i);
		}
		
		
		
		
		// Condition for nodes in DetailAST
		while(a != null) {
			
			
			if(intList.contains(a.getType())) {
				b.visitToken(a);
			}
			
			
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}

}
