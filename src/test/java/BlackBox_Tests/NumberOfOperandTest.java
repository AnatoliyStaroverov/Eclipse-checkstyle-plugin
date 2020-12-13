package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.NumberOfOperandsCheck;
import Test_Engine.TestEngine;

public class NumberOfOperandTest {
	
	String projectPath = "/src/test/java/BlackBox_Test_Files/Operands/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test // Tests To find all Operands
	public void OperatorTest1() throws IOException {
		
		NumberOfOperandsCheck check = new NumberOfOperandsCheck(); 
		TestEngine test = new TestEngine(filePath , "test2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(15, check.getOperandCount()); 
    }
	
	@Test // Tests To find all Operands
	public void OperatorTest2() throws IOException {
		
		NumberOfOperandsCheck check = new NumberOfOperandsCheck(); 
		TestEngine test = new TestEngine(filePath , "test2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(12, check.getOperandUniqueCount()); 
    }
	
	@Test // Tests on file with Operands commented out. Should return 3 
	// because of class name and packages.
	public void OperatorTest3() throws IOException {
		
		NumberOfOperandsCheck check = new NumberOfOperandsCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(3, check.getOperandCount()); 
    }
	

}
