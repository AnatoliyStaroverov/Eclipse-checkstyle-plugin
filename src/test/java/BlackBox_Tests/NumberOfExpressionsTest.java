package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.NumberOfExpressionsCheck;
import Test_Engine.TestEngine;

public class NumberOfExpressionsTest {

	String projectPath = "/src/test/java/BlackBox_Test_Files/Expressions/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test // Tests To find all Expersions
	public void ExpresionTest1() throws IOException {
		
		NumberOfExpressionsCheck check = new NumberOfExpressionsCheck(); 
		TestEngine test = new TestEngine(filePath , "test2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(7, check.getCount()); 
    }
	
	@Test // Tests To find all Operands
	public void ExpresionTest2() throws IOException {
		
		NumberOfExpressionsCheck check = new NumberOfExpressionsCheck(); 
		TestEngine test = new TestEngine(filePath , "test2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(7, check.getCount()); 
    }
	
	@Test // Tests on file with Operands commented out. Should return 1 
	// because of class name.
	public void ExpresionTest3() throws IOException {
		
		NumberOfExpressionsCheck check = new NumberOfExpressionsCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(0, check.getCount()); 
    }
}
