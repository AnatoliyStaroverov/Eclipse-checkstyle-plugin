package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.LinesOfCommentCheck;
import Test_Engine.TestEngine;

public class LinesOfCommentsTest {
	
	// Find Test folder and file in project.
		//String projectPath = "\\src\\test\\java\\BlackBox_Test_Files\\LinesOfComments\\ ";
		String projectPath = "/src/test/java/BlackBox_Test_Files/LineOfComments/";
		String filePath = System.getProperty("user.dir") + projectPath ;
		
		@Test // test single and block comments in one function.
		public void Commenttest1() throws IOException {
			LinesOfCommentCheck check = new LinesOfCommentCheck(); 
			TestEngine test = new TestEngine(filePath , "test1.java", check); 
			
			try {
				test.RunTestCase(); 
			} catch (CheckstyleException e) {
				
				e.printStackTrace();
			}

			assertEquals(11, check.getCount()); 
	    }
	    
		
		@Test  // testing comments in multiple functions.
		public void Commenttest2() throws IOException {
			LinesOfCommentCheck check = new LinesOfCommentCheck(); 
			TestEngine test = new TestEngine(filePath , "test2.java", check); 
			
			try {
				test.RunTestCase(); 
			} catch (CheckstyleException e) {
				
				e.printStackTrace();
			}

			assertEquals(14, check.getCount()); 
	    }

		@Test  // testing inproper comments
		public void Commenttest3() throws IOException {
			LinesOfCommentCheck check = new LinesOfCommentCheck(); 
			TestEngine test = new TestEngine(filePath , "test3.java", check); 
			
			try {
				test.RunTestCase(); 
			} catch (CheckstyleException e) {
				
				e.printStackTrace();
			}

			assertEquals(0, check.getCount()); 
	    }
}
