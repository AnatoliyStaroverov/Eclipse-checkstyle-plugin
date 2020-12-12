package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.NumberOfLoopsCheck;

import Test_Engine.TestEngine;
public class NumberOfLoopsTest {
	
	String projectPath = "/src/test/java/BlackBox_Test_Files/Loops/";
	String filePath = System.getProperty("user.dir") + projectPath ;

	@Test // Tests file for do, while, and for loops
	public void LoopTest1() throws IOException {
		
		NumberOfLoopsCheck check = new NumberOfLoopsCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(5, check.getLoopCount() ); 
    }
	
	@Test // tests commented out loops
	public void LoopTest2() throws IOException {
		
		NumberOfLoopsCheck check = new NumberOfLoopsCheck(); 
		TestEngine test = new TestEngine(filePath , "test2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(0, check.getLoopCount() ); 
    }
}
