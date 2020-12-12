package BlackBox_Tests;
import Test_Engine.TestEngine;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.NumberOfOperatorCheck;



public class NumberOfOperatorsCheckTest {
	
	String projectPath = "/src/test/java/BlackBox_Test_Files/Operators/";
	String filePath = System.getProperty("user.dir") + projectPath ;
		
	@Test // Tests To find all Operators
	public void OperatorTest1() throws IOException {
		
		NumberOfOperatorCheck check = new NumberOfOperatorCheck(); 
		TestEngine test = new TestEngine(filePath , "OperatorTest1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(61, check.getResults()); 
    }
	
	@Test // Tests To find all Operators
	public void OperatorTest2() throws IOException {
		
		NumberOfOperatorCheck check = new NumberOfOperatorCheck(); 
		TestEngine test = new TestEngine(filePath , "OperatorTest1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(25, check.getOperatorUniqueCount()); 
    }
	
	@Test // Tests on file with operators commented out. Should return 0
	public void OperatorTest3() throws IOException {
		
		NumberOfOperatorCheck check = new NumberOfOperatorCheck(); 
		TestEngine test = new TestEngine(filePath , "OperatorTest2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(0, check.getResults()); 
    }
	
}
