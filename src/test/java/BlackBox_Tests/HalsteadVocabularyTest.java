package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.HalsteadVocabularyCheck;
import Test_Engine.TestEngine;

public class HalsteadVocabularyTest {
	
	String projectPath = "/src/test/java/BlackBox_Test_Files/HalsteadVocabulary/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test  
	public void LengthTest1() throws IOException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(7.0, check.getUniqueOperatorCount(),0.1); 
    }
	
	
	@Test 
	public void LengthTest2() throws IOException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
		
			e.printStackTrace();
		}

		assertEquals(12.0, check.getUniqueOperandCount() ,0.1); 
    }
	
	
	@Test  
	public void LengthTest3() throws IOException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(19., check.getHalsteadVocabulary(),0.1); 
    }

}
