package BlackBox_Tests;
import Test_Engine.TestEngine;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import Checks.NumberOfCommetsCheck;


public class NumberOfCommentsTest {
	
	// Find Test folder and file in project.
	//String projectPath = "\\src\\test\\java\\BlackBox_Test_Files\\Commments\\ ";
	String projectPath = "/src/test/java/BlackBox_Test_Files/Commments/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test
	public void EdgeCasetest() throws IOException {
		NumberOfCommetsCheck check = new NumberOfCommetsCheck(); 
		TestEngine test = new TestEngine(filePath , "Comment_Edge_Case.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(0, check.getResults()); 
    }
    
	
	@Test
	public void Regulartest() throws IOException {
		NumberOfCommetsCheck check = new NumberOfCommetsCheck(); 
		TestEngine test = new TestEngine(filePath , "Comments_Block.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(11, check.getResults()); 
    }
    
	
	

}
