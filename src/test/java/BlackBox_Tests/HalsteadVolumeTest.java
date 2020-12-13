package BlackBox_Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import Checks.HalsteadVolumeCheck;
import Test_Engine.TestEngine;

public class HalsteadVolumeTest {

	String projectPath = "/src/test/java/BlackBox_Test_Files/HalsteadVolume/";
	String filePath = System.getProperty("user.dir") + projectPath ;
	
	@Test // Tests Halstead vocabualary.
	public void VolumeTest1() throws IOException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(19, check.getHalsteadVocabulary()); 
    }
	
	@Test // Tests halstead length .
	public void VolumeTest2() throws IOException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(43, check.getHalsteadLength()); 
    }
	
	@Test // test single and block comments in one function.
	public void VolumeTest3() throws IOException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
		TestEngine test = new TestEngine(filePath , "test1.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(182.66, check.getHalsteadVolume(),0.1); 
    }
	
	@Test // test file with no operators
	public void VolumeTest4() throws IOException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
		TestEngine test = new TestEngine(filePath , "test2.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(6.33, check.getHalsteadVolume(),0.1); 
    }
	
	@Test // test file with no operands
	public void VolumeTest5() throws IOException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
		TestEngine test = new TestEngine(filePath , "test3.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(0.0, check.getHalsteadVolume(),0.1); 
    }
	
	@Test // test for 1 operand and 1 operator.
	public void VolumeTest6() throws IOException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck(); 
		TestEngine test = new TestEngine(filePath , "test3.java", check); 
		
		try {
			test.RunTestCase(); 
		} catch (CheckstyleException e) {
			
			e.printStackTrace();
		}

		assertEquals(0.0, check.getHalsteadVolume(),0.1); 
    }
}
